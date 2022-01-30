package me.tipsytapir.sethome
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {
    override fun onEnable() {
        logger.info("Enabled %s".format(this.name));
        this.getCommand("sethome")?.setExecutor(CommandHome());
    }

    override fun onDisable() {
        logger.info("disabled");
    }
}