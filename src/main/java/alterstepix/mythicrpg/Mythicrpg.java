package alterstepix.mythicrpg;

import alterstepix.mythicrpg.armorsets.GrandmasterArmor;
import alterstepix.mythicrpg.commands.*;
import alterstepix.mythicrpg.itemabilities.*;

import alterstepix.mythicrpg.misc.CustomRecipes;
import alterstepix.mythicrpg.misc.MobDropManager;
import alterstepix.mythicrpg.mobs.*;
import alterstepix.mythicrpg.scrolls.ArrowStorm;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Mythicrpg extends JavaPlugin{

    FileConfiguration configuration = getConfig();

    @Override
    public void onEnable() {

        configuration.options().copyDefaults(true);
        saveConfig();


        Bukkit.getServer().getPluginCommand("MythicItemsGui").setExecutor(new GetMythicItems(this));
        Bukkit.getServer().getPluginCommand("MythicDropsGui").setExecutor(new GetMythicDrops(this));
        Bukkit.getServer().getPluginCommand("MythicScrollsGui").setExecutor(new GetMythicScrolls(this));
        Bukkit.getServer().getPluginCommand("AddItemAbility").setExecutor(new AppendAbilityLore(this));
        Bukkit.getServer().getPluginCommand("SummonMythicMob").setExecutor(new SummonMythicMob(this));
        Bukkit.getServer().getPluginCommand("MythicArmorGui").setExecutor(new GetMythicArmor(this));

        Bukkit.getServer().getPluginCommand("AddItemAbility").setTabCompleter(new AppendAbilityLore(this));
        Bukkit.getServer().getPluginCommand("SummonMythicMob").setTabCompleter(new SummonMythicMob(this));

        Bukkit.getServer().getPluginManager().registerEvents(new LightningAxe(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new IdolsIncarnate(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Terminator(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new HealingSword(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new FrozenWand(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new ImpulseSword(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new AmberScythe(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new MilkPotion(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new GiantSword(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new FuriousAxe(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new AirBurner(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new RunicDagger(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new WitherSpider(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Parasite(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new InfectedZombie(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new MasterAssassin(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new SemiIdol(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AncientZombie(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new NetherLord(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new ArrowStorm(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new GrandmasterArmor(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new MobDropManager(this),this);

        CustomRecipes recipes = new CustomRecipes(this);

        if(configuration.getInt("EnableTerminatorRecipe") == 1)
            recipes.RegisterTerminatorRecipe();
        if(configuration.getInt("EnableMilkPotionRecipe") == 1)
            recipes.RegisterMilkPotionRecipe();
        if(configuration.getInt("EnableLightningAxeRecipe") == 1)
            recipes.RegisterLightingAxeRecipe();
        if(configuration.getInt("EnableImpulseSwordRecipe") == 1)
            recipes.RegisterImpulseSwordRecipe();
        if(configuration.getInt("EnableIdolsIncarnateRecipe") == 1)
            recipes.RegisterIdolsIncarnateRecipe();
        if(configuration.getInt("EnableHealingSwordRecipe") == 1)
            recipes.RegisterHealingSwordRecipe();
        if(configuration.getInt("EnableGiantSwordRecipe") == 1)
            recipes.RegisterGiantSwordRecipe();
        if(configuration.getInt("EnableFuriousRecipe") == 1)
            recipes.RegisterFuriousAxeRecipe();
        if(configuration.getInt("EnableFrozenWandRecipe") == 1)
            recipes.RegisterFrozenWandRecipe();
        if(configuration.getInt("EnableAmberScytheRecipe") == 1)
            recipes.RegisterAmberScytheRecipe();
        if(configuration.getInt("EnableAirBurnerRecipe") == 1)
            recipes.RegisterAirBurnerRecipe();


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
