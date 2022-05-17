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
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FrozenStorm implements Listener {
    Mythicrpg main;
    ItemLoreLibrary lib;

    public FrozenStorm(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("FrozenStorm").get(1))) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
                new BukkitRunnable()
                {
                    Random rnd = new Random();
                    int i = 0;
                    @Override
                    public void run() {
                        for(int j = 0; j < 7; j++)
                        {
                            for(Entity entity : player.getNearbyEntities(12,12,12))
                            {
                                if(entity instanceof LivingEntity target)
                                {
                                    target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,2,false,false,false));
                                }
                            }
                            FallingBlock ice = player.getWorld().spawnFallingBlock(player.getLocation(), Material.PACKED_ICE, (byte)0);
                            ice.setCustomName("frozenStormIce");
                            ice.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3),ThreadLocalRandom.current().nextDouble(0.7, 1.2),0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI*2)));
                            new BukkitRunnable(){
                                public void run()
                                {
                                    if(!ice.isDead())
                                    {
                                        ice.getWorld().spawnParticle(Particle.SNOWFLAKE,ice.getLocation(),1);
                                        for(Entity entity : ice.getNearbyEntities(4,4,4))
                                        {
                                            if(entity instanceof LivingEntity && entity != player){
                                                if(ice.getLocation().distanceSquared(entity.getLocation()) < 8)
                                                {
                                                    LivingEntity target = (LivingEntity) entity;
                                                    PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, 60,3,false,false,false);
                                                    PotionEffect effect1 = new PotionEffect(PotionEffectType.WEAKNESS, 60, 3,false,false,false);
                                                    target.addPotionEffect(effect);
                                                    target.addPotionEffect(effect1);
                                                    target.damage(4,player);
                                                    target.getWorld().spawnParticle(Particle.SNOWFLAKE,target.getLocation(),5);
                                                    ice.remove();
                                                    cancel();
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        cancel();
                                    }
                                }
                            }.runTaskTimer(main,0L,2L);
                        }
                        if(i > 7)
                            cancel();
                        i++;
                    }
                }.runTaskTimer(main,0L,20L);
            }
        }
    }

    @EventHandler
    public void onChange(EntityChangeBlockEvent event)
    {
        if(event.getEntity() instanceof FallingBlock)
        {
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("frozenStormIce"))
            {
                event.setCancelled(true);
                event.getEntity().remove();
            }
        }
    }

    @EventHandler
    public void onDrop(EntityDropItemEvent event)
    {
        if(event.getEntity() instanceof FallingBlock)
        {
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("frozenStormIce"))
            {
                event.setCancelled(true);
                event.getItemDrop().remove();
            }
        }
    }

}
