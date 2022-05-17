/*
Copyright 2022 AlterStepix

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.mobsystem.classes;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class MobItemCreator {
    public static ItemStack leatherArmor(Material material, int protection, int r, int g, int b)
    {
        ItemStack result = new ItemStack(material,1);
        LeatherArmorMeta meta = (LeatherArmorMeta) result.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,protection,true);
        meta.setColor(Color.fromRGB(r,g,b));
        meta.setUnbreakable(true);

        result.setItemMeta(meta);
        return result;
    }
    public static ItemStack normalArmor(Material material, int protection)
    {
        ItemStack result = new ItemStack(material,1);
        ItemMeta meta = result.getItemMeta();
        if(protection > 0)
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,protection,true);
        meta.setUnbreakable(true);

        result.setItemMeta(meta);
        return result;
    }
    public static ItemStack normalSword(Material material, int sharpness)
    {
        ItemStack result = new ItemStack(material,1);
        ItemMeta meta = result.getItemMeta();
        if(sharpness > 0)
            meta.addEnchant(Enchantment.DAMAGE_ALL,sharpness,true);
        meta.setUnbreakable(true);

        result.setItemMeta(meta);
        return result;
    }
}
