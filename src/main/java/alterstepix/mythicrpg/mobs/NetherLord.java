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
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NetherLord implements Listener {
    Mythicrpg main;
    FileConfiguration config;
    WitherSkeleton bossEntity;
    double Healing = 0;
    List<Entity> BossSpecial = new ArrayList<Entity>();
    int Phase = 1;
    boolean AlreadyPhaseII = false;
    boolean AlreadyPhaseIII = false;

    public NetherLord(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createNetherLord(Location loc)
    {
        WitherSkeleton boss = loc.getWorld().spawn(loc,WitherSkeleton.class);
        bossEntity = boss;
        boss.setCustomName(ColorUtil.ConvertToCustom(config.getString("BossPrefix"))+" "+ColorUtil.ConvertToCustom(config.getString("NetherLordBossNametag")));
        boss.setCustomNameVisible(true);
        Attributable bAt = boss;

        AttributeInstance hpAt = bAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        hpAt.setBaseValue(config.getInt("NetherLordBossHealth"));
        boss.setHealth(config.getInt("NetherLordBossHealth"));

        AttributeInstance hpDef = bAt.getAttribute(Attribute.GENERIC_ARMOR);
        hpDef.setBaseValue(50);

        AttributeInstance KbAt = bAt.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        KbAt.setBaseValue(100);

        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD,1);
        ItemMeta meta = sword.getItemMeta();
        meta.addEnchant(Enchantment.DAMAGE_ALL,20,true);
        sword.setItemMeta(meta);

        boss.getEquipment().setItemInMainHand(sword);

        SkeletonHorse horse = loc.getWorld().spawn(loc,SkeletonHorse.class);
        Attributable hAt = horse;
        AttributeInstance KbHaT = hAt.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        KbHaT.setBaseValue(100);

        horse.setInvulnerable(true);
        horse.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,9999999,10,false,false,false));
        horse.addPassenger(boss);

        boss.getEquipment().setHelmet(GetPlayerHead.GetCustomHead(GetPlayerHead.WitherusNetherlordHead));

        BossBar bar = Bukkit.createBossBar(ColorUtil.ConvertToCustom(config.getString("BossPrefix"))+ColorUtil.ConvertToCustom(config.getString("NetherLordBossNametag")), BarColor.RED, BarStyle.SEGMENTED_10);

        createHealer(boss.getLocation().add(0,3.5,0));

        new BukkitRunnable()
        {
            int i = 0;
            public void run()
            {
                if(!boss.isDead())
                {
                    for(Entity e : boss.getNearbyEntities(30,30,30))
                    {
                        if(e instanceof Player)
                            bar.addPlayer((Player)e);
                    }
                    for(Player p : Bukkit.getOnlinePlayers())
                    {
                        if(p.getLocation().distanceSquared(boss.getLocation()) > 1000)
                        {
                            bar.removePlayer(p);
                        }
                        else
                        {
                            bar.addPlayer(p);
                        }
                    }

                    bar.setProgress(boss.getHealth()/boss.getMaxHealth());
                    bar.setTitle(ColorUtil.ConvertToCustom(config.getString("BossPrefix"))+ColorUtil.ConvertToCustom(config.getString("NetherLordBossNametag")) +" §7["+Math.round(boss.getHealth())+"/"+boss.getMaxHealth()+"]");

                    if(boss.getMaxHealth() - boss.getHealth() >= 3)
                        boss.setHealth(boss.getHealth()+Healing);


                    if(boss.getHealth() < 325 && boss.getHealth() > 100 && !AlreadyPhaseII)
                    {
                        Phase = 2;
                        boss.getWorld().playSound(boss.getLocation(),Sound.ENTITY_WITHER_DEATH,5,5);
                        AlreadyPhaseII = true;
                    }


                    if(boss.getHealth() < 125 && !AlreadyPhaseIII)
                    {
                        Phase = 3;
                        boss.getWorld().playSound(boss.getLocation(),Sound.ENTITY_WITHER_DEATH,5,5);
                        horse.remove();
                        boss.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,999999,2,false,false,false));
                        boss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,999999,1,false,false,false));
                        AlreadyPhaseIII = true;
                    }


                    if(boss.getTarget() != null)
                    {
                        if(i % 3 == 0)
                        {
                            for(int j = 0; j < 5; j++)
                            {
                                Double rotation = ThreadLocalRandom.current().nextDouble(-1.5, 1.5);
                                WitherSkull proj = boss.getWorld().spawn(boss.getLocation().add(boss.getLocation().getDirection().normalize().multiply(1.5).add(new Vector(0,1,0))),WitherSkull.class);
                                proj.setVelocity(boss.getTarget().getLocation().subtract(proj.getLocation()).toVector().normalize().multiply(7));
                                proj.setVelocity(proj.getDirection().rotateAroundY(rotation));
                                boss.getWorld().spawnParticle(Particle.SMOKE_NORMAL,boss.getEyeLocation(),1);
                            }

                        }

                        if(Phase == 2)
                        {
                            boss.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE,1));
                            boss.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS,1));
                            boss.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS,1));
                        }
                        else if(Phase == 3)
                        {
                            boss.getEquipment().setBoots(null);
                            boss.getEquipment().setLeggings(null);
                            boss.getEquipment().setBoots(null);
                        }

                        if(i % 4 == 0)
                        {
                            for(Entity entity : boss.getNearbyEntities(15,15,15))
                            {
                                if(entity instanceof Player)
                                {
                                    Player trg = (Player) entity;
                                    trg.setFireTicks(trg.getFireTicks()+40);
                                    trg.getWorld().spawnParticle(Particle.FLAME,trg.getEyeLocation(),30);
                                    trg.getWorld().playSound(trg.getLocation(),Sound.ENTITY_DRAGON_FIREBALL_EXPLODE,5,5);
                                }
                            }
                        }
                        if(i % 10 == 2)
                        {
                            for(Entity entity : boss.getNearbyEntities(15,15,15))
                            {
                                if(entity instanceof Player)
                                {
                                    entity.setVelocity(boss.getLocation().add(0,2,0).subtract(boss.getLocation()).toVector().normalize().multiply(-1).setY(0.3));
                                }
                            }
                        }
                        if(i % 2 == 0)
                        {
                            for(Entity entity : boss.getNearbyEntities(8,8,8))
                            {
                                if(entity instanceof Player)
                                {
                                    Player trg = (Player) entity;
                                    trg.damage(15);
                                    trg.getWorld().spawnParticle(Particle.CRIT_MAGIC,trg.getEyeLocation(),30);
                                    trg.getWorld().playSound(trg.getLocation(),Sound.ENTITY_GENERIC_EXPLODE,5,5);
                                }
                            }
                        }
                        if(i % 15 == 5)
                        {
                            for(int x = 0; x < 2; x++)
                            {
                                Random r = new Random();
                                WitherSpider summoned = new WitherSpider(main);
                                summoned.createLeapingSpider(boss.getLocation().add(r.nextInt(1 + 1) -1, 0, r.nextInt(1 + 1) -1));
                            }
                            if(Phase == 2)
                            {
                                Random r = new Random();
                                WitherSpider summoned = new WitherSpider(main);
                                summoned.createLeapingSpider(boss.getLocation().add(r.nextInt(1 + 1) -1, 0, r.nextInt(1 + 1) -1));
                            }
                        }
                        if(i % 15 == 0 && (Phase == 2 || Phase == 3))
                        {
                            if(Phase == 2)
                            {
                                boss.getWorld().createExplosion(boss.getLocation(),4,false,false);
                            }
                            if(Phase == 3)
                            {
                                new BukkitRunnable()
                                {
                                    int t = 0;
                                    public void run()
                                    {
                                        boss.getWorld().createExplosion(boss.getLocation(),6,false,false);
                                        if(t > 5)
                                            cancel();
                                        t++;
                                    }
                                }.runTaskTimer(main,0L,10L);
                            }
                        }
                        if(i % 50 == 0)
                        {
                            createHealer(boss.getLocation().add(0,3.5,0));
                        }
                        if(i % 50 == 43)
                        {
                            boss.getWorld().spawnParticle(Particle.LAVA,boss.getLocation(),15);
                            boss.getWorld().playSound(boss.getLocation(),Sound.ENTITY_BLAZE_DEATH,5,5);
                        }
                        if(i % 50 == 44)
                        {
                            boss.getWorld().spawnParticle(Particle.LAVA,boss.getLocation(),30);
                            boss.getWorld().playSound(boss.getLocation(),Sound.ENTITY_BLAZE_DEATH,8,5);
                        }
                        if(i % 50 == 45)
                        {
                            boss.getWorld().spawnParticle(Particle.LAVA,boss.getLocation(),45);
                            boss.getWorld().playSound(boss.getLocation(),Sound.ENTITY_BLAZE_DEATH,12,5);
                        }
                        if(i % 50 == 46 && boss.getTarget() != null) {
                            new BukkitRunnable()
                            {
                                int k =0;
                                public void run()
                                {
                                    if(boss.getTarget() != null)
                                    {
                                        for(int j = 0; j < 7; j++)
                                        {
                                            Double rotation = ThreadLocalRandom.current().nextDouble(-3, 3);
                                            WitherSkull proj = boss.getWorld().spawn(boss.getLocation().add(boss.getLocation().getDirection().normalize().multiply(1.5).add(new Vector(0,1,0))),WitherSkull.class);
                                            proj.setVelocity(boss.getTarget().getLocation().subtract(proj.getLocation()).toVector().normalize().multiply(7));
                                            proj.setVelocity(proj.getDirection().rotateAroundY(rotation));
                                            boss.getWorld().spawnParticle(Particle.SMOKE_NORMAL,boss.getEyeLocation(),1);
                                        }
                                        for(int j = 0; j < 7; j++)
                                        {
                                            Double rotation = ThreadLocalRandom.current().nextDouble(-2, 2);
                                            Fireball proj = boss.getWorld().spawn(boss.getLocation().add(boss.getLocation().getDirection().normalize().multiply(1.5).add(new Vector(0,1,0))),Fireball.class);
                                            proj.setVelocity(boss.getTarget().getLocation().subtract(proj.getLocation()).toVector().normalize().multiply(7));
                                            proj.setVelocity(proj.getDirection().rotateAroundY(rotation));
                                            boss.getWorld().spawnParticle(Particle.SMOKE_NORMAL,boss.getEyeLocation(),1);
                                        }
                                        for(int j = 0; j < 7; j++)
                                        {
                                            Double rotation = ThreadLocalRandom.current().nextDouble(-1, 1);
                                            Arrow proj = boss.getWorld().spawn(boss.getLocation().add(boss.getLocation().getDirection().normalize().multiply(1.5).add(new Vector(0,1,0))),Arrow.class);
                                            proj.setVelocity(boss.getTarget().getLocation().subtract(proj.getLocation()).toVector().normalize().multiply(3).rotateAroundY(rotation));
                                            boss.getWorld().spawnParticle(Particle.SMOKE_NORMAL,boss.getEyeLocation(),1);
                                        }
                                        if(k > 5)
                                            cancel();
                                    }
                                    k++;
                                }
                            }.runTaskTimer(main,0L,2L);
                        }

                        if(Phase == 2)
                        {
                            horse.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,99999,1,false,false,false));
                        }

                    }

                }
                else
                {
                    bar.removeAll();
                    boss.remove();
                    horse.remove();
                    cancel();
                    for(Entity ent : BossSpecial)
                    {
                        ent.remove();
                    }
                }
                i++;
            }
        }.runTaskTimer(main,0L,20L);

    }
    public void createHealer(Location loc)
    {
        Blaze healer = loc.getWorld().spawn(loc,Blaze.class);

        healer.setMaxHealth(config.getInt("NetherHealerHealth"));
        healer.setHealth(config.getInt("NetherHealerHealth"));
        healer.setCustomName(ColorUtil.ConvertToCustom(config.getString("NetherHealerNametag"))+" ["+healer.getHealth()+"/"+healer.getMaxHealth()+"]");
        healer.setCustomNameVisible(true);

        Attributable at = healer;
        AttributeInstance atArmor = healer.getAttribute(Attribute.GENERIC_ARMOR);
        atArmor.setBaseValue(70);

        BossSpecial.add(healer);
        Guardian laser = loc.getWorld().spawn(loc,Guardian.class);
        BossSpecial.add(laser);
        laser.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,9999999,1,false,false));
        laser.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,9999999,10,false,false));
        healer.addPassenger(laser);
        laser.setAI(false);
        healer.setAI(false);
        Healing += 3;
        new BukkitRunnable()
        {
            public void run()
            {
                if(!healer.isDead())
                {
                    healer.setCustomName(ColorUtil.ConvertToCustom(config.getString("NetherHealerNametag"))+" ["+healer.getHealth()+"/"+healer.getMaxHealth()+"]");
                    laser.setLastDamage(0);
                    laser.setTarget(bossEntity);
                    laser.setLaser(true);
                }
                else
                {
                    Healing -= 3;
                    laser.remove();
                    healer.remove();
                    cancel();
                }


            }
        }.runTaskTimer(main,0L,1L);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e)
    {
        if(e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)
        {
            if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("NetherLordBossNametag").split("!")[1]))
            {
                e.setCancelled(true);
            }
        }
        if(e.getEntity().getCustomName() != null && e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE && Phase!=3 &&  e.getEntity().getCustomName().contains(config.getString("NetherLordBossNametag").split("!")[1]))
        {
            e.setCancelled(true);
        }
    }

}
