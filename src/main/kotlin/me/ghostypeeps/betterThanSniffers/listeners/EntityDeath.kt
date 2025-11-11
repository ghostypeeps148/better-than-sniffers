package me.ghostypeeps.betterThanSniffers.listeners



import me.ghostypeeps.betterThanSniffers.item.Items
import org.bukkit.entity.Squid
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

/**
 * Runs when an entity dies. Yeah thats it.
 */
class EntityDeath : Listener {

    @EventHandler
    fun death(event: EntityDeathEvent) {
       if (event.entity is Squid) {
            event.drops.add(Items.CALAMARI.asItemStack(2))
       }
    }
}