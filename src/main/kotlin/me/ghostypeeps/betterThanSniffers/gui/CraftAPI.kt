package me.ghostypeeps.betterThanSniffers.gui

import me.ghostypeeps.betterThanSniffers.item.Items
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType

/**
 * Creates GUIs for Better Than Sniffers.
 */
object CraftAPI : InventoryHolder {
    const val NUMBER_SLOTS = 9;
    private var inventory: Inventory = Bukkit.createInventory(this, NUMBER_SLOTS);
    val BUCKETS : HashSet<ItemStack> = hashSetOf(ItemType.WATER_BUCKET.createItemStack(), ItemType.LAVA_BUCKET.createItemStack())
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
            event.isCancelled = true
        } else if (Items.GUI_INPUT_LIQUID.equals(item)){
            if (held == null) {
                event.isCancelled = true
            } else {
                if (BUCKETS.contains(held.asOne())) {

                }
            }
        }
    }

}