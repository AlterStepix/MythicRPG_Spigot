package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.mobs.Necromancer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Nametags implements Listener {

    Mythicrpg main;
    FileConfiguration config;

    public Nametags(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void Init()
    {

        new BukkitRunnable(){
            public void run()
            {
                for(World wrd : Bukkit.getServer().getWorlds())
                {
                    for(Entity entity : wrd.getEntities()) {
                        if (entity instanceof LivingEntity)
                        {
                            LivingEntity trg = (LivingEntity) entity;
                            if(trg.getCustomName() != null) {
                                String infectedZombieN = config.getString("InfectedZombieNametag");
                                String necromancerN = config.getString("ParasiteNametag");
                                String witherSpider = config.getString("WitherSpiderNametag");
                                if (trg.getCustomName().startsWith(infectedZombieN))
                                {
                                    trg.setCustomName(config.getString("InfectedZombieNametag") + " §7["+Math.round(trg.getHealth())+"/"+trg.getMaxHealth()+"]");
                                }
                                else if(trg.getCustomName().startsWith(necromancerN))
                                {
                                    trg.setCustomName(config.getString("ParasiteNametag") + " §7["+Math.round(trg.getHealth())+"/"+trg.getMaxHealth()+"]");
                                }
                                else if(trg.getCustomName().startsWith(witherSpider))
                                {
                                    trg.setCustomName(config.getString("WitherSpiderNametag") + " §7["+Math.round(trg.getHealth())+"/"+trg.getMaxHealth()+"]");
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(main,0L,20L);
    }


}
