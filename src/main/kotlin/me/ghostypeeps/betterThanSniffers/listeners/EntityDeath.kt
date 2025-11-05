package me.ghostypeeps.betterThanSniffers.listeners



import me.ghostypeeps.betterThanSniffers.utils.Items
import org.bukkit.entity.Player
import org.bukkit.entity.Squid
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDeathEvent
import kotlin.random.Random

class EntityDeath : Listener {

    @EventHandler
    fun death(event: EntityDeathEvent) {
       if (event.entity is Squid) {
            event.drops.add(Items.CALAMARI.asItemStack(2))
       }
    }
}