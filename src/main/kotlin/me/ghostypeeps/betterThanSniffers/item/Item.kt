package me.ghostypeeps.betterThanSniffers.item

import org.bukkit.inventory.ItemType

/**
 * Item with a custom texture or a custom base Minecraft item.
 */
open class Item(name: String, index: String) : ItemLike<Item>(name, index) {
    fun setBaseMaterial(baseMaterial: ItemType) : Item {
        this.item = baseMaterial.createItemStack()
        return this
    }
}