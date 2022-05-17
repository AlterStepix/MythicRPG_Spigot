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
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InfernalAuraScroll implements Listener {
    public Mythicrpg main;
    ItemLoreLibrary lib;

    public InfernalAuraScroll(Mythicrpg main) {
        this.main = main;
        lib = new ItemLoreLibrary(main);
        lib.Init();

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("InfernalAura").get(1))) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                new BukkitRunnable() {
                    int i = 0;
                    @Override
                    public void run() {
                        if(i > 7)
                            cancel();
                        player.getWorld().spawnParticle(Particle.FLAME,player.getLocation(),20,0,1,0,0.2);
                        for(Entity en :player.getNearbyEntities(7,7,7))
                        {
                            if(en instanceof LivingEntity trg)
                            {
                                trg.damage(5);
                            }
                        }
                        i++;
                    }
                }.runTaskTimer(main, 0L, 20L);
            }
        }
    }
}
