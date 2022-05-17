/*
Copyright 2022 AlterStepix

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class FrozenWand implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    Cooldown thiscd = new Cooldown();
    ItemLoreLibrary lib;

    public FrozenWand(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        thiscd.init();
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("FrozenBreathe").get(1))) {
                if (thiscd.checkCD(player)) {

                    FallingBlock ice = player.getWorld().spawnFallingBlock(player.getLocation(), Material.BLUE_ICE, (byte)0);
                    ice.setCustomName("FW_ice");
                    ice.setVelocity(player.getLocation().getDirection().multiply(2));

                    FallingBlock ice2 = player.getWorld().spawnFallingBlock(player.getLocation(), Material.PACKED_ICE, (byte)0);
                    ice2.setCustomName("FW_ice");
                    ice2.setVelocity((player.getLocation().getDirection().multiply(2)).rotateAroundY(Math.toRadians(-10)));

                    FallingBlock ice3 = player.getWorld().spawnFallingBlock(player.getLocation(), Material.PACKED_ICE, (byte)0);
                    ice3.setCustomName("FW_ice");
                    ice3.setVelocity((player.getLocation().getDirection().multiply(2)).rotateAroundY(Math.toRadians(10)));

                    new BukkitRunnable(){
                        public void run()
                        {
                            if(!ice.isDead())
                            {
                                ice.getWorld().spawnParticle(Particle.SNOWBALL,ice.getLocation(),1);
                                for(Entity entity : ice.getNearbyEntities(2,2,2))
                                {
                                    if(entity instanceof LivingEntity trg && entity != player){
                                        if(ice.getLocation().distanceSquared(trg.getLocation()) < 6){
                                            PotionEffect potionEffect = new PotionEffect(PotionEffectType.WEAKNESS, 200, 2, true, true, true);
                                            PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.SLOW, 120, 3, true, true, true);
                                            trg.damage(2);
                                            trg.addPotionEffect(potionEffect);
                                            trg.addPotionEffect(potionEffect2);
                                            trg.getWorld().spawnParticle(Particle.SNOWBALL, trg.getLocation(),15);
                                            trg.getWorld().playSound(trg.getLocation(), Sound.BLOCK_GLASS_BREAK,15,5);
                                            ice.remove();
                                        }
                                    }
                                }
                            }
                            if(!ice2.isDead())
                            {
                                ice2.getWorld().spawnParticle(Particle.SNOWBALL,ice2.getLocation(),1);
                                for(Entity entity : ice2.getNearbyEntities(2,2,2))
                                {
                                    if(entity instanceof LivingEntity trg && entity != player){
                                        if(ice2.getLocation().distanceSquared(trg.getLocation()) < 6){
                                            PotionEffect potionEffect = new PotionEffect(PotionEffectType.WEAKNESS, 120, 2, true, true, true);
                                            PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.SLOW, 80, 3, true, true, true);
                                            trg.addPotionEffect(potionEffect);
                                            trg.addPotionEffect(potionEffect2);
                                            trg.getWorld().spawnParticle(Particle.SNOWBALL, trg.getLocation(),15);
                                            trg.getWorld().playSound(trg.getLocation(), Sound.BLOCK_GLASS_BREAK,15,5);
                                            ice2.remove();
                                        }
                                    }
                                }
                            }
                            if(!ice3.isDead())
                            {
                                ice3.getWorld().spawnParticle(Particle.SNOWBALL,ice3.getLocation(),1);
                                for(Entity entity : ice3.getNearbyEntities(2,2,2))
                                {
                                    if(entity instanceof LivingEntity trg && entity != player){
                                        if(ice3.getLocation().distanceSquared(trg.getLocation()) < 6){
                                            PotionEffect potionEffect = new PotionEffect(PotionEffectType.WEAKNESS, 120, 2, true, true, true);
                                            PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.SLOW, 80, 3, true, true, true);
                                            trg.addPotionEffect(potionEffect);
                                            trg.addPotionEffect(potionEffect2);
                                            trg.getWorld().spawnParticle(Particle.SNOWBALL, trg.getLocation(),15);
                                            trg.getWorld().playSound(trg.getLocation(), Sound.BLOCK_GLASS_BREAK,15,5);
                                            ice3.remove();
                                        }
                                    }
                                }
                            }
                            if (ice.isDead()&& ice2.isDead()&&ice3.isDead()){
                                cancel();
                            }
                        }
                    }.runTaskTimer(main,0L,2L);

                    player.getWorld().spawnParticle(Particle.SNOWBALL, player.getLocation(), 5);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SNOW_GOLEM_SHOOT, 8, 5);
                    thiscd.putCooldown(player,config.getInt("frozenWandCooldown"));

                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                    player.getWorld().spawnParticle(Particle.WATER_SPLASH, player.getLocation(), 10);
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_POINTED_DRIPSTONE_DRIP_WATER,15,5);
                }
            }
        }
    }
    @EventHandler
    public void onChange(EntityChangeBlockEvent event)
    {
        if(event.getEntity() instanceof FallingBlock)
        {
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("FW_ice"))
            {
                event.getEntity().getWorld().spawnParticle(Particle.CRIT_MAGIC,event.getEntity().getLocation(),12,0,0,0,1);
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
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("FW_ice"))
            {
                event.setCancelled(true);
                event.getItemDrop().remove();
            }
        }
    }

}


