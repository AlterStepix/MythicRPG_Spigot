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
import alterstepix.mythicrpg.util.GetPlayerHead;
import alterstepix.mythicrpg.util.MobItemCreator;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DarknessIncarnate implements Listener {

    public static ArrayList<LivingEntity> bosses = new ArrayList<>();

    public LivingEntity summon(Location location)
    {
        Skeleton boss = location.getWorld().spawn(location,Skeleton.class);
        bosses.add(boss);
        boss.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
        boss.getEquipment().setChestplate(MobItemCreator.leather(Material.LEATHER_CHESTPLATE,0,0,0,0));
        boss.getEquipment().setHelmet(GetPlayerHead.GetCustomHead(GetPlayerHead.DarknessIncarnateHead));

        boss.setCustomName(ColorUtil.ConvertToCustom(Mythicrpg.INSTANCE.getConfig().getString("DarknessIncarnateNametag")));
        boss.setCustomNameVisible(true);

        boss.setInvisible(true);
        //boss.setInvulnerable(true);

        AttributeInstance health = boss.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        health.setBaseValue(Mythicrpg.INSTANCE.getConfig().getInt("DarknessIncarnateHealth"));
        boss.setHealth(health.getBaseValue());

        AttributeInstance armor = boss.getAttribute(Attribute.GENERIC_ARMOR);
        armor.setBaseValue(95);


        new BukkitRunnable()
        {
            Particle.DustTransition tr = new Particle.DustTransition(Color.BLACK,Color.PURPLE,1.2f);
            @Override
            public void run() {
                boss.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,boss.getEyeLocation(),7,0.5,0.5,0.5,tr);
                if(boss.isDead())
                    cancel();
            }
        }.runTaskTimer(Mythicrpg.INSTANCE,0L,2L);

        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                i++;

                if(boss.isDead())
                {
                    boss.remove();
                    cancel();
                }

                for(Entity entity : boss.getNearbyEntities(12,12,12))
                {
                    if(entity instanceof LivingEntity target)
                    {
                        target.damage(20);
                        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60,12,false,false,false));
                    }
                }

                if(i % 12 == 0)
                {
                    new BukkitRunnable()
                    {
                        int j = 0;
                        @Override
                        public void run() {
                            for(Entity entity : boss.getNearbyEntities(12,12,12))
                            {
                                if(entity instanceof LivingEntity target)
                                {
                                    target.damage(50);
                                    target.getWorld().strikeLightningEffect(target.getLocation());
                                }
                            }

                            if(j > 10)
                                this.cancel();

                            j++;
                        }
                    }.runTaskTimer(Mythicrpg.INSTANCE,0L,2L);
                }

                if(i > 16)
                {
                    if(i % 17 == 0)
                    {
                        boss.setInvulnerable(true);
                        boss.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,40,1,false,false,false));
                        boss.setGravity(false);
                    }
                    if(i % 17 == 3)
                    {
                        Particle.DustTransition tr = new Particle.DustTransition(Color.GRAY,Color.BLACK,2f);
                        Particle.DustTransition tr2 = new Particle.DustTransition(Color.PURPLE,Color.BLACK,2f);
                        Particle.DustTransition tr3 = new Particle.DustTransition(Color.GRAY,Color.SILVER,2f);
                        boss.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,boss.getEyeLocation(),80,1.5,1.5,0.5,tr);
                        boss.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,boss.getEyeLocation(),80,1.5,1.5,0.5,tr3);
                        boss.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,boss.getEyeLocation(),80,1.9,1.9,0.9,tr2);
                        boss.getWorld().playSound(boss.getLocation(), Sound.ENTITY_WITHER_SHOOT,5,5);
                        for (int i = 0; i < 15; i++) {
                            ItemStack item = new ItemStack(Material.FLINT);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(i + "");
                            item.setItemMeta(meta);
                            Item web = boss.getWorld().dropItem(boss.getLocation(), item);
                            //web.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3), ThreadLocalRandom.current().nextDouble(0.7, 1.2), 0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI * 2)));

                            web.setCustomName("§3DE_FLINT");

                            web.setVelocity(new Vector(ThreadLocalRandom.current().nextDouble(-2,2),Math.random()*0.3,ThreadLocalRandom.current().nextDouble(-2,2)));

                            new BukkitRunnable()
                            {
                                Particle.DustTransition tr4 = new Particle.DustTransition(Color.PURPLE,Color.BLACK,0.3f);
                                int i =0;
                                @Override
                                public void run() {
                                    if(web.isDead() || !web.isValid() || i>200)
                                    {
                                        this.cancel();
                                        web.remove();
                                    }

                                    if(boss.getTarget() != null)
                                        web.setVelocity(boss.getTarget().getLocation().add(0,1,0).subtract(web.getLocation()).toVector().multiply(0.05));


                                    web.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,web.getLocation(),3,0,0,0,0,tr4);

                                    i++;

                                }
                            }.runTaskTimer(Mythicrpg.INSTANCE,5L,2L);

                        }

                    }
                    if(i % 17 == 4)
                    {
                        Particle.DustTransition tr = new Particle.DustTransition(Color.GRAY,Color.BLACK,2f);
                        Particle.DustTransition tr2 = new Particle.DustTransition(Color.PURPLE,Color.BLACK,2f);
                        Particle.DustTransition tr3 = new Particle.DustTransition(Color.GRAY,Color.SILVER,2f);
                        boss.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,boss.getEyeLocation(),80,1.5,1.5,0.5,tr);
                        boss.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,boss.getEyeLocation(),80,1.5,1.5,0.5,tr3);
                        boss.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,boss.getEyeLocation(),80,1.9,1.9,0.9,tr2);

                        boss.setInvulnerable(false);
                        boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,60,1,false,false));
                        boss.setGravity(true);
                    }
                }

                if(i % 15 == 6)
                {
                    for(Entity entity : boss.getNearbyEntities(17,17,17))
                    {
                        if(entity instanceof LivingEntity trg)
                        {

                            Vibration vibration = new Vibration(boss.getEyeLocation(), new Vibration.Destination.EntityDestination(trg),5);

                            new BukkitRunnable()
                            {
                                int j = 0;
                                @Override
                                public void run() {
                                    boss.getWorld().playSound(boss.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK,5,5);
                                    boss.getWorld().spawnParticle(Particle.VIBRATION,boss.getLocation(),1,vibration);

                                    if( j > 16)
                                        this.cancel();

                                    trg.damage(50);

                                    j++;
                                }

                            }.runTaskTimer(Mythicrpg.INSTANCE,0L,2L);


                        }
                    }
                }
                if(i % 4 == 0 && !boss.isInvulnerable() && boss.hasGravity())
                {
                    for(Entity entity : boss.getNearbyEntities(12,12,12))
                    {
                        if(entity instanceof LivingEntity target)
                        {
                            Vector offset = target.getLocation().getDirection().setY(0).normalize().multiply(-1);
                            if(target.getLocation().add(offset).getBlock().isPassable())
                            {
                                boss.teleport(boss.getTarget().getLocation().add(offset));
                                boss.getWorld().playSound(boss.getLocation(), Sound.ENTITY_WITHER_SHOOT,5,5);
                                boss.getWorld().spawnParticle(Particle.SQUID_INK,boss.getLocation(),3);
                                target.damage(70);
                            }
                            else
                            {
                                target.setVelocity((boss.getLocation().add(0,2,0).subtract(target.getLocation()).toVector().multiply(0.275)));
                                boss.getWorld().spawnParticle(Particle.SPELL_WITCH, boss.getLocation(),5,0,0,0,0.2);
                                boss.getWorld().playSound(boss.getLocation(),Sound.BLOCK_ANVIL_BREAK,5,5);
                            }
                        }
                    }
                }


            }
        }.runTaskTimer(Mythicrpg.INSTANCE,0L,20L);


        return boss;
    }

    @EventHandler
    public void deDamageByLEEvent(EntityDamageByEntityEvent event)
    {
        FileConfiguration config = Mythicrpg.INSTANCE.getConfig();
        if(event.getDamager() instanceof Player player)
        {
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().contains(config.getString("DarknessIncarnateNametag").split("!")[1]) && event.getEntity() instanceof LivingEntity trg)
            {
                player.sendTitle(ColorUtil.ConvertToCustom(config.getString("DarknessIncarnateNametag")),"§c"+(int)trg.getHealth()+"♥ / "+(int)trg.getMaxHealth()+"♥",5,10,5);
            }
        }
    }


    @EventHandler
    public void Pickup_item(EntityPickupItemEvent e)
    {
        if(e.getEntity() instanceof Player p)
        {
            if(e.getItem().getCustomName() != null &&e.getItem().getCustomName().contains("§3DE_FLINT"))
            {
                e.setCancelled(true);
                e.getItem().remove();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,4,false,false,false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,60,2,false,false,false));
                p.setFireTicks(p.getFireTicks() + 25);
                p.damage(50);

                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT,5,5);

                for(LivingEntity boss : bosses)
                {
                    if(boss.getWorld().getBlockAt(p.getLocation().subtract(p.getLocation().getDirection().normalize())).isPassable())
                    {
                        boss.teleport(p.getLocation().subtract(p.getLocation().getDirection().normalize()));
                    }
                    else
                    {
                        boss.teleport(p.getLocation());
                    }
                }


            }
        }
    }

}
