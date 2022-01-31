package me.tipsytapir.sethome

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandHome(private val homes: HomeConfiguration): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {
            val location = homes.getHomeLocation(sender.identity());
            if(location == null) {
                sender.sendMessage("Er staat geen domicilie geregisteerd.");
            } else {
                sender.teleport(location);
            }
        }
        return true;
    }
}