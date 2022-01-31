package me.tipsytapir.sethome

import net.kyori.adventure.identity.Identity
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.util.logging.Level

class HomeConfiguration(private val file: File): YamlConfiguration() {
    private var changed = false;

    companion object Factory {
        fun loadConfig(file: File): HomeConfiguration {
            val homes = HomeConfiguration(file);
            try {
                if(!file.exists()) {
                    Bukkit.getLogger().log(Level.INFO, "Could not find file 'homes.yml', created the file instead");
                    file.createNewFile();
                }
                homes.load(file);
            } catch(exception: Exception) {
                Bukkit.getLogger().log(Level.SEVERE, "Could not open file. See stacktrace.");
                exception.printStackTrace();
            }

            return homes;
        }
    }

    fun saveHome(playerInfo: PlayerInfo, location: Location) {
        this.set("players.%s.home".format(playerInfo.identity.uuid().toString()), location);
        this.set("players.%s.world".format(playerInfo.identity.uuid().toString()), location.world.name);
        this.set("players.%s.displayName".format(playerInfo.identity.uuid().toString()), playerInfo.displayName);

        changed = true;
    }

    fun getHomeLocation(identity: Identity): Location? {
        return this.getLocation("players.%s.home".format(identity.uuid().toString()));
    }

    fun flush(force: Boolean? = false) {
        if(changed || force == true) {
            Bukkit.getLogger().log(Level.INFO, "Flushing homes.yml");
            this.save(this.file);
        } else {
            Bukkit.getLogger().log(Level.INFO, "No changes")
        }
    }
}