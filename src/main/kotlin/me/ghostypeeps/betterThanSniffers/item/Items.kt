package me.ghostypeeps.betterThanSniffers.item

import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemType

/**
 * Registers items and works like the Block class, but more polished.
 */
object Items {
    lateinit var COPPER_HOE: Item;
    lateinit var COPPER_SHOVEL: Item;
    lateinit var COPPER_SWORD: Item
    lateinit var COPPER_AXE: Item
    lateinit var LOOSE_STONE: Item;
    lateinit var TANNED_LEATHER : Item;
    lateinit var REINFORCED_STICK : Item;
    lateinit var SHOVEL_CAST : Item;
    lateinit var AXE_CAST : Item;
    lateinit var HOE_CAST : Item;
    lateinit var SWORD_CAST : Item;
    lateinit var BOILED_POTATO : Item;
    lateinit var COOKED_CARROT : Item;
    lateinit var STEAMED_BEETROOT : Item;
    lateinit var WOOL : Item;
    lateinit var COOKED_CALAMARI : Item;
    lateinit var POUCH : Item;
    lateinit var CALAMARI : Item;
    lateinit var PICKAXE_CAST : Item;
    lateinit var SULFUR : Item;
    lateinit var LATEX : Item;
    lateinit var CURED_RUBBER : Item;
    lateinit var TRAIN : Item;
    lateinit var SAW : Item;
    lateinit var COPPER_PICKAXE : Item;
    lateinit var DIAMOND_INGOT: Item;
    lateinit var GUI_TEST_BLANK: Item;
    lateinit var GUI_TEST_ARROW: Item;
    lateinit var GUI_INPUT_LIQUID: Item;

    fun init() {
        LOOSE_STONE = Item("Loose Stone", "loose_stone").setTexture("item/nature/stone")
        TANNED_LEATHER = Item("Tanned Leather", "tanned_leather").setTexture("item/basic/tanned_leather")
        REINFORCED_STICK = Item("Advanced Tool Rod", "adv_tool_rod").setLore(
            "A better tool rod for better tools."
        ).setTexture("item/metal/haft")
        SHOVEL_CAST = Item("Shovel Cast", "shovel_cast")
        AXE_CAST = Item("Axe Cast", "axe_cast")
        HOE_CAST = Item("Hoe Cast", "hoe_cast")
        SWORD_CAST = Item("Sword Cast", "sword_cast")
        BOILED_POTATO = Item("Boiled Potato", "boiled_potato").setBaseMaterial(ItemType.COOKED_COD)
        COOKED_CARROT = Item("Cooked Carrot", "cooked_carrot").setBaseMaterial(ItemType.COOKED_COD)
        STEAMED_BEETROOT = Item("Steamed Beetroot", "steamed_beetroot").setBaseMaterial(ItemType.COOKED_COD)
        WOOL = Item("Wool", "wool_item")
        COOKED_CALAMARI = Item("Cooked Calamari", "c_calamari").setBaseMaterial(ItemType.COOKED_COD)
        POUCH = Item("Pouch", "pouch")
        CALAMARI = Item("Calamari", "calamari").setBaseMaterial(ItemType.PUFFERFISH)
        PICKAXE_CAST = Item("Pickaxe Cast", "pickaxe_cast")
        SULFUR = Item("Brimstone", "sulfur").setTexture("item/metal/brimstone")
        LATEX = Item("Latex", "latex")
        CURED_RUBBER = Item("Rubber", "rubber")
        TRAIN = Item(
            "Horribly Cropped 4K Resolution Unnecessarily Ultrahyperrealistic Supercalifragalisticexpaliadocious Train",
            "haha_train_go_brr"
        ).setTexture("item/amogus/sus/train")
            .setLore("I like trains. NYYYYYYYYYYYYYYYYYYYYYYYYRRRRRRRRRRRR-")
            .setRarity(ItemRarity.RARE)
        SAW = Item("Hammer", "hammer").setTexture("item/metal/iron_chisel")
        COPPER_PICKAXE = Item("Copper Pickaxe", "copper_pickaxe").setBaseMaterial(ItemType.STONE_PICKAXE)
        COPPER_AXE = Item("Copper Axe", "copper_axe").setBaseMaterial(ItemType.STONE_AXE)
        COPPER_SWORD = Item("Copper Sword", "copper_sword").setBaseMaterial(ItemType.STONE_SWORD)
        COPPER_HOE = Item("Copper Hoe", "copper_hoe").setBaseMaterial(ItemType.STONE_HOE)
        COPPER_SHOVEL = Item("Copper Shovel", "copper_shovel").setBaseMaterial(ItemType.STONE_SHOVEL)
        DIAMOND_INGOT = Item("Diamond Ingot", "diamond_ingot").setTexture("item/metal/diamond_ingot")
        GUI_TEST_ARROW = Item("",  "gui_arrow")
        GUI_TEST_BLANK = Item("", "gui_blank")
        GUI_INPUT_LIQUID = Item("", "gui_empty_liquid")
    }
}