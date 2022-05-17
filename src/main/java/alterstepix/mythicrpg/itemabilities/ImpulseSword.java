/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ImpulseSword implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    Cooldown thiscd = new Cooldown();
    ItemLoreLibrary lib;

    public ImpulseSword(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        thiscd.init();
        lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Pull").get(1)) && !player.isSneaking()) {
                if (thiscd.checkCD(player)) {
                    int radius = config.getInt("impulseSwordRadius");
                    for(Entity entity : player.getNearbyEntities(radius,radius,radius))
                    {
                        if(entity instanceof LivingEntity)
                        {
                            LivingEntity target = (LivingEntity) entity;
                            target.getWorld().playSound(target.getLocation(), Sound.BLOCK_ANVIL_BREAK,5,5);
                            target.getWorld().spawnParticle(Particle.END_ROD,target.getLocation(),5,0,0,0,1);
                            target.setVelocity(player.getLocation().add(0,2,0).subtract(target.getLocation()).toVector().normalize());

                            thiscd.putCooldown(player,config.getInt("impulseSwordCooldown"));
                        }
                    }
                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                }
            }
            else if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Push").get(1)) && player.isSneaking()) {
                if (thiscd.checkCD(player)) {
                    int radius = config.getInt("impulseSwordRadius");
                    for(Entity entity : player.getNearbyEntities(radius,radius,radius))
                    {
                        if(entity instanceof LivingEntity)
                        {
                            LivingEntity target = (LivingEntity) entity;
                            target.getWorld().playSound(target.getLocation(), Sound.BLOCK_ANVIL_BREAK,5,5);
                            target.getWorld().spawnParticle(Particle.END_ROD,target.getLocation(),5);
                            target.setVelocity(target.getLocation().add(0,2,0).subtract(player.getLocation()).toVector().multiply(0.275));
                            thiscd.putCooldown(player,config.getInt("impulseSwordCooldown"));
                        }
                    }
                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                }
            }
        }
    }
}
