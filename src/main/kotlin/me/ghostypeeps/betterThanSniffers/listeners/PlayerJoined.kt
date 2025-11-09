package me.ghostypeeps.betterthansniffers.listeners


import io.netty.channel.Channel
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.network.ServerGamePacketListenerImpl
import org.bukkit.attribute.Attribute
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.lang.reflect.Field


class PlayerJoined : Listener {
    @EventHandler
    fun setAttributes(event: PlayerJoinEvent) {
        val player = event.player
        player.getAttribute(Attribute.MAX_HEALTH)!!.baseValue = 10.0;
        interceptChunkData((player as CraftPlayer).handle)
    }
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