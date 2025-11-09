package me.ghostypeeps.betterThanSniffers.listeners


import me.ghostypeeps.betterThanSniffers.utils.Items
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

/**
 * Runs when players break blocks.
 */
class PlayerBrokeBlock : Listener {
    @EventHandler
    fun breakHandler(event: BlockBreakEvent) {
        val block = event.block
        /**
         * Switch case to check which blocks the player broke.
         */
        when (block.type) {
            Material.STONE -> {
                if (event.player.inventory.itemInMainHand.type == Material.WOODEN_PICKAXE) {
                    event.isCancelled = true;
                    block.world.dropItemNaturally(block.location, Items.LOOSE_STONE.asItemStack())
                    block.type = Material.COBBLESTONE
                }
            }
            Material.COBBLESTONE -> {
                if (event.player.inventory.itemInMainHand.type == Material.WOODEN_PICKAXE) {
                    block.world.dropItemNaturally(block.location, Items.LOOSE_STONE.asItemStack())
                    event.isDropItems = false;
                }
            }
            Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE -> {
                if (event.player.inventory.itemInMainHand.type == Material.WOODEN_PICKAXE) {
                    event.isCancelled = true;
                    block.world.dropItemNaturally(block.location, ItemStack(Material.RAW_COPPER))
                    block.type = Material.COBBLESTONE
                }
            }
            Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE -> {
                if (event.player.inventory.itemInMainHand.type == Material.WOODEN_PICKAXE) {
                    event.isCancelled = true;
                    block.world.dropItemNaturally(block.location, ItemStack(Material.COAL))
                    block.type = Material.COBBLESTONE
                }
            }
            Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE -> {
                if (event.player.inventory.itemInMainHand.type == Material.STONE_PICKAXE) {
                    event.isCancelled = true;
                    block.world.dropItemNaturally(block.location, ItemStack(Material.DIAMOND))
                    block.type = Material.COBBLESTONE
                }
            }
            Material.DEEPSLATE, Material.COBBLED_DEEPSLATE -> {
                if (event.player.inventory.itemInMainHand.type == Material.STONE_PICKAXE || event.player.inventory.itemInMainHand.type == Material.WOODEN_PICKAXE) {
                    event.isCancelled = true;
                }
            }
            else -> {}
        }
    }
}