package me.ghostypeeps.betterThanSniffers.block

import io.netty.channel.Channel
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.network.ServerGamePacketListenerImpl
import java.lang.reflect.Field

object BlockPacketFix {
    fun interceptChunkData(player: ServerPlayer) {
        val connection: ServerGamePacketListenerImpl = player.connection
        try {
            // Get the Netty channel
            // note for me: this code is a snippet that gets the private field ServerGamePacketListenerImpl::Channel since it is private
            // i love ruining Minecraft a lot
            val channelField: Field = ServerGamePacketListenerImpl::class.java.getDeclaredField("channel")
            channelField.setAccessible(true)
            val channel: Channel = channelField.get(connection) as Channel

            // Inject a custom handler before the default packet handler
            // note for me 2: this takes a object implementing ChannelDuplexHandler and injects it.
            channel.pipeline().addBefore("packet_handler", "custom_chunk_listener", PacketInterceptor)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
    private object PacketInterceptor : ChannelDuplexHandler() {
        override fun write(ctx: ChannelHandlerContext, msg: Any, promise: ChannelPromise?) {
            // intercepts with better than sniffers' code
            if (msg is ClientboundLevelChunkWithLightPacket) {
                println("Intercepted ClientboundLevelChunkWithLightPacket")
            }
            // writes the rest of the packet instead of reimplementing everything
            super.write(ctx, msg, promise)
        }
    }
}