package me.ghostypeeps.betterThanSniffers.util

import me.ghostypeeps.betterThanSniffers.item.Items
import me.ghostypeeps.betterThanSniffers.util.SnifferUtil.SNIFFER_PLUGIN
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Tag
import org.bukkit.craftbukkit.entity.CraftItem
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.*

// utility class
// todo: remove some more vanilla recipes i probably missed
/**
 * registers all the recipes and items in the plugin
 */
object CustomRecipeHandler {
        fun registerRecipes() {
            removeVanillaRecipe("furnace")
            removeVanillaRecipe("stone_pickaxe")
            removeVanillaRecipe("stone_sword")
            removeVanillaRecipe("stone_axe")
            removeVanillaRecipe("stone_shovel")
            removeVanillaRecipe("stone_hoe")
            removeVanillaRecipe("iron_pickaxe")
            removeVanillaRecipe("iron_sword")
            removeVanillaRecipe("iron_axe")
            removeVanillaRecipe("iron_shovel")
            removeVanillaRecipe("iron_hoe")
            removeVanillaRecipe("diamond_pickaxe")
            removeVanillaRecipe("diamond_sword")
            removeVanillaRecipe("diamond_axe")
            removeVanillaRecipe("diamond_shovel")
            removeVanillaRecipe("diamond_hoe")
            removeVanillaRecipe("chiseled_bookshelf")
            removeVanillaRecipe("blast_furnace")

            registerShapedRecipe("compacting_loose_stone",arrayOf("AA","AA"), (ItemType.COBBLESTONE_SLAB), Items.LOOSE_STONE.asItemStack())
            registerShapelessRecipe("cobblestone_slabs_combining", listOf(
                ItemType.COBBLESTONE_SLAB.createItemStack(),
                ItemType.COBBLESTONE_SLAB.createItemStack(),
                ), ItemType.COBBLESTONE.createItemStack())
            registerShapelessRecipe("rocks", listOf(
                Items.LOOSE_STONE.asItemStack(),
                Items.LOOSE_STONE.asItemStack(),
                Items.LOOSE_STONE.asItemStack(),
                Items.LOOSE_STONE.asItemStack(),
                Items.LOOSE_STONE.asItemStack(),
                Items.LOOSE_STONE.asItemStack(),
                Items.LOOSE_STONE.asItemStack(),
                Items.LOOSE_STONE.asItemStack(),
                Items.LOOSE_STONE.asItemStack()
            ), ItemType.COBBLESTONE_SLAB.createItemStack())
            registerShapelessRecipe("tanned_leather",listOf(
                ItemType.PACKED_MUD.createItemStack(),
                ItemType.LEATHER.createItemStack(),
            ), Items.TANNED_LEATHER.asItemStack())
            registerShapelessRecipe("reinforced_stick",listOf(
                Items.TANNED_LEATHER.asItemStack(),
                ItemType.STICK.createItemStack(),
                ItemType.STICK.createItemStack(),
                ItemType.STICK.createItemStack()
            ), Items.REINFORCED_STICK.asItemStack())
            registerCampfireRecipe("clay_to_bricks", ItemType.BRICK.createItemStack(), ItemType.CLAY_BALL.createItemStack(), 0.1f, 2400)
            registerCampfireRecipe("calamari", Items.COOKED_CALAMARI.asItemStack(), Items.CALAMARI.asItemStack(), 0.1f, 2400)
            registerShapedRecipe("axe_cast",arrayOf("AA","A "), Items.AXE_CAST.asItemStack(), ItemType.BRICK.createItemStack())
            registerShapedRecipe("hoe_cast",arrayOf("AA"),  Items.HOE_CAST.asItemStack(), ItemType.BRICK.createItemStack())
            registerShapedRecipe("pickaxe_cast",arrayOf("AAA"),  Items.PICKAXE_CAST.asItemStack(), (ItemType.BRICK).createItemStack())
            registerShapedRecipe("shovel_cast",arrayOf("A"),  Items.SHOVEL_CAST.asItemStack(), (ItemType.BRICK).createItemStack())
            registerShapedRecipe("sword_cast",arrayOf("A","A"), Items.SWORD_CAST.asItemStack(), (ItemType.BRICK).createItemStack())
            registerShapelessRecipe("copper_axe", listOf(Items.AXE_CAST.asItemStack(), (ItemType.COPPER_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.STONE_AXE).createItemStack())
            registerShapelessRecipe("copper_hoe", listOf(Items.HOE_CAST.asItemStack(), (ItemType.COPPER_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.STONE_HOE).createItemStack())
            registerShapelessRecipe("copper_pickaxe", listOf(Items.PICKAXE_CAST.asItemStack(), (ItemType.COPPER_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.STONE_PICKAXE).createItemStack())
            registerShapelessRecipe("copper_shovel", listOf(Items.SHOVEL_CAST.asItemStack(), (ItemType.COPPER_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.STONE_SHOVEL.createItemStack()))
            registerShapelessRecipe("copper_sword", listOf(Items.SWORD_CAST.asItemStack(), (ItemType.COPPER_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.STONE_SWORD.createItemStack()))
            registerShapelessRecipe("iron_axe", listOf(Items.AXE_CAST.asItemStack(), (ItemType.IRON_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.IRON_AXE).createItemStack())
            registerShapelessRecipe("iron_hoe", listOf(Items.HOE_CAST.asItemStack(), (ItemType.IRON_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.IRON_HOE).createItemStack())
            registerShapelessRecipe("iron_pickaxe", listOf(Items.PICKAXE_CAST.asItemStack(), (ItemType.IRON_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.IRON_PICKAXE).createItemStack())
            registerShapelessRecipe("iron_shovel", listOf(Items.SHOVEL_CAST.asItemStack(), (ItemType.IRON_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.IRON_SHOVEL).createItemStack())
            registerShapelessRecipe("iron_sword", listOf(Items.SWORD_CAST.asItemStack(), (ItemType.IRON_INGOT).createItemStack(), Items.REINFORCED_STICK.asItemStack()), (ItemType.IRON_SWORD).createItemStack())
            registerShapedRecipe("smooth_stone",arrayOf("AA","AA"), ItemType.SMOOTH_STONE.createItemStack(), ItemType.COBBLESTONE.createItemStack())
            registerShapedRecipe("smithing_table",arrayOf("AA","BB","BB"), ItemType.SMITHING_TABLE.createItemStack(), ItemType.SMOOTH_STONE.createItemStack(), ItemType.SMOOTH_STONE_SLAB.createItemStack());
            registerShapedRecipe("furnace",arrayOf("AAA","A A","AAA"), ItemType.FURNACE.createItemStack(), ItemType.BRICKS.createItemStack());
            registerShapedRecipe("blast_furnace",arrayOf("AAA","ABA","CCC"), (ItemType.BLAST_FURNACE).createItemStack(), (ItemType.SMOOTH_STONE).createItemStack(), (ItemType.DIAMOND).createItemStack(),
                (ItemType.COPPER_BLOCK).createItemStack());
            if (Trains.isTrains()) {
                registerShapedRecipe("i_like_trains", arrayOf("AAB","CAB"," CA"), Items.TRAIN.asItemStack(), (ItemType.COBBLESTONE.createItemStack()), (ItemType.DIRT).createItemStack(),(ItemType.STICK.createItemStack()))
            }
        }

        private fun registerCampfireRecipe(id : String, result : ItemStack, input : ItemStack, exp : Float, cookingTime : Int) {
            val key = NamespacedKey(SNIFFER_PLUGIN, id)
            val r = CampfireRecipe(key, result, RecipeChoice.ExactChoice(input), exp, cookingTime)
            Bukkit.getServer().addRecipe(r)
        }

        private fun registerSmithingRecipe(id : String, result : ItemStack, template: ItemStack, base: ItemStack, addition: ItemStack) {
            val key = NamespacedKey(SNIFFER_PLUGIN, id)
            val r = SmithingTransformRecipe(key, result, RecipeChoice.ExactChoice(template), RecipeChoice.ExactChoice(base), RecipeChoice.ExactChoice(addition))
            Bukkit.getServer().addRecipe(r)
        }

        private fun registerShapedRecipe(id : String,shape : Array<String>, result : ItemStack, key1 : ItemStack, key2 : ItemStack? = null, key3 : ItemStack? = null) {
            val key = NamespacedKey(SNIFFER_PLUGIN, id)
            val r = ShapedRecipe(key, result)
            r.shape(*shape)
            r.setIngredient('A', key1);
            key2?.let {r.setIngredient('B', key2)}
            key3?.let {r.setIngredient('C', key3)}
            Bukkit.getServer().addRecipe(r)
        }
        private fun removeVanillaRecipe(id : String) {
            Bukkit.getServer().removeRecipe(NamespacedKey(NamespacedKey.MINECRAFT,id))
        }
        private fun registerFurnaceRecipe(id : String, result: ItemStack, input: Material) {
            val key = NamespacedKey(SNIFFER_PLUGIN, id)
            val r = FurnaceRecipe(key, result, input, 0.5F, 200);
            Bukkit.getServer().addRecipe(r)
        }
        private fun registerShapelessRecipe(id : String, items : List<ItemStack>,  result : ItemStack) {
            val key = NamespacedKey(SNIFFER_PLUGIN, id)
            val r = ShapelessRecipe(key, result)
            for (item in items) {r.addIngredient(item)}
            Bukkit.getServer().addRecipe(r)
        }
}