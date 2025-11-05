package me.ghostypeeps.betterThanSniffers.listeners.geyser

import org.geysermc.event.subscribe.Subscribe
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent
import org.geysermc.geyser.api.event.lifecycle.GeyserDefineCustomSkullsEvent.SkullTextureType
import org.geysermc.geyser.api.extension.Extension

class RegisterCustomSkull : Extension {
    @Subscribe
    fun onDefineCustomSkulls(event : GeyserDefineCustomSkullsEvent ) {
        event.register("test", SkullTextureType.SKIN_HASH);
    }
}