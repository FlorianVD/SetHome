package me.tipsytapir.sethome

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandSetHome(private val homes: HomeConfiguration): CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender is Player) {
            val playerLocation = sender.location;
            homes.saveHome(PlayerInfo(sender.name, sender.identity()), playerLocation);
            sender.sendMessage("Domicilie geregistreerd. Wees welkom!");
        }
        return true;
    }

}
