package alterstepix.mythicrpg;

import alterstepix.mythicrpg.commands.GetMythicItems;
import alterstepix.mythicrpg.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mythicrpg extends JavaPlugin {

    public static ItemManager itemManager;

    @Override
    public void onEnable() {

        ItemManager.init();

        Bukkit.getServer().getPluginCommand("MythicItemGui").setExecutor(new GetMythicItems());
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);

        System.out.println("Plugin Enabled");

        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin Disabled");
    }


}
