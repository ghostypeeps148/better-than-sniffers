package me.ghostypeeps.betterThanSniffers.utils.api
import me.ghostypeeps.betterThanSniffers.BetterThanSniffers
import me.ghostypeeps.betterThanSniffers.utils.ResourcePackCreationTest
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
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
 * Item with a custom texture or a custom base Minecraft item.
 */
open class Item(name: String, index: String) : ItemLike<Item>(name, index) {
    fun setBaseMaterial(baseMaterial: Material) : Item {
        this.item = ItemStack.of(baseMaterial)
        return this
    }
}