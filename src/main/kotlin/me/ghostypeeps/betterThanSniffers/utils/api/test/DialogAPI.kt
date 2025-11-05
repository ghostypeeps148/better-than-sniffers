package me.ghostypeeps.betterThanSniffers.utils.api.test


/**
 * Sends Dialogs to Java Clients and a JSONUI instance to Bedrock clients.
 */
/*
@SuppressWarnings("ApiStatus.Experimental")
class DialogAPI {
    fun DualButtonModal(player: Player, title: String, message: String, string: String, function: Consumer) {
        // the fact that player.player?.player?.player?.player?.player?.uniqueId!! is valid code is so funny to me
        val uuid = player.uniqueId;
        if (GeyserApi.api().isBedrockPlayer(uuid)) {
            // Bedrock Player; send ActionForm
            GeyserApi.api().sendForm(uuid, ModalForm.builder().title(title).content(message).button1(string).button2("Back").validResultHandler {}.build())
        } else {
            // Java Player; send Conformation dialog
            val dialog =
                // java why the fridge do you need to make this so frigging complicated. LOOK AT THE DAMMIT BEDROCK CODE!
                Dialog.create(Consumer { builder: RegistryBuilderFactory<Dialog, out DialogRegistryEntry.Builder>, ->
                    builder!!.empty()
                        .base(DialogBase.builder(Component.text("Title")).canCloseWithEscape(true).build())
                        .type(DialogType.confirmation(ActionButton.builder(Component.text(string)).action(DialogAction.customClick(
                            NamespacedKey(getPlugin(BetterThanSniffers::class.java), "clickaction"), null)).build(), .))))
                }
                )
            player.showDialog(dialog)
        }

    }
}
*/