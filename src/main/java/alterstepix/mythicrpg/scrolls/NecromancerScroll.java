/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.scrolls;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import alterstepix.mythicrpg.util.MobItemCreator;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class NecromancerScroll implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;

    public NecromancerScroll(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }


    @EventHandler
    public void onClick(PlayerInteractEntityEvent event)
    {
        if(event.getRightClicked() instanceof LivingEntity target)
        {
            Player player = event.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("NecromancerScroll").get(1))) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

                for(int i = 0; i < 9; i++)
                {
                    Random r = new Random();
                    Skeleton skeleton = player.getWorld().spawn(player.getLocation().add(r.nextInt(1 + 1) -1, 0, r.nextInt(1 + 1) -1),Skeleton.class);
                    skeleton.setCustomNameVisible(true);

                    skeleton.getEquipment().setHelmet(MobItemCreator.normal(Material.IRON_HELMET,0));
                    skeleton.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.IRON_SWORD,0));

                    skeleton.setTarget(target);

                    new BukkitRunnable()
                    {
                        @Override
                        public void run() {
                            if(!skeleton.isDead())
                            {
                                skeleton.setCustomName("§c"+(int)skeleton.getHealth()+" / "+skeleton.getMaxHealth());

                                if(target.isDead())
                                {
                                    skeleton.setVelocity(new Vector(0,0.5,0));
                                    new BukkitRunnable()
                                    {
                                        @Override
                                        public void run() {
                                            skeleton.getWorld().spawnParticle(Particle.CRIT,skeleton.getLocation(),15,0,0,0,1);
                                            skeleton.getWorld().playSound(skeleton.getLocation(), Sound.BLOCK_CROP_BREAK,8,5);
                                            skeleton.remove();
                                            cancel();
                                        }
                                    }.runTaskTimer(main,(long)(Math.random()*20L),(long)(Math.random()*20L));

                                    cancel();
                                }

                                if(skeleton.getTarget() != null && skeleton.getTarget() != target)
                                    skeleton.setTarget(target);

                            }
                            else
                            {
                                skeleton.remove();
                                cancel();
                            }
                        }
                    }.runTaskTimer(main,0L,2L);

                }

            }
        }
    }

}
