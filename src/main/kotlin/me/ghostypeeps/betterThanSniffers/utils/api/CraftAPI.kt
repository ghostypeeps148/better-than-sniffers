package me.ghostypeeps.betterThanSniffers.utils.api

import me.ghostypeeps.betterThanSniffers.utils.Items
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

/**
 * Creates GUIs for Better Than Sniffers.
 */
object CraftAPI : InventoryHolder{
    const val NUMBER_SLOTS = 9;
    private var inventory: Inventory = Bukkit.createInventory(this, NUMBER_SLOTS);
    val BUCKETS = hashSetOf(Material.WATER_BUCKET, Material.LAVA_BUCKET)
    // exists solely for the purpose of satisfying the interface
    override fun getInventory(): Inventory {
        return inventory;
    }
    fun of(slots : Array<ItemStack?>) : Inventory {
        require(slots.size == NUMBER_SLOTS) {"Must have 9 slots."}
        inventory.contents = slots
        return inventory
    }

    fun handleClick(event: InventoryClickEvent, clickedInventory: Inventory, slot: Int) {
        val item = clickedInventory.getItem(slot)
        val held = event.currentItem
        if (Items.GUI_TEST_BLANK.equals(item) || Items.GUI_TEST_ARROW.equals(item)) {
            event.isCancelled
        } else if (Items.GUI_INPUT_LIQUID.equals(item)){
            if (held == null) {
                event.isCancelled
            } else {
                if (BUCKETS.contains(held.type)) {

                }
            }
        }
    }

}