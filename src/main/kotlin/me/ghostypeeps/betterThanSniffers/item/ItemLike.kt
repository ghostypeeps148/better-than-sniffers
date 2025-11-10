package me.ghostypeeps.betterThanSniffers.item

import me.ghostypeeps.betterThanSniffers.util.ResourcePackCreationTest
import me.ghostypeeps.betterThanSniffers.util.SnifferUtil.SNIFFER_ID
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType
import java.io.File
import java.net.URI
import java.nio.charset.StandardCharsets
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * An abstract that is used for all item like objects, ex: blocks, skulls, gui buttons, regular items
 * @param name
 * Friendly name of the ItemLike
 * @param index
 * Unfriendly name of the ItemLike
 */
abstract class ItemLike<T : ItemLike<T>>(name: String,
                        val index: String)
{
    protected open var item : ItemStack = ItemStack.of(Material.PRISMARINE_CRYSTALS);
    protected val customItemKey = NamespacedKey(SNIFFER_ID, "item_$index")

    init {
        val meta : ItemMeta = item.itemMeta
        meta.itemName(Component.text(name).decoration(TextDecoration.ITALIC, false))
        item.itemMeta = meta
    }
    fun setLore(tooltip: Component): T {
        val lore = ArrayList<Component>()
        lore.add(tooltip.decorate(TextDecoration.ITALIC).color(TextColor.color(127,127,127)))
        item.itemMeta.lore(lore)
        return this as T;
    }
    fun setLore(tooltip: String): T {
        return setLore(Component.text(tooltip));
    }
    fun setRarity(rarity: ItemRarity): T {
        item.itemMeta.setRarity(rarity)
        return this as T;
    }
    fun equals(other: T): Boolean {
        return this.index == other.index
    }
    fun equals(other: ItemStack): Boolean {
        return this.customItemKey == other.itemMeta.persistentDataContainer
    }
    fun asItemStack() : ItemStack {
        return this.item.asQuantity(1)
    }
    fun asItemStack(amount : Int) : ItemStack {
        return this.item.asQuantity(amount)
    }
    open fun setTexture(tex: String): T {
        val file = File(ResourcePackCreationTest.DESTINATION)
        if (!file.exists()) {
            println("Pack missing, creating pack...")
            ResourcePackCreationTest.createPack()
        }
        val meta = item.itemMeta
        meta.persistentDataContainer.set(customItemKey, PersistentDataType.BOOLEAN, true)
        meta.itemModel = customItemKey
        item.itemMeta = meta
        // todo: i copy and pasted this from stack overflow and i dont know how it works but it does so i should probably understand that
        val env: HashMap<String, String> = HashMap()
        env["create"] = "true"
        // generates a path where the zip file system should be created as a URI
        val uri: URI? = URI.create("jar:" + Paths.get(ResourcePackCreationTest.DESTINATION).toUri())
        // creates the zip file sys
        FileSystems.newFileSystem(uri, env).use { sys ->
            // add an entry for the item definition path
            val pathToItemDefinition: Path = sys.getPath("assets/btsniffers/items/item_$index.json")
            // add intermediatory directories because otherwise it gets angy and errors
            Files.createDirectories(pathToItemDefinition.parent)
            Files.newBufferedWriter(pathToItemDefinition,
                /* at least I think it's UTF 8 im not sure */
                StandardCharsets.UTF_8, StandardOpenOption.CREATE).use { writer ->
                writer.write("""
{
    "model": {
        "type": "minecraft:model",
        "model": "btsniffers:item/item_$index"
    }
}
                    """)
                writer.close()
            }
            // repeat for model definition
            val pathToModelDefinition: Path = sys.getPath("assets/btsniffers/models/item/item_$index.json")
            Files.createDirectories(pathToModelDefinition.parent);
            Files.newBufferedWriter(pathToModelDefinition,
                StandardCharsets.UTF_8, StandardOpenOption.CREATE).use { writer ->
                writer.write("""
{
    "parent": "minecraft:item/generated",
    "textures": {
        "layer0": "btsniffers:$tex"
    }                     
}
                    """)
                writer.close()
            }
            sys.close()
        }
        return this as T;
    }
}