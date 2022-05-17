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
import alterstepix.mythicrpg.util.GetPlayerHead;
import alterstepix.mythicrpg.util.MobItemCreator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class OverworldInvader {

    Mythicrpg main;
    FileConfiguration config;

    public OverworldInvader(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void CreateOverworldInvader(Location loc)
    {

        PiglinBrute mob = loc.getWorld().spawn(loc,PiglinBrute.class);

        mob.setImmuneToZombification(true);

        mob.setCustomNameVisible(true);

        mob.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999, 3,false,false,false));

        Attributable bAt = mob;

        mob.getEquipment().setHelmet(GetPlayerHead.GetCustomHead(GetPlayerHead.OverworldInvaderHead));
        mob.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.NETHERITE_AXE,7));

        AttributeInstance hpDef = bAt.getAttribute(Attribute.GENERIC_ARMOR);
        hpDef.setBaseValue(50);

        AttributeInstance KbAt = bAt.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        KbAt.setBaseValue(100);

        AttributeInstance hpAt = bAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        hpAt.setBaseValue(config.getInt("OverworldInvaderHealth"));


        mob.setHealth(config.getInt("OverworldInvaderHealth"));


        mob.setCustomName((ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("OverworldInvaderNamtetag")) + " §7["+Math.round(mob.getHealth())+"/"+mob.getMaxHealth()+"]"));

        new BukkitRunnable()
        {
            int i =0;
            public void run()
            {
                mob.setCustomName((ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("OverworldInvaderNamtetag")) + " §7["+Math.round(mob.getHealth())+"/"+mob.getMaxHealth()+"]"));
                if(!mob.isDead())
                {
                    if(i % 5 == 0)
                    {
                        for(Entity entity : mob.getNearbyEntities(10,10,10))
                        {
                            if(entity instanceof Player)
                            {
                                Player trg = (Player) entity;
                                trg.setVelocity(mob.getLocation().add(0,2,0).subtract(trg.getLocation()).toVector().normalize().setY(0.1));
                                mob.getWorld().spawnParticle(Particle.ASH,mob.getLocation(),5);
                                mob.getWorld().playSound(mob.getLocation(), Sound.BLOCK_ANVIL_BREAK,8,5);
                            }
                        }
                    }
                    if(i % 7 == 0)
                    {
                        Fireball fire = mob.getWorld().spawn(mob.getLocation().add(mob.getLocation().getDirection().normalize().multiply(1.5)),Fireball.class);
                        fire.setVelocity(mob.getLocation().getDirection());
                    }
                }
                else
                {
                    mob.remove();
                    cancel();
                }
                i++;
            }
        }.runTaskTimer(main,0L,20L);


    }

}
