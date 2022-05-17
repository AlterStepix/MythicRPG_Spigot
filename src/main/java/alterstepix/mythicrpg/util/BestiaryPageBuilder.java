/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BestiaryPageBuilder {

    public BestiaryPageBuilder()
    {

    }
    public ItemStack create(String title, int difficulty,int health, ArrayList<String> itemDesc, ArrayList<String> drops)
    {
        ItemStack page = new ItemStack(Material.PAPER);
        ItemMeta meta = page.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(title));

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§6Difficulty: "+difficulty);
        lore.add("§cHealth: "+health);
        lore.add("");
        lore.add("§6Description: ");
        lore.addAll(itemDesc);
        lore.add("");
        lore.add("§6Drops: ");
        lore.addAll(drops);
        lore.add("");
        lore.add("§cClick for more options.");

        meta.setLore(lore);
        page.setItemMeta(meta);

        return page;
    }
}
