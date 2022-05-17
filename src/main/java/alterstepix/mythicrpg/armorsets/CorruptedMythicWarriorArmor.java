/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.armorsets;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class CorruptedMythicWarriorArmor implements Listener {
    ItemLoreLibrary lib;
    Mythicrpg main;

    public CorruptedMythicWarriorArmor(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        lib.Init();

    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player player)
        {
            if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null)
            {
                if(player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getBoots().getItemMeta() != null)
                {
                    if(player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null && player.getInventory().getBoots().getItemMeta().getLore() != null)
                    {
                        if(player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("CMWA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("CMWA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("CMWA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("CMWA").get(1)))
                        {
                            if(Math.random() < 0.45)
                            {
                                e.setCancelled(true);
                                player.getWorld().playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK,5,5);
                                player.getWorld().spawnParticle(Particle.CRIT,player.getLocation(),10,0,0,0,1);
                            }
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,60,3,true,true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,60,1,true,true));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player player)
        {
            if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null)
            {
                if(player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getBoots().getItemMeta() != null)
                {
                    if(player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null && player.getInventory().getBoots().getItemMeta().getLore() != null)
                    {
                        if(player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("CMWA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("CMWA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("CMWA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("CMWA").get(1)))
                        {
                            e.setDamage(e.getDamage()*1.5);
                            if(Math.random() < 0.2)
                            {
                                if(e.getEntity() instanceof LivingEntity target)
                                {

                                    player.sendMessage("§7You just applied §5Corruption");
                                    new BukkitRunnable()
                                    {
                                        int i =0;
                                        @Override
                                        public void run() {
                                            i++;
                                            target.damage(20,player);

                                            if(i >= 7)
                                                cancel();
                                        }
                                    }.runTaskTimer(main,0L,20L);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
