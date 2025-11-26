package me.ghostypeeps.betterThanSniffers.listeners

import me.ghostypeeps.betterThanSniffers.gui.CraftAPI
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
class PlayerInventoryClick : Listener {

    private var pouch : Inventory = Bukkit.createInventory(null, 27, Component.text("Pouch"));

    @EventHandler
    fun click(event: InventoryClickEvent) {
        if (event.clickedInventory != null && event.clickedInventory?.holder == CraftAPI) {
            CraftAPI.handleClick(event, event.clickedInventory!!, event.slot)
        }
    }
}


