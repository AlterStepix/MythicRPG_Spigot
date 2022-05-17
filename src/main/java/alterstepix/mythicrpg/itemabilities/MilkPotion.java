/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class MilkPotion implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;

    public MilkPotion(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void PotionSplashEvent(PotionSplashEvent e)
    {
        ThrownPotion pot = e.getPotion();
        ItemStack item = pot.getItem();
        if(item.getItemMeta() != null && item.getItemMeta().getLore() != null && item.getItemMeta().getLore().contains(lib.Lore.get("Milk").get(1)))
        {
            for(Entity enitiy : e.getAffectedEntities())
            {
                if(enitiy instanceof LivingEntity)
                {
                    LivingEntity trg = (LivingEntity) enitiy;
                    for(PotionEffect eff : trg.getActivePotionEffects())
                    {
                        trg.removePotionEffect(eff.getType());
                        trg.getWorld().playSound(trg.getLocation(), Sound.ENTITY_GENERIC_DRINK,5,5);
                        trg.getWorld().spawnParticle(Particle.END_ROD,trg.getLocation().add(0,1,0),30);
                    }
                }
            }
        }
    }

}


