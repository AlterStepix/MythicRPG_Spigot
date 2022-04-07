package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class WitherSpider implements Listener {

    static Mythicrpg main;
    FileConfiguration config;

    public WitherSpider(Mythicrpg main)
    {
        this.main = main;
        config = main.getConfig();
    }

    public void createLeapingSpider(Location location)
    {
        int hp = config.getInt("WitherSpiderHealth");
        Spider spider = location.getWorld().spawn(location, Spider.class);
        spider.setCustomName(ColorUtil.ConvertToCustom(config.getString("WitherSpiderNametag")));
        spider.setCustomNameVisible(true);
        Attributable spiderAt = spider;
        AttributeInstance attribute = spiderAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attribute.setBaseValue(hp);
        spider.setHealth(hp);
        spider.setCustomName(ColorUtil.ConvertToCustom(config.getString("WitherSpiderNametag")) + " §7["+Math.round(spider.getHealth())+"/"+spider.getMaxHealth()+"]");
        new BukkitRunnable(){
            int i = 0;
            public void run()
            {
                if(!spider.isDead())
                {
                    spider.setCustomName(ColorUtil.ConvertToCustom(config.getString("WitherSpiderNametag")) + " §7["+Math.round(spider.getHealth())+"/"+spider.getMaxHealth()+"]");

                    if(spider.getTarget() == null)
                    {
                        for(Entity entity : spider.getNearbyEntities(10,10,10))
                        {
                            if(entity instanceof Player)
                            {
                                Player player = (Player) entity;
                                if(player.getGameMode() != GameMode.CREATIVE)
                                    spider.setTarget(player);
                            }
                        }
                    }
                    else
                    {
                        LivingEntity target = spider.getTarget();
                        if(target.getLocation().distanceSquared(spider.getLocation()) > 25)
                        {
                            spider.getWorld().playSound(spider.getLocation(), Sound.ENTITY_WITHER_SHOOT, 5, 5);
                            spider.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE,spider.getLocation(),10);
                            spider.setVelocity(target.getLocation().add(0,2,0).subtract(spider.getLocation()).toVector().multiply(0.275));
                        }
                        if(i % 3 == 1)
                        {
                            ItemStack item = new ItemStack(Material.COBWEB);
                            Item web = spider.getWorld().dropItem(spider.getLocation(),item);
                            web.setVelocity(spider.getTarget().getLocation().add(0,1,0).subtract(web.getLocation()).toVector().multiply(0.6));
                            web.setCustomName("§3WitherSpiderWeb");
                        }
                    }
                }
                else{
                    cancel();
                }
                i++;
            }
        }.runTaskTimer(main,0L,20L);

    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if(event.getDamager() instanceof Spider)
        {
            Spider s = (Spider) event.getDamager();
            if(event.getDamager().getCustomName() != null && event.getDamager().getCustomName().contains(config.getString("WitherSpiderNametag").split("!")[1]))
            {
                if(event.getEntity() instanceof Player)
                {
                    Player player = (Player) event.getEntity();
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60,3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,1));

                }
            }
        }
    }

    @EventHandler
    public void Pickup_item(EntityPickupItemEvent e)
    {
        if(e.getEntity() instanceof Player p)
        {
            if(e.getItem().getCustomName() != null &&e.getItem().getCustomName().contains("§3WitherSpiderWeb"))
            {
                e.setCancelled(true);
                e.getItem().remove();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,9,false,false,false));
                p.damage(2);

            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e)
    {
        if(e.getEntity().getCustomName()!=null && e.getEntity().getCustomName().contains(config.getString("WitherSpiderNametag").split("!")[1])) {
            for (int i = 0; i < 3; i++)
            {
                ItemStack item = new ItemStack(Material.COBWEB);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(i+"");
                item.setItemMeta(meta);
                Item web = e.getEntity().getWorld().dropItem(e.getEntity().getLocation(),item);
                web.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3),ThreadLocalRandom.current().nextDouble(0.7, 1.2),0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI*2)));

                web.setCustomName("§3WitherSpiderWeb");
            }
        }
    }

}
