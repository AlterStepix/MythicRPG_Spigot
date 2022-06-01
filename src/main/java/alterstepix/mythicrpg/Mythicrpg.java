/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg;

import alterstepix.mythicrpg.armorsets.*;
import alterstepix.mythicrpg.commands.*;
import alterstepix.mythicrpg.itemabilities.*;

import alterstepix.mythicrpg.menusystem.MenuListener;
import alterstepix.mythicrpg.misc.CustomRecipes;
import alterstepix.mythicrpg.misc.MobDropManager;
import alterstepix.mythicrpg.misc.NaturalSpawn;
import alterstepix.mythicrpg.misc.ResourcePackLoader;
import alterstepix.mythicrpg.mobs.*;
import alterstepix.mythicrpg.scrolls.*;
import alterstepix.mythicrpg.util.PMU;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Mythicrpg extends JavaPlugin{

    public static Mythicrpg INSTANCE;

    private static final HashMap<Player, PMU> PMU_Map = new HashMap<>();

    FileConfiguration configuration = getConfig();

    @Override
    public void onEnable() {
        INSTANCE = this;
        configuration.options().copyDefaults(true);
        saveConfig();


        Bukkit.getServer().getPluginCommand("MythicItemsGui").setExecutor(new GetMythicItems(this));
        Bukkit.getServer().getPluginCommand("MythicDropsGui").setExecutor(new GetMythicDrops(this));
        Bukkit.getServer().getPluginCommand("MythicScrollsGui").setExecutor(new GetMythicScrolls(this));
        Bukkit.getServer().getPluginCommand("AddItemAbility").setExecutor(new AppendAbilityLore(this));
        Bukkit.getServer().getPluginCommand("SummonMythicMob").setExecutor(new SummonMythicMob(this));
        Bukkit.getServer().getPluginCommand("MythicArmorGui").setExecutor(new GetMythicArmor(this));
        Bukkit.getServer().getPluginCommand("GetMythicLoot").setExecutor(new GetRandomLoot());
        Bukkit.getServer().getPluginCommand("MythicBestiary").setExecutor(new MythicBestiary());

        Bukkit.getServer().getPluginCommand("AddItemAbility").setTabCompleter(new AppendAbilityLore(this));
        Bukkit.getServer().getPluginCommand("SummonMythicMob").setTabCompleter(new SummonMythicMob(this));

        Bukkit.getServer().getPluginManager().registerEvents(new BestiaryBook(this),this);
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
        Bukkit.getServer().getPluginManager().registerEvents(new MythicSwordOfLegends(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new FlamingWhip(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new DarknessConcentrator(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new InfectedSword(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new SwordOfGrowth(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Singularity(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new CorruptedMythicIdolsIncarnate(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new FeatherBow(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlazingFlare(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new AmethystSword(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new LightningHammer(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Inquisitor(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new WitherSpider(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new Parasite(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new InfectedZombie(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new MasterAssassin(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new SemiIdol(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AncientZombie(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new NetherLord(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new CursedEmperor(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new FrozenSoul(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new RevenantArcher(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new MushroomMonster(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new mGiant(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new RedstoneMonster(),this);
        Bukkit.getServer().getPluginManager().registerEvents(new DarknessIncarnate(),this);

        Bukkit.getServer().getPluginManager().registerEvents(new ArrowStorm(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new NetherScroll(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new HealingTotemScroll(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new InfernalAuraScroll(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new FrozenStorm(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new NecromancerScroll(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new GrandmasterArmor(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new MasterAssassinArmor(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new FrozenWarriorArmor(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new MythicWarriorArmor(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new ThiefArmor(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new BeastArmor(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new CorruptedMythicWarriorArmor(this),this);
        Bukkit.getServer().getPluginManager().registerEvents(new GlacialMythicWarriorArmor(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new MobDropManager(this),this);


        Bukkit.getServer().getPluginManager().registerEvents(new ResourcePackLoader(),this);

        Bukkit.getServer().getPluginManager().registerEvents(new NaturalSpawn(this),this);

        Bukkit.getServer().getPluginManager().registerEvents(new MenuListener(),this);

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
        if(configuration.getInt("EnableRunicSword") == 1)
            recipes.RegisterRunicDaggerRecipe();
        if(configuration.getInt("EnableRunicSwordRecipe") == 1)
            recipes.RegisterRunicDaggerRecipe();
        if(configuration.getInt("FlamingWhipRecipe") == 1)
            recipes.RegisterFlamingWhipRecipe();
        if(configuration.getInt("MythicSwordOfLegendsRecipe") == 1)
            recipes.RegisterMythicSwordOfLegendsRecipe();
        if(configuration.getInt("DarknessConcentratorRecipe") == 1)
            recipes.RegisterDarknessConcentratorRecipe();
        if(configuration.getInt("InfectedSwordRecipe") == 1)
            recipes.RegisterInfectedSwordRecipe();


        System.out.println("[mrpg] Plugin Enabled");
        
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("[mrpg] Plugin Disabled");
    }

    public FileConfiguration getConfiguration()
    {
        return configuration;
    }


    public static PMU getPMU(Player player)
    {
        PMU pmu;

        if(PMU_Map.containsKey(player))
        {
            return PMU_Map.get(player);
        }
        else
        {
            pmu = new PMU(player);
            PMU_Map.put(player,pmu);
            return pmu;
        }

    }

}
