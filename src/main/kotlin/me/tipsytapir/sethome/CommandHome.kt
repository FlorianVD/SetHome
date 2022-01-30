package me.tipsytapir.sethome

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandHome: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {
            val playerLocation = sender.location;
            println("%f, %f, %f".format(playerLocation.x, playerLocation.y, playerLocation.z));
        }
        return true;
    }
}