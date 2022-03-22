package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;

public class SemiIdol implements Listener {
    Mythicrpg main;
    FileConfiguration config;

    public SemiIdol(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createSemiIdol(Location location)
    {

        IronGolem golem = location.getWorld().spawn(location,IronGolem.class);

        Attributable mAt = golem;
        AttributeInstance attribute = mAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attribute.setBaseValue(config.getInt("SemiIdolHealth"));

        golem.setHealth(config.getInt("SemiIdolHealth"));

        golem.setCustomName(ColorUtil.ConvertToCustom(config.getString("SemiIdolNametag")) + " §7["+Math.round(golem.getHealth())+"/"+golem.getMaxHealth()+"]");
        golem.setCustomNameVisible(true);

        new BukkitRunnable()
        {
            int i = 0;
            public void run()
            {

                if(!golem.isDead())
                {
                    golem.setCustomName(ColorUtil.ConvertToCustom(config.getString("SemiIdolNametag")) + " §7["+Math.round(golem.getHealth())+"/"+golem.getMaxHealth()+"]");
                    if(golem.getTarget() != null) {
                        if (i % 20 == 0) {
                            Random r = new Random();
                            AirSpirit summoned1 = new AirSpirit(main);
                            summoned1.createAirSpirit(golem.getLocation().add(r.nextInt(1 + 1) - 1, 0, r.nextInt(1 + 1) - 1));

                            FireSpirit summoned2 = new FireSpirit(main);
                            summoned2.createFireSpirit(golem.getLocation().add(r.nextInt(1 + 1) - 1, 0, r.nextInt(1 + 1) - 1));

                            IceSpirit summoned3 = new IceSpirit(main);
                            summoned3.createIceSpirit(golem.getLocation().add(r.nextInt(1 + 1) - 1, 0, r.nextInt(1 + 1) - 1));
                        }
                        if (i % 5 == 2) {
                            LivingEntity trg = golem.getTarget();
                            trg.getWorld().strikeLightningEffect(trg.getLocation());
                            trg.damage(4);
                        }
                        if (i % 5 == 0)
                        {
                            for(Entity e : golem.getNearbyEntities(10,10,10)){
                                if(e instanceof Player)
                                {
                                    golem.setTarget((Player)e);
                                }
                            }
                        }
                    }


                    i++;
                }
                else
                {
                    golem.remove();
                }
            }
        }.runTaskTimer(main,0L,20L);
    }

    @EventHandler
    public void onTarget(EntityTargetEvent e)
    {
        if(e.getEntity() != null && e.getEntity().getCustomName()!= null && e.getEntity().getCustomName().contains(config.getString("SemiIdolNametag").split("!")[1]))
        {
            if(e.getTarget() instanceof Player)
            {
                e.getTarget().getWorld().strikeLightningEffect(e.getTarget().getLocation());
            }
            else
            {
                e.setCancelled(true);
            }
        }
        if(e.getEntity() != null && e.getTarget().getCustomName()!= null && e.getTarget().getCustomName().contains(config.getString("SemiIdolNametag").split("!")[1]))
        {
            e.setCancelled(true);
        }
    }

}
