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
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ThiefArmor implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;

    public ThiefArmor(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        this.lib.Init();
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
                        if(player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("TA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("TA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("TA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("TA").get(1)))
                        {
                            if(e.getCause() == EntityDamageEvent.DamageCause.FALL)
                            {
                                e.setCancelled(true);
                            }
                            if(e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)
                            {
                                if(Math.random() < 0.2)
                                {
                                    e.setCancelled(true);
                                    player.getWorld().playSound(player.getLocation(), Sound.ITEM_SHIELD_BLOCK,5,5);
                                    player.getWorld().spawnParticle(Particle.CRIT,player.getLocation(),10,0,0,0,1);
                                }
                            }

                        }
                    }
                }
            }
        }
    }

}
