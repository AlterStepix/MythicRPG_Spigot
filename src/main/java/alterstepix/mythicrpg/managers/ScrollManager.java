/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.managers;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.mobs.NetherLord;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScrollManager {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary ILL;

    public ItemStack ArrowStormScroll;
    public ItemStack NetherStormScroll;
    public ItemStack HealingTotemScroll;
    public ItemStack InfernalAuraScroll;
    public ItemStack FrozenStormScroll;
    public ItemStack NecromancerScroll;

    public ScrollManager(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        ILL = new ItemLoreLibrary(main);
        ILL.Init();
    }

    public void init()
    {
        createArrowStormScroll();
        createNetherStormScroll();
        createHealingTotemScroll();
        createInfernalAuraScroll();
        createFrozenStormScroll();
        createNecromancerScroll();
    }

    public void createArrowStormScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ArrowStormName")));
        meta.setLore(ILL.Lore.get("ArrowStorm"));
        scroll.setItemMeta(meta);

        ArrowStormScroll = scroll;
    }
    public void createNetherStormScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("NetherStormScroll")));
        meta.setLore(ILL.Lore.get("NetherStorm"));
        scroll.setItemMeta(meta);

        NetherStormScroll = scroll;
    }
    public void createHealingTotemScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("HealingTotemScroll")));
        meta.setLore(ILL.Lore.get("HealingTotem"));
        scroll.setItemMeta(meta);

        HealingTotemScroll = scroll;
    }

    public void createInfernalAuraScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("InfernalAuraScroll")));
        meta.setLore(ILL.Lore.get("InfernalAura"));
        scroll.setItemMeta(meta);

        InfernalAuraScroll = scroll;
    }
    public void createFrozenStormScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("FrozenStormScroll")));
        meta.setLore(ILL.Lore.get("FrozenStorm"));
        scroll.setItemMeta(meta);

        FrozenStormScroll = scroll;
    }
    public void createNecromancerScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("NecromancerScroll")));
        meta.setLore(ILL.Lore.get("NecromancerScroll"));
        scroll.setItemMeta(meta);

        NecromancerScroll = scroll;
    }



}
