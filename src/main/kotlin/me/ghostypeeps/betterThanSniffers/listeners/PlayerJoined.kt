package me.ghostypeeps.betterthansniffers.listeners


import me.ghostypeeps.betterThanSniffers.utils.api.Item
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoined : Listener {
    @EventHandler
    fun setAttributes(event: PlayerJoinEvent) {
        /*
        val player = event.player
        player.inventory.setItem(12, Item.POUCH.asItemStack())
        player.getAttribute(Attribute.MAX_HEALTH)!!.baseValue = 10.0;
         */
    }
}