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
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Ghost {

    Mythicrpg main;
    FileConfiguration config;

    public Ghost(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createGhost(Location location)
    {

        Creeper ghost = location.getWorld().spawn(location,Creeper.class);
        ghost.setMaxFuseTicks(999999);
        ghost.setFuseTicks(0);
        ghost.setPowered(true);
        ghost.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,999999,1,false,false,false));
        ghost.setCustomName(ColorUtil.ConvertToCustom(config.getString("GhostNametag")));
        ghost.setCustomNameVisible(true);

        ArmorStand name = location.getWorld().spawn(location, ArmorStand.class);
        name.setCustomName(ColorUtil.ConvertToCustom(config.getString("GhostNametag")) + " §7[" + Math.round(ghost.getHealth()) + "/" + ghost.getMaxHealth() + "]");
        name.setCustomNameVisible(true);
        name.setVisible(false);
        name.setMarker(false);

        ghost.setMaxHealth(config.getInt("GhostHealth"));
        ghost.setHealth(config.getInt("GhostHealth"));

        AttributeInstance att = ghost.getAttribute(Attribute.GENERIC_ARMOR);
        att.setBaseValue(20);

        new BukkitRunnable()
        {
            public void run()
            {
                name.teleport(ghost.getLocation());
                name.setCustomName(ColorUtil.ConvertToCustom(config.getString("GhostNametag")) + " §7[" + Math.round(ghost.getHealth()) + "/" + ghost.getMaxHealth() + "]");
                if(ghost.isDead())
                {
                    name.remove();
                    cancel();
                }
            }

        }.runTaskTimer(main,0L,1L);


        new BukkitRunnable()
        {
            public void run()
            {
                if(!ghost.isDead())
                {
                    ghost.setFuseTicks(0);
                    for(Entity entity : ghost.getNearbyEntities(7,7,7))
                    {
                        if(entity instanceof LivingEntity trg)
                        {
                            trg.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,60,1,false,false,false));
                            trg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,2,false,false,false));
                            trg.damage(2);
                        }
                    }
                }
                else
                {
                    ghost.remove();
                    cancel();
                }
            }
        }.runTaskTimer(main,0L,20L);

    }

}
