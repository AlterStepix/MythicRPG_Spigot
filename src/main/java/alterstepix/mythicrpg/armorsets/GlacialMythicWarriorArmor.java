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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;


public class GlacialMythicWarriorArmor implements Listener {
    FileConfiguration config;
    ItemLoreLibrary lib;
    Mythicrpg main;
    public GlacialMythicWarriorArmor(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
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
                        if(player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("GMWA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("GMWA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("GMWA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("GMWA").get(1)))
                        {
                            if(Math.random() < 0.55)
                            {
                                e.setCancelled(true);
                                player.getWorld().playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK,5,5);
                                player.getWorld().spawnParticle(Particle.CRIT,player.getLocation(),10,0,0,0,1);
                            }
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,60,0,true,true));
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
                        if(player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("GMWA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("GMWA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("GMWA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("GMWA").get(1)))
                        {
                            e.setDamage(e.getDamage()*1.5);
                            if(Math.random() < 0.2)
                            {
                                if(e.getEntity() instanceof LivingEntity target)
                                {

                                    player.sendMessage("§7You just applied §bFreezing");
                                    new BukkitRunnable()
                                    {
                                        int i =0;
                                        @Override
                                        public void run() {
                                            i++;
                                            if(target.getPotionEffect(PotionEffectType.SLOW) == null)
                                            {
                                                PotionEffect newEff = new PotionEffect(PotionEffectType.SLOW, 40, 0,true,true,true);
                                                target.addPotionEffect(newEff);
                                            } else
                                            {
                                                PotionEffect effect = target.getPotionEffect(PotionEffectType.SLOW);
                                                PotionEffect newEff = new PotionEffect(PotionEffectType.SLOW, effect.getDuration()+40, effect.getAmplifier()+1,true,true,true);
                                                target.removePotionEffect(effect.getType());
                                                target.addPotionEffect(newEff);
                                                if(target.getCustomName() != null)
                                                {
                                                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bFreezing on "+target.getCustomName()+"§b: "+newEff.getAmplifier()+" for "+(newEff.getDuration()/20)+"s"));
                                                }

                                            }

                                            if(i >= 5)
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
