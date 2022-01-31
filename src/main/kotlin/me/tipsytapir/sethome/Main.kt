package me.tipsytapir.sethome

import net.kyori.adventure.identity.Identity
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main: JavaPlugin() {

    private val homes: HomeConfiguration = HomeConfiguration.loadConfig(File("%s/homes.yml".format(this.dataFolder.path)));

    override fun onEnable() {
        logger.info("Enabled %s".format(this.name));
        this.getCommand("sethome")?.setExecutor(CommandSetHome(homes));
        this.getCommand("home")?.setExecutor(CommandHome(homes));

        scheduleFlush();
    }

    private fun scheduleFlush() {
        val scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimerAsynchronously(this, { _ -> homes.flush() }, 20L * 60L * 15L, 20L * 60L * 15L); // Run every 15 minutes
    }

    override fun onDisable() {
        homes.flush(force = true);
        logger.info("disabled");
    }
}

data class PlayerInfo (val displayName: String, val identity: Identity);