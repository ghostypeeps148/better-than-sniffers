package me.ghostypeeps.betterThanSniffers.block

import io.netty.buffer.Unpooled
import io.netty.channel.Channel
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPipelineException
import io.netty.channel.ChannelPromise
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
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
                            val writeBuf = FriendlyByteBuf(Unpooled.buffer())
                            val level = playerNMS.level()
                            val chunk = level.getChunk(packet.x, packet.z)
                            writeBuf.writeInt(chunk.locX)
                            writeBuf.writeInt(chunk.locZ)
                            val sections = chunk.sections
                            var primaryBitMask = 0
                            for (i in sections.indices) {
                                val section = sections[i]
                                if (section.hasOnlyAir()) continue
                                val readBuf = FriendlyByteBuf(Unpooled.buffer())
                                section.states.write(readBuf, null, i)
                                primaryBitMask = primaryBitMask or (1 shl i)
                            }
                            writeBuf.writeVarInt(primaryBitMask)

                        }

                        // writes the rest of the packet instead of reimplementing everything
                        super.write(ctx, packet, promise)mmmmmmmmm
                    }
                }
            )
        } catch (_: ChannelPipelineException) {
            println("Better than Sniffers attempted to re-register packet interceptor for ClientboundLevelChunkWithLightPacket, ignoring")
        }
    }

}