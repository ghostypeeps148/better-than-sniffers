package me.ghostypeeps.betterThanSniffers.listeners


import com.destroystokyo.paper.MaterialTags
import me.ghostypeeps.betterThanSniffers.item.Items
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class PlayerKnapping : Listener {
    @EventHandler
    fun rightClickHandler(event: PlayerInteractEvent) {
        if ((event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR) && event.item == Items.TRAIN.asItemStack()) {
            event.player.sendMessage("i like trains")
        }
        if (event.action == Action.RIGHT_CLICK_BLOCK && event.item == Items.SAW.asItemStack()) {
            //event.clickedBlock == ItemType.STONE_STAIRS
        }
        if (event.clickedBlock == null) {
            return
        }
        val block = event.clickedBlock!!
    }
}