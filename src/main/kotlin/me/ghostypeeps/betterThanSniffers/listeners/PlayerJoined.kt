package me.ghostypeeps.betterthansniffers.listeners


import io.netty.channel.Channel
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import me.ghostypeeps.betterThanSniffers.block.BlockPacketFix
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
        BlockPacketFix.interceptChunkData((player as CraftPlayer).handle)
    }

}