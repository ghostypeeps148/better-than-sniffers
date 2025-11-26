package me.ghostypeeps.betterThanSniffers

import io.papermc.paper.plugin.bootstrap.BootstrapContext
import io.papermc.paper.plugin.bootstrap.PluginBootstrap
import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.LifecycleEventHandlerConfiguration
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import io.papermc.paper.registry.event.RegistryEvents
import me.ghostypeeps.betterThanSniffers.block.Blocks



/**
 * Runs before the server starts, so I can modify the registries.
 */
@SuppressWarnings("UnstableApiUsage")
@Deprecated("not neccessary rn")
class SnifferBootstrap : PluginBootstrap {
    override fun bootstrap(context: BootstrapContext) {
    }
}