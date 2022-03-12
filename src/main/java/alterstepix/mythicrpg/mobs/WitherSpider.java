package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

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
                    }
                }
                else{
                    cancel();
                }
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
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60,1));

                }
            }
        }
    }


}
