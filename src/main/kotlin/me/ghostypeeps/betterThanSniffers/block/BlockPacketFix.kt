package me.ghostypeeps.betterThanSniffers.block

import io.netty.buffer.Unpooled
import io.netty.channel.Channel
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPipelineException
import io.netty.channel.ChannelPromise
import me.ghostypeeps.betterThanSniffers.util.SnifferUtil
import net.minecraft.core.IdMap
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.protocol.configuration.ClientboundRegistryDataPacket
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacketData
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.chunk.LevelChunk
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

object BlockPacketFix {
    fun interceptChunkData(player: Player) {
        // Gets Netty channel from the player.
        val playerNMS = (player as CraftPlayer).handle
        val channel: Channel = playerNMS.connection.connection.channel
        // Inject a custom handler before the default packet handler
        // note for me 2: this takes a object implementing ChannelDuplexHandler and injects it.
        // todo: when reconnecting this will error with channel pipeline exception
        try {
            channel.pipeline().addBefore("packet_handler", "sniffer_sanitize_chunk_data",
                object : ChannelDuplexHandler() {
                    override fun write(ctx: ChannelHandlerContext, packet: Any, promise: ChannelPromise) {
                        // intercepts with better than sniffers' code
                        if (packet is ClientboundLevelChunkWithLightPacket) {
                            println("Better Than Sniffers intercepted the ClientboundLevelChunkWithLightPacket")
                            val buf = FriendlyByteBuf(Unpooled.buffer())
                            val level = playerNMS.level()
                            val chunk = level.getChunk(packet.x, packet.z)
                            buf.writeInt(chunk.locX)
                            buf.writeInt(chunk.locZ)
                            val sections = chunk.sections
                            var primaryBitMask = 0
                            for (i in sections.indices) {
                                val section = sections[i]
                                if (section.hasOnlyAir()) continue
                                for (x in 0 until 16)
                                    for (y in 0 until 16)
                                        for (z in 0 until 16)
                                            // section.states.set(x, y, z, Blocks.OAK_PLANKS.defaultBlockState())
                                primaryBitMask = primaryBitMask or (1 shl i)
                            }
                            buf.writeVarInt(primaryBitMask)
                            val bitsPerEntry = buf.readByte()
                            val paletteSize = buf.readVarInt()
                            repeat(paletteSize) {
                                val rawId = buf.readVarInt()
                                val newId = BuiltInRegistries.BLOCK.getId(Blocks.OAK_PLANKS)
                                buf.writeVarInt(newId)
                            }
                            packet.chunkData.write(RegistryFriendlyByteBuf(buf, SnifferUtil.SERVER.registryAccess()))
                        }

                        // writes the rest of the packet instead of reimplementing everything
                        super.write(ctx, packet, promise)
                    }
                }
            )
        } catch (_: ChannelPipelineException) {
            println("Better than Sniffers attempted to re-register packet interceptor for ClientboundLevelChunkWithLightPacket, ignoring")
        }
    }

}