package alterstepix.mythicrpg;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mythicrpg extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.options().copyDefaults(true);
        saveConfig();

        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
        Bukkit.getLogger().info("[MRPG] Plugin Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[MRPG] Plugin Disabled");
    }

    public FileConfiguration getConfig()
    {
        return config;
    }

}
