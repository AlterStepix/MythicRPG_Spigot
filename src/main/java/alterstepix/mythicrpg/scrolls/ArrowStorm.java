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
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

import java.util.List;
import java.util.Random;

public class ArrowStorm implements Listener {
    Mythicrpg main;
    ItemLoreLibrary lib;

    public ArrowStorm(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("ArrowStorm").get(1))) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
                new BukkitRunnable()
                {
                    Random rnd = new Random();
                    int i = 0;
                    @Override
                    public void run() {
                        for(int j = 0; j < 7; j++)
                        {
                            Arrow arrow = player.getWorld().spawn(player.getLocation().add(player.getLocation().getDirection().normalize().multiply(1.5).add(new Vector(0,1,0))), Arrow.class);
                            Double rotation = ThreadLocalRandom.current().nextDouble(-1, 1)*0.2;
                            arrow.setVelocity(player.getLocation().getDirection().normalize().multiply(rnd.nextDouble()+1.5).rotateAroundY(rotation).multiply(new Vector(1,ThreadLocalRandom.current().nextDouble(0, 1),1)).add(new Vector(0,0.4,0)));
                            arrow.setVelocity(arrow.getVelocity().multiply(2));
                            arrow.setDamage(6);

                        }
                        if(i > 7)
                            cancel();
                        i++;
                    }
                }.runTaskTimer(main,0L,2L);
            }
        }
    }
}
