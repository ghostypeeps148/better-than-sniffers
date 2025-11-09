package me.ghostypeeps.betterThanSniffers.block

import me.ghostypeeps.betterThanSniffers.SnifferUtil
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

/**
 * Creates blocks by adding them to the net.minecraft.server Block registry.
 */
object Blocks {
    /**
     * This is where the blocks are initialized.
     */
    lateinit var test: Block;
    /**
     * This function is called from SnifferBootstrap, before the server freezes the block registry, so we can add it before the server stops allowing that.
     */
    fun init() {
        val key = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("btsniffers", "test"))
        test = Block(BlockBehaviour.Properties.of().strength(0.5F, 0.6F).setId(key))
        Registry.register(BuiltInRegistries.BLOCK, key, test)
        println("This Code Is Running1!!!!!!!")
    }

    /**
     * test sends a fake oak block to the client
     */
    fun testSendFakeBlock(player: Player, x: Int, y: Int, z: Int) {
        val nmsPlayer = (player as CraftPlayer).handle
        val pos = BlockPos(x, y, z)
        val packet = ClientboundBlockUpdatePacket(pos, Blocks.OAK_PLANKS.defaultBlockState())
        nmsPlayer.connection.send(packet)
    }
}