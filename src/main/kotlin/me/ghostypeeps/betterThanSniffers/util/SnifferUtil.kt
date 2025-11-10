package me.ghostypeeps.betterThanSniffers.util

import me.ghostypeeps.betterThanSniffers.BetterThanSniffers
import net.minecraft.server.MinecraftServer
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.plugin.java.JavaPlugin

/**
 * Util.
 */
object SnifferUtil {
    val SERVER : MinecraftServer = (Bukkit.getServer() as CraftServer).server
    val SNIFFER_PLUGIN = JavaPlugin.getPlugin(BetterThanSniffers::class.java)
    const val SNIFFER_ID = "btsniffers"
}