package me.ghostypeeps.betterThanSniffers

import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import me.ghostypeeps.betterThanSniffers.block.Blocks

/**
 * Runs before the server starts, so I can modify the registries.
 */
@SuppressWarnings("UnstableApiUsage")
class SnifferBootstrap : PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {
        Blocks.init()
    }
}