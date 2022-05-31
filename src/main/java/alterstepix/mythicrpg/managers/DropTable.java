/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.managers;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.GetPlayerHead;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DropTable {

    public ItemStack amberShard; //Fire Spirit
    public ItemStack impulseShard; // Air Spirit
    public ItemStack frozenShard; // Ice Spirit
    public ItemStack infectedFlesh; // Infected Zombie
    public ItemStack infectedHeart; // Infected Zombie
    public ItemStack shadyAura; // Master Assassin
    public ItemStack parasiteHeart; // Parasite
    public ItemStack lightningShard; // SemiIdol
    public ItemStack witheredEye; // Wither Spider
    public ItemStack ancientShard; // Ancient Zombie
    public ItemStack decayedHeart; // Ancient Zombie
    public ItemStack witheredShard; // Netherlord
    public ItemStack netherCatalyst; //Netherlord
    public ItemStack netherEssence; //Netherlord
    public ItemStack cursedCrown; //Cursed Emperor
    public ItemStack ghostEssence; // Ghost
    public ItemStack destructiveShard; // Overworld Invader
    public ItemStack cursedHeart; // Cursed Emperor
    public ItemStack cursedBone; // Cursed Emperor
    public ItemStack cyclopsBone; // Cyclops
    public ItemStack NONE; // Watching eye
    public ItemStack FlamingRedstone; // Redstone Monster

    Mythicrpg main;
    FileConfiguration config;

    public DropTable(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void init()
    {
        CreateAmberShard();
        CreateImpulseShard();
        CreateFrozenShard();
        CreateInfectedFlesh();
        CreateInfectedHeart();
        CreateParasiteHeart();
        CreateLightingShard();
        CreateWitheredEye();
        CreateAncientShard();
        CreateDecayedHeart();
        CreateNetherCatalyst();
        CreateNetherEssence();
        CreateWitheredShard();
        CreateCursedCrown();
        CreateCursedBone();
        CreateCursedHeart();
        CreateDestructiveShard();
        CreateGhostEssence();
        CreateShadyAura();
        CreateCyclopsBone();
    }

    private void CreateAmberShard()
    {
        ItemStack item = new ItemStack(Material.HONEYCOMB, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("AmberShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        amberShard = item;
    }
    private void CreateImpulseShard()
    {
        ItemStack item = new ItemStack(Material.FEATHER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ImpulseShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        impulseShard = item;
    }
    private void CreateFrozenShard()
    {
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("FrozenShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        frozenShard = item;
    }
    private void CreateInfectedFlesh()
    {
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("InfectedFleshName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        infectedFlesh = item;
    }
    private void CreateInfectedHeart()
    {
        ItemStack item = new ItemStack(Material.BEETROOT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("InfectedHeartName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("RareRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        infectedHeart = item;
    }
    private void CreateParasiteHeart()
    {
        ItemStack item = new ItemStack(Material.GREEN_DYE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ParasiteHeartName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("RareRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        parasiteHeart = item;
    }
    private void CreateLightingShard()
    {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("LightningShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("EpicRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        lightningShard = item;
    }
    private void CreateWitheredEye()
    {
        ItemStack item = new ItemStack(Material.SPIDER_EYE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("WitheredEyeName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        witheredEye = item;
    }
    private void CreateAncientShard()
    {
        ItemStack item = new ItemStack(Material.FLINT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("AncientShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        ancientShard = item;
    }
    private void CreateDecayedHeart()
    {
        ItemStack item = new ItemStack(Material.FIREWORK_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("DecayedHeartName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("RareRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        decayedHeart = item;
    }
    public void CreateWitheredShard()
    {
        ItemStack item = new ItemStack(Material.COAL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("WitheredShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        witheredShard = item;
    }
    public void CreateNetherCatalyst()
    {
        ItemStack item = GetPlayerHead.GetCustomHead(GetPlayerHead.NetherCatalyst);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("NetherCatalystName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("EpicRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        netherCatalyst = item;
    }
    public void CreateNetherEssence()
    {
        ItemStack item = new ItemStack(Material.NETHER_BRICK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("NetherEssenceName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("RareRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        netherEssence = item;
    }
    public void CreateCursedCrown()
    {
        ItemStack item = new ItemStack(Material.GOLDEN_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("CursedCrownName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        cursedCrown = item;
    }
    public void CreateCursedHeart()
    {
        ItemStack item = new ItemStack(Material.BONE_MEAL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("CursedHeartName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        cursedHeart = item;
    }
    public void CreateCursedBone()
    {
        ItemStack item = new ItemStack(Material.BONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("CursedBoneName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        cursedBone = item;
    }
    public void CreateDestructiveShard()
    {
        ItemStack item = new ItemStack(Material.RED_DYE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("DestructiveShard")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("RareRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        destructiveShard = item;
    }
    public void CreateGhostEssence()
    {
        ItemStack item = new ItemStack(Material.GRAY_DYE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GhostEssence")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        ghostEssence = item;
    }
    public void CreateShadyAura()
    {
        ItemStack item = new ItemStack(Material.INK_SAC, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ShadyAura")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        shadyAura = item;
    }
    public void CreateCyclopsBone()
    {
        ItemStack item = new ItemStack(Material.BONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("CyclopsAura")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        cyclopsBone = item;
    }
}
