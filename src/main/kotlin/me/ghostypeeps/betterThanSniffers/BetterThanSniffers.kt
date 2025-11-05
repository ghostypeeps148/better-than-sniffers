package me.ghostypeeps.betterThanSniffers

import me.ghostypeeps.betterThanSniffers.commands.Serialize
import me.ghostypeeps.betterThanSniffers.listeners.PlayerBrokeBlock
import me.ghostypeeps.betterThanSniffers.listeners.PlayerKnapping
import me.ghostypeeps.betterThanSniffers.utils.CustomRecipeHandler
import me.ghostypeeps.betterThanSniffers.utils.Items
import me.ghostypeeps.betterThanSniffers.utils.ResourcePackCreationTest
import me.ghostypeeps.betterThanSniffers.utils.api.CraftAPI
import me.ghostypeeps.betterThanSniffers.utils.api.CraftStationSlotType
import org.bukkit.craftbukkit.generator.CustomChunkGenerator
import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.java.JavaPlugin


// First Plugin, so may not be super well-made.

/*
MIT License

Copyright (c) 2025 Ghostypeeps

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

// todo: credit tfc textures (replace tfc textures) (update: kinda solved but just replaced them with placeholders)
// todo: broken formatting in some classes
class BetterThanSniffers : JavaPlugin() {
    override fun onEnable() {
        dataFolder.mkdirs()
        getCommand("better-than-sniffers")!!.setExecutor(Serialize())

        Items.init()

        registerListeners()
        CustomRecipeHandler.registerRecipes()

        ResourcePackCreationTest.webServer()

    }

    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator {
        return SnifferWorldGenerator()
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(PlayerKnapping(), this)
        // server.pluginManager.registerEvents(PlayerJoined(), this)
        server.pluginManager.registerEvents(PlayerBrokeBlock(), this)
        // server.pluginManager.registerEvents(PlayerInventoryClick(), this)
    }
    override fun onDisable() {
        logger.info("Shutting down Better than Sniffers...")
    }
}
