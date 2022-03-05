package alterstepix.mythicrpg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mythicrpg extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
        Bukkit.getLogger().info("[MRPG] Plugin Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("[MRPG] Plugin Disabled");
    }
}
