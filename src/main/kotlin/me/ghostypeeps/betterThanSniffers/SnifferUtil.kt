package me.ghostypeeps.betterThanSniffers

import me.ghostypeeps.betterThanSniffers.block.Blocks.test
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.world.level.block.Block
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util.
 */
object SnifferUtil {
    val SERVER : MinecraftServer = (Bukkit.getServer() as CraftServer).server
    val SNIFFER_PLUGIN = JavaPlugin.getPlugin(BetterThanSniffers::class.java)
}