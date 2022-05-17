/*
Copyright 2022 AlterStepix

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.mobsystem.info;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class CustomMobInfo {
    public String Nametag;
    public String NametagColor;
    public EntityType Type;

    public int MaxHealth;
    public int Defence;
    public float Speed;

    public PotionEffect[] Effects;

    public ItemStack Helmet;
    public ItemStack Chestplate;
    public ItemStack Leggings;
    public ItemStack Boots;
    public ItemStack Mainhand;
}
