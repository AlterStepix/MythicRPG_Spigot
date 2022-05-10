package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.GetPlayerHead;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CursedEmperor implements Listener {
    Mythicrpg main;
    FileConfiguration config;
    int Phase = 0;

    public CursedEmperor(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createCursedEmperor(Location loc)
    {
        Skeleton emperor = loc.getWorld().spawn(loc,Skeleton.class);

        AttributeInstance attHp = emperor.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attHp.setBaseValue(config.getInt("CursedEmperorBossHealth"));
        emperor.setHealth(config.getInt("CursedEmperorBossHealth"));

        AttributeInstance attDef = emperor.getAttribute(Attribute.GENERIC_ARMOR);
        attDef.setBaseValue(40);

        emperor.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 99999, 1, false,false,false));

        ItemStack cp = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta)cp.getItemMeta();
        meta.setColor(Color.fromRGB(96,129 ,120));
        meta.setUnbreakable(true);
        cp.setItemMeta(meta);
        ItemStack lg = new ItemStack(Material.LEATHER_LEGGINGS,1);
        ItemStack bt = new ItemStack(Material.LEATHER_BOOTS,1);
        lg.setItemMeta(meta);
        bt.setItemMeta(meta);
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        ItemMeta m = sword.getItemMeta();
        m.setUnbreakable(true);
        sword.setItemMeta(m);

        ItemStack head = GetPlayerHead.GetCustomHead(GetPlayerHead.CursedEmperorHead);
        emperor.getEquipment().setHelmet(head);
        emperor.getEquipment().setChestplate(cp);
        emperor.getEquipment().setLeggings(lg);
        emperor.getEquipment().setBoots(bt);
        emperor.getEquipment().setItemInMainHand(sword);

        emperor.setCustomName(ColorUtil.ConvertToCustom(config.getString("BossPrefix"))+" "+ColorUtil.ConvertToCustom(config.getString("CursedEmperorBossNametag")));
        emperor.setCustomNameVisible(true);
        BossBar bar = Bukkit.createBossBar(ColorUtil.ConvertToCustom(config.getString("BossPrefix"))+ColorUtil.ConvertToCustom(config.getString("NetherLordBossNametag")), BarColor.RED, BarStyle.SEGMENTED_10);

        for(int i = 0; i <3; i++)
            createHealer(emperor.getLocation());
        new BukkitRunnable()
        {
            int i = 0;
            public void run()
            {
                if(!emperor.isDead())
                {
                    for(Entity e : emperor.getNearbyEntities(30,30,30))
                    {
                        if(e instanceof Player)
                            bar.addPlayer((Player)e);
                    }
                    for(Player p : Bukkit.getOnlinePlayers())
                    {
                        if(p.getLocation().distanceSquared(emperor.getLocation()) > 1000)
                        {
                            bar.removePlayer(p);
                        }
                        else
                        {
                            bar.addPlayer(p);
                        }
                    }

                    bar.setProgress(emperor.getHealth()/emperor.getMaxHealth());
                    bar.setTitle(ColorUtil.ConvertToCustom(config.getString("BossPrefix"))+ColorUtil.ConvertToCustom(config.getString("CursedEmperorBossNametag")) +" §7["+Math.round(emperor.getHealth())+"/"+emperor.getMaxHealth()+"]");

                    if(emperor.getTarget() != null) {
                        if (i % 8 == 0)
                        {
                            for (Entity entity : emperor.getNearbyEntities(7, 7, 7)) {
                                if (entity instanceof LivingEntity trg) {
                                    trg.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 70, 2, true, true, true));
                                    trg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 70, 2, true, true, true));
                                }
                            }
                        }
                        if (i % 6 == 0)
                        {
                            Random r = new Random();
                            emperor.getWorld().playSound(emperor.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON,5,5);
                            for(int x = 0; x < 2; x++)
                            {
                                Silverfish rat = emperor.getWorld().spawn(emperor.getLocation().add(r.nextInt(1 + 1) -1, 0, r.nextInt(1 + 1) -1),Silverfish.class);
                                rat.setTarget(emperor.getTarget());
                                rat.setCustomName(ColorUtil.ConvertToCustom(config.getString("RatNametag")));
                                rat.setCustomNameVisible(true);
                                rat.setMaxHealth(config.getInt("RatHealth"));
                                rat.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,999999,4,false,false,false));
                                AttributeInstance speedRat = rat.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                                speedRat.setBaseValue(0.5);

                            }
                        }
                        if(i % 16 == 0)
                        {
                            Random r = new Random();
                            emperor.getWorld().playSound(emperor.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON,5,5);
                            for(int x = 0; x < 6; x++)
                            {
                                Skeleton lg = emperor.getWorld().spawn(emperor.getLocation().add(r.nextInt(1 + 1) -1, 0, r.nextInt(1 + 1) -1),Skeleton.class);
                                lg.setTarget(emperor.getTarget());
                                lg.setCustomName(ColorUtil.ConvertToCustom(config.getString("LegionaryNametag")));
                                lg.setCustomNameVisible(true);
                                lg.setMaxHealth(config.getInt("LegionaryHealth"));
                                lg.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
                                lg.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                                lg.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                                lg.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                                lg.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
                                lg.setVelocity(emperor.getTarget().getLocation().add(0,1,0).subtract(lg.getLocation()).toVector().multiply(0.2));
                                lg.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,99999,6,false,false,false));
                            }
                        }
                        if(i % 8 == 0)
                        {
                            for (int i = 0; i < 5; i++) {
                                ItemStack item = new ItemStack(Material.GOLD_INGOT);
                                ItemMeta meta = item.getItemMeta();
                                meta.setDisplayName(i + "");
                                item.setItemMeta(meta);
                                Item web = emperor.getWorld().dropItem(emperor.getLocation(), item);
                                web.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3), ThreadLocalRandom.current().nextDouble(0.7, 1.2), 0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI * 2)));

                                web.setCustomName("§3CursedGold");
                            }
                        }
                        if(i % 18 == 0)
                        {
                            createHealer(emperor.getLocation());
                        }

                    }

                }
                else
                {
                    emperor.getWorld().playSound(emperor.getLocation(),Sound.ENTITY_WITHER_DEATH,5,5);
                    for(int i = 0; i < 3; i++)
                        emperor.getWorld().strikeLightningEffect(emperor.getLocation());
                    bar.removeAll();
                    emperor.remove();
                    cancel();
                }
                i++;

            }
        }.runTaskTimer(main,0L,20L);
    }

    @EventHandler
    public void Pickup_item(EntityPickupItemEvent e)
    {
        if(e.getEntity() instanceof Player p)
        {
            if(e.getItem().getCustomName() != null &&e.getItem().getCustomName().contains("§3CursedGold"))
            {
                e.setCancelled(true);
                e.getItem().remove();
                p.setFireTicks(p.getFireTicks()+40);
                p.damage(12);

            }
        }
    }

    @EventHandler
    public void onHitEvent(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof LivingEntity entity)
        {
            if(entity.getCustomName() != null && entity.getCustomName().contains(config.getString("CursedEmperorBossNametag").split("!")[1]))
            {
                ItemStack item = new ItemStack(Material.GOLD_INGOT);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("cursedGold");
                item.setItemMeta(meta);
                Item web = entity.getWorld().dropItem(entity.getLocation(), item);
                web.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3), ThreadLocalRandom.current().nextDouble(0.7, 1.2), 0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI * 2)));

                web.setCustomName("§3CursedGold");
            }
        }
        if(e.getDamager() instanceof LivingEntity entity && e.getEntity() instanceof LivingEntity trg)
        {
            if(entity.getCustomName() != null && entity.getCustomName().contains(config.getString("RatNametag").split("!")[1]))
            {
                trg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,2,true,true,true));
            }
            if(entity.getCustomName() != null && entity.getCustomName().contains(config.getString("LegionaryNametag").split("!")[1]))
            {
                trg.setVelocity(new Vector(0,0.6,0));
            }
        }
    }

    public void createHealer(Location loc)
    {
        Zombie healer = loc.getWorld().spawn(loc, Zombie.class);
        healer.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        healer.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        healer.getEquipment().setItemInMainHand(new ItemStack(Material.GOLDEN_SWORD));
        healer.setMaxHealth(config.getInt("AncientPriestHealth"));

        AttributeInstance def = healer.getAttribute(Attribute.GENERIC_ARMOR);
        def.setBaseValue(35);

        healer.setCustomNameVisible(true);

        new BukkitRunnable()
        {
            int i =0;
            public void run()
            {
                if(!healer.isDead())
                {
                    healer.setCustomName(ColorUtil.ConvertToCustom(config.getString("AncientPriestName")) + " §7["+Math.round(healer.getHealth())+"/"+healer.getMaxHealth()+"]");

                    if(i % 2 == 0)
                    {
                        for (Entity e : healer.getNearbyEntities(10,10,10))
                        {
                            if(e instanceof Player player)
                            {
                                player.damage(6);
                                player.getWorld().spawnParticle(Particle.VILLAGER_ANGRY,player.getLocation(),10,0,0,0);
                            }
                            else if(e instanceof LivingEntity trg)
                            {
                                if(trg.getMaxHealth() - trg.getHealth() > 2 && trg.getHealth() > 0)
                                    trg.setHealth(trg.getHealth()+1);
                                trg.getWorld().spawnParticle(Particle.VILLAGER_HAPPY,trg.getEyeLocation(),10,0,0,0);
                            }
                        }
                    }

                    i++;
                }
                else {
                    healer.remove();
                    cancel();
                }
            }
        }.runTaskTimer(main,0L,20L);
    }
}
