package me.ghostypeeps.betterThanSniffers.commands

import me.ghostypeeps.betterThanSniffers.block.Blocks
import me.ghostypeeps.betterThanSniffers.gui.CraftAPI
import me.ghostypeeps.betterThanSniffers.gui.CraftStationSlotType
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import kotlin.math.roundToInt

/**
 * Command code that I need to fix since I made a Paper plugin.
 */
class Serialize : CommandExecutor {
    @OptIn(ExperimentalStdlibApi::class)
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Only players can run this command.")
            return false;
        }
        if (args[0] == "debug") {
            if (args[1] == "serialize") {
                val clip = Toolkit.getDefaultToolkit().systemClipboard
                clip.setContents(
                    StringSelection(sender.inventory.itemInMainHand.serializeAsBytes().toHexString()),
                    null
                )
                sender.sendMessage("Serialized data copied to clipboard!")
                return true;
            }
            if (args[1] == "show-cool-text-gui") {
                sender.openInventory(CraftAPI.of(arrayOf(
                    CraftStationSlotType.INPUT.item,
                    CraftStationSlotType.INPUT.item,
                    CraftStationSlotType.INPUT_LIQ.item,
                    CraftStationSlotType.INPUT_LIQ.item,
                    CraftStationSlotType.ARROW.item,
                    CraftStationSlotType.OUTPUT.item,
                    CraftStationSlotType.OUTPUT.item,
                    CraftStationSlotType.OUTPUT_LIQ.item,
                    CraftStationSlotType.OUTPUT_LIQ.item
                )))
            }
            if (args[1] == "fake-block-packet-test") {
                Blocks.testSendFakeBlock(sender, sender.x.roundToInt(), sender.y.roundToInt(), sender.z.roundToInt())
            }
        } else if (args[0] == "send-pack") {
            sender.setResourcePack("http://localhost:16868", null , Component.text("Please install the provided resource pack."), true)
            return true;
        }
        // if (args[0] == "give") {
        //    sender.give(args[1])
        //}
        return false;
    }
}