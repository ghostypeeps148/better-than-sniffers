package me.ghostypeeps.betterThanSniffers.listeners


import me.ghostypeeps.betterThanSniffers.block.BlockPacketFix
import org.bukkit.attribute.Attribute
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent


class PlayerJoined : Listener {
    @EventHandler
    fun setAttributes(event: PlayerJoinEvent) {
        val player = event.player
        player.getAttribute(Attribute.MAX_HEALTH)!!.baseValue = 10.0;
    }
}