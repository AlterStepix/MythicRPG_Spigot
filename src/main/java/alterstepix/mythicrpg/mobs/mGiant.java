/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.MobItemCreator;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class mGiant implements Listener {

    Mythicrpg main;
    FileConfiguration config;

    public mGiant(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public LivingEntity summon(Location location)
    {
        Zombie AI_Controller = location.getWorld().spawn(location,Zombie.class);
        AI_Controller.setInvulnerable(true);
        AI_Controller.setInvisible(true);

        Giant giant = location.getWorld().spawn(location, Giant.class);
        giant.addPassenger(AI_Controller);

        AttributeInstance giantHealth = giant.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        giantHealth.setBaseValue(config.getInt("GiantHealth"));
        giant.setHealth(config.getInt("GiantHealth"));

        AttributeInstance giantDef = giant.getAttribute(Attribute.GENERIC_ARMOR);
        giantDef.setBaseValue(75);

        AttributeInstance giantSpeed = giant.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        giantSpeed.setBaseValue(0.25);

        giant.setCustomName(ColorUtil.ConvertToCustom(config.getString("GiantNametag")));

        giant.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.IRON_SWORD,0));

        Random r = new Random();

        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                if(!giant.isDead()) {

                    // Default Damage Section
                    for(Player p : Bukkit.getOnlinePlayers())
                    {
                        if(p.getLocation().distanceSquared(giant.getLocation()) < 144)
                        {
                            p.damage(14);
                        }
                    }

                    // Mob Spawning Section

                    if (i % 15 == 0) {
                        for (int x = 0; x < 3; x++) {
                            AncientZombie summoned = new AncientZombie(main);
                            LivingEntity s = summoned.createAncientZombie(giant.getLocation().add(r.nextInt(1 + 1) - 1, 0, r.nextInt(1 + 1) - 1));
                            if (AI_Controller.getTarget() != null)
                                s.setVelocity(AI_Controller.getTarget().getLocation().subtract(s.getLocation()).toVector().multiply(0.275).normalize().add(new Vector(0, 0.4, 0)));
                        }
                    }
                    if (i % 25 == 0) {
                        for (int x = 0; x < 2; x++) {
                            Cyclops summoned = new Cyclops(main);
                            summoned.summon(giant.getLocation().add(r.nextInt(1 + 1) - 1, 0, r.nextInt(1 + 1) - 1));
                        }
                    }
                    if (i % 20 == 0) {
                        for (int x = 0; x < 2; x++) {
                            WatchingEye summoned = new WatchingEye(main);
                            summoned.summon(giant.getLocation().add(r.nextInt(1 + 1) - 1, 0, r.nextInt(1 + 1) - 1));
                        }
                    }

                    // Other Abilities

                    if (i % 17 == 5) {
                        giant.setVelocity(new Vector(0, 1.4, 0));
                        giant.getWorld().playSound(giant.getLocation(), Sound.ENTITY_WITHER_SHOOT, 5, 5);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (giant.isDead()) {
                                    cancel();
                                }
                                if (giant.isOnGround()) {
                                    giant.getWorld().spawnParticle(Particle.CRIT, giant.getLocation(), 100, 0, 0, 0, 3);
                                    giant.getWorld().playSound(giant.getLocation(), Sound.ENTITY_ENDER_DRAGON_HURT, 5, 5);
                                    for (Entity ent : giant.getNearbyEntities(12, 12, 12)) {
                                        if (ent instanceof Player target) {
                                            target.damage(75);
                                            target.setVelocity(new Vector(0, 1, 0));
                                        }
                                    }
                                    cancel();
                                }
                            }
                        }.runTaskTimer(main, 15L, 2L);
                    }
                    if (i % 13 == 0 || i % 14 == 0 || i % 15 == 0 || i % 16 == 0) {
                        for(Entity entity : giant.getNearbyEntities(12,12,12))
                        {
                            if(entity instanceof LivingEntity target)
                            {

                                if (target.getCustomName() != null && !target.getCustomName().contains(config.getString("AncientZombieNametag").split("!")[1]) && !target.getCustomName().contains(config.getString("CyclopsNametag").split("!")[1])
                                        && !target.getCustomName().contains(config.getString("WatchingEyeNametag").split("!")[1]))
                                {
                                    target.damage(40);
                                }
                            }

                        }
                    }

                    if(i % 19 == 7)
                    {
                        Giant tSword = giant.getWorld().spawn(giant.getLocation().add(r.nextInt(4 + 4) - 4, 0, r.nextInt(4 + 4) - 4), Giant.class);
                        giant.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
                        giant.getWorld().spawnParticle(Particle.CRIT, giant.getLocation(), 100, 0, 0, 0, 3);
                        tSword.setInvisible(true);
                        tSword.setAI(false);
                        tSword.setCustomName("Dinnerbone");
                        tSword.setInvulnerable(true);
                        tSword.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.IRON_SWORD,0));
                        for(Entity entity : tSword.getNearbyEntities(12,12,12))
                        {
                            if(entity instanceof Player target)
                            {
                                    target.damage(100);
                                    target.getWorld().spawnParticle(Particle.CRIT_MAGIC,target.getLocation(),10);
                                    target.getWorld().playSound(target.getLocation(),Sound.BLOCK_BELL_USE,15,5);
                            }

                        }
                        new BukkitRunnable()
                        {
                            int i = 0;
                            @Override
                            public void run() {
                                i++;
                                if(i > 5)
                                {
                                    giant.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.IRON_SWORD,0));
                                    tSword.remove();
                                    cancel();
                                }
                            }

                        }.runTaskTimer(main,0L,20L);
                    }

                    // Projectiles Abilities

                    if (i % 9 == 0)
                    {
                        for(int i = 0; i < 30; i++)
                        {
                            Arrow arrow = giant.getWorld().spawnArrow(giant.getEyeLocation().add(0,7,0),new Vector(0,0.5,0), 3, 5);
                        }
                    }

                    i++;
                }
                else
                {
                    giant.remove();
                    AI_Controller.remove();
                    cancel();
                }
            }
        }.runTaskTimer(main,0L,20L);

        return giant;
    }

    @EventHandler
    public void giantDamageEvent(EntityDamageEvent event)
    {
        if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains(config.getString("GiantNametag").split("!")[1]))
        {
            if(event.getCause() == EntityDamageEvent.DamageCause.FALL || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)
            {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void giantDamageByLEEvent(EntityDamageByEntityEvent event)
    {
        if(event.getDamager() instanceof Player player)
        {
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains(config.getString("GiantNametag").split("!")[1]) && event.getEntity() instanceof LivingEntity trg)
            {
                player.sendTitle(ColorUtil.ConvertToCustom(config.getString("GiantNametag")),"§c"+(int)trg.getHealth()+"♥ / "+(int)trg.getMaxHealth()+"♥",5,10,5);
            }
        }
    }

}
