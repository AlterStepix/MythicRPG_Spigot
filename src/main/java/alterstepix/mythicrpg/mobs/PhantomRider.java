/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Wither;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PhantomRider {
    Mythicrpg main;
    FileConfiguration config;

    public PhantomRider(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createPhantom(Location location)
    {
        Phantom phantom = location.getWorld().spawn(location,Phantom.class);
        Stray skeleton = location.getWorld().spawn(location,Stray.class);

        skeleton.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 99999,10,false,false,false));

        phantom.setInvulnerable(true);
        phantom.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999,10,false,false,false));

        AttributeInstance skeletonHP = skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        skeletonHP.setBaseValue(config.getInt("PhantomRiderHealth"));

        phantom.addPassenger(skeleton);

        skeleton.setCustomName(ColorUtil.ConvertToCustom(config.getString("PhantomRiderNametag")) + " §7["+Math.round(phantom.getHealth())+"/"+phantom.getMaxHealth()+"]");
        skeleton.setCustomNameVisible(true);

        new BukkitRunnable()
        {
            @Override
            public void run() {
                if(!skeleton.isDead())
                {
                    phantom.setCustomName(ColorUtil.ConvertToCustom(config.getString("PhantomRiderNametag")) + " §7["+Math.round(phantom.getHealth())+"/"+phantom.getMaxHealth()+"]");
                }
                else
                {
                    phantom.remove();
                    skeleton.remove();
                    cancel();
                }
            }
        }.runTaskTimer(main,0L,20L);

    }
}
