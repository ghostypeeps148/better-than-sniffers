package me.ghostypeeps.betterThanSniffers.block

import com.destroystokyo.paper.profile.PlayerProfile
import com.destroystokyo.paper.profile.ProfileProperty
import me.ghostypeeps.betterThanSniffers.item.ItemLike
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.HashSet
import java.util.UUID

/**
 * Test.
 */
class BlockSmall(name: String, index: String) : ItemLike<BlockSmall>(name, index) {
    companion object {
        var add_to_geyser_heads : HashSet<String> = HashSet()
    }
    override var item = ItemStack.of(Material.PLAYER_HEAD)
    override fun setTexture(tex: String): BlockSmall {
        add_to_geyser_heads.add(tex)
        val meta = item.itemMeta
        if (meta !is SkullMeta) {
            throw IllegalArgumentException("The block is not a skull for some reason")
        }
        val uuid = UUID.randomUUID()
        val playerProfile: PlayerProfile = Bukkit.createProfile(uuid)
        playerProfile.setProperty(ProfileProperty("textures", tex))
        meta.playerProfile = playerProfile
        return this;
    }
}