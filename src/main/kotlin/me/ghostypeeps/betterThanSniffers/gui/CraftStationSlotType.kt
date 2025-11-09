package me.ghostypeeps.betterThanSniffers.gui

import me.ghostypeeps.betterThanSniffers.item.Items
import org.bukkit.inventory.ItemStack

enum class CraftStationSlotType (val item: ItemStack?) {
    INPUT(null),
    INPUT_LIQ(Items.GUI_INPUT_LIQUID.asItemStack()),
    EMPTY(Items.GUI_TEST_BLANK.asItemStack()),
    ARROW(Items.GUI_TEST_ARROW.asItemStack()),
    OUTPUT(null),
    OUTPUT_LIQ(Items.GUI_INPUT_LIQUID.asItemStack());
}