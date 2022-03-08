package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Nametags implements Listener {

    Mythicrpg main;
    FileConfiguration config;

    public Nametags(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof LivingEntity && e.getEntity().getCustomName() != null)
        {
            LivingEntity mob = (LivingEntity) e.getEntity();
            if(mob.getCustomName().startsWith(config.getString("WitherSpiderNametag")))
            {
                mob.setCustomName(config.getString("WitherSpiderNametag") + "§7["+mob.getHealth()+"/"+mob.getMaxHealth()+"]");
            }
            else if(mob.getCustomName().startsWith(config.getString("NecromancerNametag")))
            {
                mob.setCustomName(config.getString("NecromancerNametag") + "§7["+mob.getHealth()+"/"+mob.getMaxHealth()+"]");
            }
            else if(mob.getCustomName().startsWith(config.getString("InfectedZombieNametag")))
            {
                mob.setCustomName(config.getString("InfectedZombieNametag") + "§7["+mob.getHealth()+"/"+mob.getMaxHealth()+"]");
            }
        }
    }

}
