package alterstepix.mythicrpg;

import alterstepix.mythicrpg.commands.AppendAbilityLore;
import alterstepix.mythicrpg.commands.GetMythicItems;
import alterstepix.mythicrpg.commands.SummonMythicMob;
import alterstepix.mythicrpg.itemabilities.*;

import alterstepix.mythicrpg.mobs.InfectedZombie;
import alterstepix.mythicrpg.mobs.Necromancer;
import alterstepix.mythicrpg.mobs.WitherSpider;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mythicrpg extends JavaPlugin {

    FileConfiguration configuration = getConfig();

    @Override
    public void onEnable() {

        configuration.options().copyDefaults(true);
        saveConfig();


        Bukkit.getServer().getPluginCommand("MythicItemGui").setExecutor(new GetMythicItems(this));
        Bukkit.getServer().getPluginCommand("AddItemAbility").setExecutor(new AppendAbilityLore(this));
        Bukkit.getServer().getPluginCommand("SummonMythicMob").setExecutor(new SummonMythicMob(this));

        Bukkit.getServer().getPluginCommand("AddItemAbility").setTabCompleter(new AppendAbilityLore(this));
        Bukkit.getServer().getPluginCommand("SummonMythicMob").setTabCompleter(new SummonMythicMob(this));

        Bukkit.getServer().getPluginManager().registerEvents(new LightningAxe(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new IdolsIncarnate(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Terminator(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new HealingSword(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new FrozenWand(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new WitherSpider(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Necromancer(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new InfectedZombie(this),this);


        System.out.println("Plugin Enabled");
        
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin Disabled");
    }

    public FileConfiguration getConfiguration()
    {
        return configuration;
    }


}
