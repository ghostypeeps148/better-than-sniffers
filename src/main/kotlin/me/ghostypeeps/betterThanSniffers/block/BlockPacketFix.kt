package me.ghostypeeps.betterThanSniffers.block

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.ListenerOptions
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import com.comphenix.protocol.wrappers.WrappedLevelChunkData
import io.netty.buffer.Unpooled
import io.papermc.paper.antixray.ChunkPacketInfo
import me.ghostypeeps.betterThanSniffers.util.SnifferUtil
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.VarInt
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacketData
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import org.bukkit.craftbukkit.entity.CraftPlayer
import java.lang.reflect.Field


// todo: code could have some minor optimizations done
object BlockPacketFix {
    // temporary
    val testMap = mapOf(
        Block.BLOCK_STATE_REGISTRY.getIdOrThrow(Blocks.STONE.defaultBlockState()) to Block.BLOCK_STATE_REGISTRY.getIdOrThrow(Blocks.DIRT.defaultBlockState()),
        Block.BLOCK_STATE_REGISTRY.getIdOrThrow(Blocks.WATER.defaultBlockState()) to Block.BLOCK_STATE_REGISTRY.getIdOrThrow(Blocks.WAXED_EXPOSED_CHISELED_COPPER.defaultBlockState())
        )
    val buffer : Field = ClientboundLevelChunkPacketData::class.java.getDeclaredField("d")
    init {
        buffer.isAccessible = true
    }
    fun init() {
        val m : ProtocolManager = ProtocolLibrary.getProtocolManager()
        m.addPacketListener(
            object : PacketAdapter(SnifferUtil.SNIFFER_PLUGIN, ListenerPriority.NORMAL, listOf(PacketType.Play.Server.MAP_CHUNK), ListenerOptions.ASYNC) {
                override fun onPacketSending(event: PacketEvent) {
                    val packet = event.packet
                    val nmsPacket = packet.handle as ClientboundLevelChunkWithLightPacket
                    val nmsPlayer = (event.player as CraftPlayer).handle
                    val level = nmsPlayer.level()
                    val x = nmsPacket.x
                    val z = nmsPacket.z
                    val chunk = level.getChunk(x, z)
                    val sections = chunk.sections
                    val buf = FriendlyByteBuf(Unpooled.copiedBuffer(nmsPacket.chunkData.readBuffer))
                    // Mark the writer index at the end of the data
                    buf.markWriterIndex()
                    sections@ for (i in sections.indices) {
                        // Get section
                        val section = sections[i]
                        println("SECTION: $i")
                        if (section.hasOnlyAir()) {
                            // section is air so we just skip the whole section
                            buf.skipBytes(section.serializedSize)
                            println("Section is air")
                            continue
                        }
                        // Ignore the Block Count value + BPE
                        buf.skipBytes(3)
                        // Mark before write so we can come back to write
                        val indexBeforePalette = buf.readerIndex()
                        // Write the elements but not before modifying it
                        val elements: IntArray = buf.readVarIntArray()

                        val indexAfterPalette = buf.readerIndex()

                        val new = elements.map {
                            return@map testMap[it] ?: it
                        }.toIntArray()

                        // the next section can almost definitely be optimized but I am not sure how so I would appreciate help

                        // Optimization: No need to modify anything if arrays are still equal
                        if (new.contentEquals(elements)) {
                            buf.skipBytes(section.states.data.storage().raw.size * 8 + section.biomes.serializedSize)
                            println("Content is equal")
                            continue
                        }
                        // Additionally, if byte size is equal then no need to do offsets and we can not assume offsets
                        val serializedOfNew = new.sumOf { return@sumOf VarInt.getByteSize(it) }
                        println("Serialized size of new array: $serializedOfNew")
                        println("Old: " + ((indexAfterPalette - indexBeforePalette)-1))
                        // Check if offsets. Instead of wasting time calculating size of old array we can use the saved reader indexes.
                        buf.readerIndex(indexBeforePalette)
                        // OBOEs for some reason
                        if (serializedOfNew + 1 != indexAfterPalette - indexBeforePalette) {
                            println("Offsets are happening but my handler sucks")
                            val t = buf.slice(indexAfterPalette, buf.writerIndex() - indexAfterPalette)
                            println("Ind after: $indexAfterPalette")
                            println("Writer index: " + buf.writerIndex())
                            buf.writerIndex(indexBeforePalette)
                            buf.writeVarIntArray(new)
                            new.forEach {
                                println(Block.BLOCK_STATE_REGISTRY.byId(it))
                            }
                            buf.readerIndex(buf.writerIndex())
                            buf.writeBytes(t)
                            buf.markWriterIndex()
                        } else {
                            println("No offset")
                            // Bring writer index to the new location and write it in directly, so no really long buffer copying
                            buf.writerIndex(indexBeforePalette)
                            buf.writeVarIntArray(new)
                            buf.resetWriterIndex()
                            buf.readerIndex(indexAfterPalette)
                        }
                        buf.skipBytes(section.states.data.storage().raw.size * 8 + section.biomes.serializedSize)
                    }

                    // Make a chunk data
                    val chunkData = ClientboundLevelChunkPacketData(chunk, ChunkPacketInfo(nmsPacket, chunk))
                    // reflection yippee. It's much easier to set the buffer using reflection so that's what I'm doing
                    buffer.set(chunkData, buf.array())
                    // Wrap the NMS data
                    val wrappedData = WrappedLevelChunkData.ChunkData(chunkData)
                    // Write the wrapped data
                    packet.levelChunkData.write(0, wrappedData)

                    event.packet = packet
                }
            }
        )
    }
}