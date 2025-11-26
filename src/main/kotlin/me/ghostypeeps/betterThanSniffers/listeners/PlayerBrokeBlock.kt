package me.ghostypeeps.betterThanSniffers.listeners


import me.ghostypeeps.betterThanSniffers.item.Items
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType

/**
 * Runs when players break blocks.
 */
@Deprecated("Material API")
class PlayerBrokeBlock : Listener {
    @EventHandler
    fun breakHandler(event: BlockBreakEvent) {
        val block = event.block
        /**
         * Switch case to check which blocks the player broke.
         *//*
        when (block.type) {
            ItemType.STONE -> {
                if (event.player.inventory.itemInMainHand.type == ItemType.WOODEN_PICKAXE) {
                    event.isCancelled = true;
                    block.world.dropItemNaturally(block.location, Items.LOOSE_STONE.asItemStack())
                    block.type = ItemType.COBBLESTONE
                }
            }
            ItemType.COBBLESTONE -> {
                if (event.player.inventory.itemInMainHand.type == ItemType.WOODEN_PICKAXE) {
                    block.world.dropItemNaturally(block.location, Items.LOOSE_STONE.asItemStack())
                    event.isDropItems = false;
                }
            }
            ItemType.COPPER_ORE, ItemType.DEEPSLATE_COPPER_ORE -> {
                if (event.player.inventory.itemInMainHand.type == ItemType.WOODEN_PICKAXE) {
                    event.isCancelled = true;
                    block.world.dropItemNaturally(block.location, (ItemType.RAW_COPPER))
                    block.type = ItemType.COBBLESTONE
                }
            }
            ItemType.COAL_ORE, ItemType.DEEPSLATE_COAL_ORE -> {
                if (event.player.inventory.itemInMainHand.type == ItemType.WOODEN_PICKAXE) {
                    event.isCancelled = true;
                    block.world.dropItemNaturally(block.location, (ItemType.COAL))
                    block.type = ItemType.COBBLESTONE
                }
            }
            ItemType.DIAMOND_ORE, ItemType.DEEPSLATE_DIAMOND_ORE -> {
                if (event.player.inventory.itemInMainHand.type == ItemType.STONE_PICKAXE) {
                    event.isCancelled = true;
                    block.world.dropItemNaturally(block.location, (ItemType.DIAMOND))
                    block.type = ItemType.COBBLESTONE
                }
            }
            ItemType.DEEPSLATE, ItemType.COBBLED_DEEPSLATE -> {
                if (event.player.inventory.itemInMainHand.type == ItemType.STONE_PICKAXE || event.player.inventory.itemInMainHand.type == ItemType.WOODEN_PICKAXE) {
                    event.isCancelled = true;
                }
            }
            else -> {}
        }*/
    }
}