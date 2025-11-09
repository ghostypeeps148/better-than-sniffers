package me.ghostypeeps.betterThanSniffers.utils

import me.ghostypeeps.betterThanSniffers.utils.Items
import me.ghostypeeps.betterThanSniffers.BetterThanSniffers
import me.ghostypeeps.betterThanSniffers.SnifferUtil.SNIFFER_PLUGIN
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Tag
import org.bukkit.inventory.*
import org.bukkit.plugin.java.JavaPlugin.getPlugin

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

            registerShapedRecipe("compacting_loose_stone",arrayOf("AA","AA"), ItemStack(Material.COBBLESTONE_SLAB), Items.LOOSE_STONE.asItemStack())
            registerShapelessRecipe("cobblestone_slabs_combining", listOf(
                ItemStack(Material.COBBLESTONE_SLAB),
                ItemStack(Material.COBBLESTONE_SLAB)
            ), ItemStack(Material.COBBLESTONE))
            registerShapelessRecipe("rocks", listOf(
                ItemStack(Items.LOOSE_STONE.asItemStack()),
                ItemStack(Items.LOOSE_STONE.asItemStack()),
                ItemStack(Items.LOOSE_STONE.asItemStack()),
                ItemStack(Items.LOOSE_STONE.asItemStack()),
                ItemStack(Items.LOOSE_STONE.asItemStack()),
                ItemStack(Items.LOOSE_STONE.asItemStack()),
                ItemStack(Items.LOOSE_STONE.asItemStack()),
                ItemStack(Items.LOOSE_STONE.asItemStack()),
                ItemStack(Items.LOOSE_STONE.asItemStack())
            ), ItemStack(Material.COBBLESTONE_SLAB))
            registerShapelessRecipe("tanned_leather",listOf(
                ItemStack(Material.PACKED_MUD),
                ItemStack(Material.LEATHER),
            ), Items.TANNED_LEATHER.asItemStack())
            registerShapelessRecipe("reinforced_stick",listOf(
                Items.TANNED_LEATHER.asItemStack(),
                ItemStack(Material.STICK),
                ItemStack(Material.STICK),
                ItemStack(Material.STICK)
            ), Items.REINFORCED_STICK.asItemStack())
            registerCampfireRecipe("clay_to_bricks", ItemStack(Material.BRICK), ItemStack.of(Material.CLAY_BALL), 0.1f, 2400)
            registerCampfireRecipe("calamari", Items.COOKED_CALAMARI.asItemStack(), Items.CALAMARI.asItemStack(), 0.1f, 2400)
            registerShapedRecipe("axe_cast",arrayOf("AA","A "), Items.AXE_CAST.asItemStack(), ItemStack(Material.BRICK))
            registerShapedRecipe("hoe_cast",arrayOf("AA"),  Items.HOE_CAST.asItemStack(), ItemStack(Material.BRICK))
            registerShapedRecipe("pickaxe_cast",arrayOf("AAA"),  Items.PICKAXE_CAST.asItemStack(), ItemStack(Material.BRICK))
            registerShapedRecipe("shovel_cast",arrayOf("A"),  Items.SHOVEL_CAST.asItemStack(), ItemStack(Material.BRICK))
            registerShapedRecipe("sword_cast",arrayOf("A","A"), Items.SWORD_CAST.asItemStack(), ItemStack(Material.BRICK))
            registerShapelessRecipe("copper_axe", listOf(Items.AXE_CAST.asItemStack(), ItemStack(Material.COPPER_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.STONE_AXE))
            registerShapelessRecipe("copper_hoe", listOf(Items.HOE_CAST.asItemStack(), ItemStack(Material.COPPER_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.STONE_HOE))
            registerShapelessRecipe("copper_pickaxe", listOf(Items.PICKAXE_CAST.asItemStack(), ItemStack(Material.COPPER_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.STONE_PICKAXE))
            registerShapelessRecipe("copper_shovel", listOf(Items.SHOVEL_CAST.asItemStack(), ItemStack(Material.COPPER_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.STONE_SHOVEL))
            registerShapelessRecipe("copper_sword", listOf(Items.SWORD_CAST.asItemStack(), ItemStack(Material.COPPER_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.STONE_SWORD))
            registerShapelessRecipe("iron_axe", listOf(Items.AXE_CAST.asItemStack(), ItemStack(Material.IRON_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.IRON_AXE))
            registerShapelessRecipe("iron_hoe", listOf(Items.HOE_CAST.asItemStack(), ItemStack(Material.IRON_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.IRON_HOE))
            registerShapelessRecipe("iron_pickaxe", listOf(Items.PICKAXE_CAST.asItemStack(), ItemStack(Material.IRON_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.IRON_PICKAXE))
            registerShapelessRecipe("iron_shovel", listOf(Items.SHOVEL_CAST.asItemStack(), ItemStack(Material.IRON_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.IRON_SHOVEL))
            registerShapelessRecipe("iron_sword", listOf(Items.SWORD_CAST.asItemStack(), ItemStack(Material.IRON_INGOT), Items.REINFORCED_STICK.asItemStack()), ItemStack(Material.IRON_SWORD))
            registerShapedRecipe("smooth_stone",arrayOf("AA","AA"), ItemStack(Material.SMOOTH_STONE, 4), ItemStack(Material.COBBLESTONE))
            registerShapedRecipe("smithing_table",arrayOf("AA","BB","BB"), ItemStack(Material.SMITHING_TABLE), ItemStack(Material.SMOOTH_STONE), RecipeChoice.MaterialChoice(Tag.LOGS).itemStack);
            registerShapedRecipe("furnace",arrayOf("AAA","A A","AAA"), ItemStack(Material.FURNACE), ItemStack(Material.BRICKS));
            registerShapedRecipe("blast_furnace",arrayOf("AAA","ABA","CCC"), ItemStack(Material.BLAST_FURNACE), ItemStack(Material.SMOOTH_STONE), ItemStack(Material.DIAMOND),
                ItemStack(Material.COPPER_BLOCK));
            if (Trains.isTrains()) {
                registerShapedRecipe("i_like_trains", arrayOf("AAB","CAB"," CA"), Items.TRAIN.asItemStack(), ItemStack(Material.COBBLESTONE), ItemStack(Material.DIRT),ItemStack(Material.STICK))
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