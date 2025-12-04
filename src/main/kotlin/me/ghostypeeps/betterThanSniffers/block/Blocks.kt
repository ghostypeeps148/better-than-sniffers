package me.ghostypeeps.betterThanSniffers.block

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour


/**
 * Creates blocks by adding them to the net.minecraft.server Block registry.
 */
object Blocks {
    /**
     * This is where the blocks are initialized.
     */
    lateinit var test: Block;

    /**
     * Registry for all block registered by Better Than Sniffers
     */
    var snifferRegistry: HashSet<Block> = hashSetOf()

    /**
     * This function is called from SnifferBootstrap, before the server freezes the block registry, so we can add it before the server stops allowing that.
     */
    fun init() {
        // Register all the vanilla blocks first
        Blocks()
        // Register test block
        // Pretty simple, most mod creation docs should cover this
        val key = ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("btsniffers", "test"))
        test = Block(BlockBehaviour.Properties.of().strength(0.5F, 0.6F).setId(key))
        Registry.register(BuiltInRegistries.BLOCK, key, test)
        snifferRegistry.add(test)
        // Make sure the block states are actually in the block state registry
        // I think this could be async, but I am probably wrong
        for (block in snifferRegistry) {
            for (state in block.stateDefinition.possibleStates) {
                Block.BLOCK_STATE_REGISTRY.add(state)
                state.initCache()
            }
        }
    }
}