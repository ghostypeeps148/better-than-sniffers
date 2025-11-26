package me.ghostypeeps.betterThanSniffers.block

import ca.spottedleaf.moonrise.patches.fast_palette.FastPaletteData
import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.ListenerOptions
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import com.comphenix.protocol.wrappers.WrappedLevelChunkData
import com.comphenix.protocol.wrappers.WrappedLevelChunkData.LightData
import io.netty.buffer.Unpooled
import io.netty.channel.Channel
import io.papermc.paper.antixray.ChunkPacketInfo
import me.ghostypeeps.betterThanSniffers.util.SnifferUtil
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacketData
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import org.bukkit.block.Block
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.inventory.ItemType
import sun.misc.Unsafe
import java.lang.reflect.Field


object BlockPacketFix {
    private val unsafe : Unsafe
    init {
        val unsafeField: Field = Unsafe::class.java.getDeclaredField("theUnsafe")
        unsafeField.isAccessible = true
        unsafe = unsafeField.get(null) as Unsafe
    }
    fun init() {
        val m : ProtocolManager = ProtocolLibrary.getProtocolManager()
        m.addPacketListener(
            object : PacketAdapter(SnifferUtil.SNIFFER_PLUGIN, ListenerPriority.NORMAL, listOf(PacketType.Play.Server.MAP_CHUNK), ListenerOptions.ASYNC) {
                override fun onPacketSending(event: PacketEvent) {
                    val packet = event.packet
                    val nmsPacket = event.packet.handle as ClientboundLevelChunkWithLightPacket
                    val nmsPlayer = (event.player as CraftPlayer).handle
                    val x = nmsPacket.x
                    val z = nmsPacket.z
                    val level = nmsPlayer.level()
                    val chunk = level.getChunk(x, z)
                    val sections = chunk.sections
                    val chunkPacketInfo = ChunkPacketInfo<BlockState>(nmsPacket, chunk)
                    for (i in sections.indices) {
                        val section = sections[i]
                        if (section.hasOnlyAir()) continue
                        val buf = FriendlyByteBuf(Unpooled.buffer())
                        section.states.data.palette().write(buf)
                        println(buf.readByteArray().toHexString())
                    }
                    val chunkData = WrappedLevelChunkData.ChunkData(chunkPacketInfo)
                    packet.levelChunkData.write(0, chunkData)
                    packet.lightUpdateData.write(0, LightData(nmsPacket.lightData))
                    // event.setPacket(packet)
                }
            }
        )
    }

}
/*
val ml = ArrayList<BlockState>()
                        section.states.data.`moonrise$getPalette`().forEach {
                            try {
                                if (it.block != Blocks.DIRT) {
                                    ml.add(it)
                                } else {
                                    ml.add(Blocks.OAK_PLANKS.defaultBlockState())
                                }
                            } catch (_: Exception) {
                                ml.add(Blocks.AIR.defaultBlockState())
                            }
                        }
                        val mlArr = ml.toArray(arrayOfNulls<BlockState>(ml.size))
                        val tmp = section.copy().states.data
                        tmp.`moonrise$setPalette`(mlArr)
                        chunkPacketInfo.setPalette(i, tmp.palette())
                        chunkPacketInfo.getPalette(i).`moonrise$getRawPalette`(tmp).forEach {
                            println(it)
                        }
 */