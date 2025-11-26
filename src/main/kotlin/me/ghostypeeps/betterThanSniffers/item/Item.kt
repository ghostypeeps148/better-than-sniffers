package me.ghostypeeps.betterThanSniffers.item

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Item with a custom texture or a custom base Minecraft item.
 */
open class Item(name: String, index: String) : ItemLike<Item>(name, index) {
    fun setBaseMaterial(baseMaterial: Material) : Item {
        this.item = (baseMaterial)
        return this
    }
}