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
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class AncientZombie implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    LivingEntity Zombietarget;

    public AncientZombie(Mythicrpg main)
    {
        this.main = main;
        config = main.getConfig();
    }

    public void setTarget(LivingEntity mob)
    {
        Zombietarget = mob;
    }

    public LivingEntity createAncientZombie(Location location)
    {
        int hp = config.getInt("AncientZombieHealth");
        Zombie zombie = location.getWorld().spawn(location, Zombie.class);
        if(Zombietarget != null)
            zombie.setTarget(Zombietarget);

        Ageable AntiBaby = (Ageable)zombie;
        AntiBaby.setAdult();

        zombie.setCustomName(ColorUtil.ConvertToCustom(config.getString("AncientZombieNametag")));
        zombie.setCustomNameVisible(true);
        Attributable infectedAt = zombie;
        AttributeInstance attributeHP = infectedAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHP.setBaseValue(hp);
        zombie.setHealth(hp);
        AttributeInstance attributeSpeed = infectedAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(0.2);

        zombie.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
        zombie.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        zombie.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        zombie.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        zombie.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));

        zombie.setCustomName(ColorUtil.ConvertToCustom(config.getString("AncientZombieNametag")) + " §7["+Math.round(zombie.getHealth())+"/"+zombie.getMaxHealth()+"]");

        new BukkitRunnable()
        {
            public void run()
            {
                if(!zombie.isDead())
                {
                    zombie.setCustomName(ColorUtil.ConvertToCustom(config.getString("AncientZombieNametag")) + " §7["+Math.round(zombie.getHealth())+"/"+zombie.getMaxHealth()+"]");
                }
                else
                {
                    cancel();
                }
            }
        }.runTaskTimer(main,0L,20L);
        return zombie;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event)
    {
        if(event.getDamager().getCustomName() != null)
        {
            if(event.getEntity() instanceof LivingEntity) {
                LivingEntity trg = (LivingEntity) event.getEntity();
                if(event.getDamager().getCustomName().contains(config.getString("AncientZombieNametag").split("!")[1])) {
                    LivingEntity zombie = (LivingEntity) event.getDamager();
                    trg.damage(2);
                    trg.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 2, true,true,true));

                    zombie.getWorld().spawnParticle(Particle.CRIT, zombie.getEyeLocation().getX(),zombie.getEyeLocation().getY()+1, zombie.getEyeLocation().getZ(),3);
                    zombie.getWorld().playSound(zombie.getLocation(), Sound.BLOCK_GRAVEL_BREAK,5,5);

                }
            }
        }
    }


}
