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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class RedstoneMonster implements Listener {
    public LivingEntity summon(Location location)
    {
        Skeleton skeleton = location.getWorld().spawn(location, Skeleton.class);

        AttributeInstance health = skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        health.setBaseValue(Mythicrpg.INSTANCE.getConfig().getInt("RedstoneMonsterHealth"));
        skeleton.setHealth(Mythicrpg.INSTANCE.getConfig().getInt("RedstoneMonsterHealth"));

        AttributeInstance defense = skeleton.getAttribute(Attribute.GENERIC_ARMOR);
        defense.setBaseValue(60);

        skeleton.setCustomName(ColorUtil.ConvertToCustom(Mythicrpg.INSTANCE.getConfig().getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(Mythicrpg.INSTANCE.getConfig().getString("RedstoneMonsterNametag")) + " §7["+Math.round(skeleton.getHealth())+"/"+skeleton.getMaxHealth()+"]");
        skeleton.setCustomNameVisible(true);

        skeleton.getEquipment().setHelmet(GetPlayerHead.GetCustomHead(GetPlayerHead.RedstoneMonsterHead));
        skeleton.getEquipment().setChestplate(MobItemCreator.normal(Material.IRON_CHESTPLATE,0));
        skeleton.getEquipment().setLeggings(MobItemCreator.leather(Material.LEATHER_LEGGINGS,0,243,89,89));
        skeleton.getEquipment().setBoots(MobItemCreator.leather(Material.LEATHER_BOOTS,0,243,89,89));
        skeleton.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.REDSTONE_TORCH,25));

        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                if (!skeleton.isDead()) {
                    skeleton.setCustomName(ColorUtil.ConvertToCustom(Mythicrpg.INSTANCE.getConfig().getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(Mythicrpg.INSTANCE.getConfig().getString("RedstoneMonsterNametag")) + " §7["+Math.round(skeleton.getHealth())+"/"+skeleton.getMaxHealth()+"]");
                    if (i % 6 == 0) {
                        for (int i = 0; i < 3; i++) {
                            ItemStack item = new ItemStack(Material.REDSTONE);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(i + "");
                            item.setItemMeta(meta);
                            Item web = skeleton.getWorld().dropItem(skeleton.getLocation(), item);
                            web.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3), ThreadLocalRandom.current().nextDouble(0.7, 1.2), 0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI * 2)));

                            web.setCustomName("§3RedstoneMonsterRedstne");
                        }

                    }

                    if(i % 13 == 5)
                    {
                        skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80,10,false,false,false));
                        skeleton.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,80,1,false,false,false));
                        skeleton.setInvulnerable(true);
                    }
                    if(i % 13 == 9)
                    {
                        for (int i = 0; i < 15; i++) {
                            ItemStack item = new ItemStack(Material.BLAZE_POWDER);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(i + "");
                            item.setItemMeta(meta);
                            Item web = skeleton.getWorld().dropItem(skeleton.getLocation(), item);
                            //web.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3), ThreadLocalRandom.current().nextDouble(0.7, 1.2), 0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI * 2)));

                            web.setCustomName("§3RedstoneMonsterFlame");


                            web.setVelocity(new Vector(ThreadLocalRandom.current().nextDouble(-2,2),Math.random()*0.3,ThreadLocalRandom.current().nextDouble(-2,2)));

                            new BukkitRunnable()
                            {
                                int i =0;
                                @Override
                                public void run() {
                                    if(web.isDead() || !web.isValid() || i>200)
                                    {
                                        cancel();
                                        web.remove();
                                    }

                                    if(skeleton.getTarget() != null)
                                        web.setVelocity(skeleton.getTarget().getLocation().add(0,1,0).subtract(web.getLocation()).toVector().multiply(0.05));

                                    web.getWorld().spawnParticle(Particle.FLAME,web.getLocation(),3,0,0,0,0);

                                    i++;

                                }
                            }.runTaskTimer(Mythicrpg.INSTANCE,5L,2L);

                        }
                        skeleton.setInvulnerable(false);
                        skeleton.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,40,1,false,false,false));
                    }
                    i++;

                } else
                {
                    skeleton.remove();
                    cancel();
                }

            }
        }.runTaskTimer(Mythicrpg.INSTANCE,0L,20L);

        return null;
    }

    @EventHandler
    public void Pickup_item(EntityPickupItemEvent e)
    {
        if(e.getEntity() instanceof Player p)
        {
            if(e.getItem().getCustomName() != null &&e.getItem().getCustomName().contains("§3RedstoneMonsterRedstne"))
            {
                e.setCancelled(true);
                e.getItem().remove();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,2,false,false,false));
                p.setFireTicks(p.getFireTicks() + 25);
                p.damage(2);

            }
            else if(e.getItem().getCustomName() != null &&e.getItem().getCustomName().contains("§3RedstoneMonsterFlame"))
            {
                e.setCancelled(true);
                e.getItem().remove();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,3,false,false,false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60,3,false,false,false));
                p.setFireTicks(p.getFireTicks()+90);
                p.getWorld().createExplosion(p.getLocation(),2,false,false);

                p.damage(15);


            }
        }
    }
}
