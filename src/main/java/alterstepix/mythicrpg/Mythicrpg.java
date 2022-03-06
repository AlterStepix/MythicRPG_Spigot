package alterstepix.mythicrpg;

import alterstepix.mythicrpg.commands.GetMythicItems;
import alterstepix.mythicrpg.itemabilities.IdolsIncarnate;
import alterstepix.mythicrpg.itemabilities.LightningAxe;

import alterstepix.mythicrpg.itemabilities.Terminator;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mythicrpg extends JavaPlugin {


    @Override
    public void onEnable() {
        
        ItemManager.init();
        Cooldown.init();

        Bukkit.getServer().getPluginCommand("MythicItemGui").setExecutor(new GetMythicItems());
        Bukkit.getServer().getPluginManager().registerEvents(new LightningAxe(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new IdolsIncarnate(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Terminator(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);

        System.out.println("Plugin Enabled");
        
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin Disabled");
    }


}
