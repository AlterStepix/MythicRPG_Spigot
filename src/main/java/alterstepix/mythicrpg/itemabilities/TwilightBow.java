package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.misc.ParticleManager;
import alterstepix.mythicrpg.util.Checker;
import alterstepix.mythicrpg.util.Cooldown;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class TwilightBow implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    Cooldown thiscd;

    public TwilightBow(Mythicrpg main) {
        this.main = main;
        this.thiscd = new Cooldown();
        thiscd.init();
    }

    @EventHandler
    public void entityInteractEvent(PlayerInteractEvent event) {
        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
        {
            if(Checker.isHoldingItem(event.getPlayer(),"RayOfShadows"))
            {
                Player player = event.getPlayer();
                if(thiscd.checkCD(player))
                {
                    thiscd.putCooldown(player,5);

                    ParticleManager.d2_vortex_ni(player.getLocation(), new Particle.DustTransition(Color.BLACK,Color.PURPLE,0.8f),2,true);
                    ParticleManager.d2_vortex_ni(player.getLocation(), new Particle.DustTransition(Color.SILVER,Color.PURPLE,1.0f),2.5,true);
                    ParticleManager.d2_vortex_ni(player.getLocation(), new Particle.DustTransition(Color.BLUE,Color.PURPLE,1.2f),1.5,true);

                    new BukkitRunnable()
                    {

                        @Override
                        public void run() {
                            Vector dir = player.getLocation().getDirection().normalize().multiply(0.2).clone();
                            Location loc = player.getEyeLocation().clone();
                            loc.getWorld().playSound(loc, Sound.ENTITY_WITHER_SHOOT,5,5);


                            Particle.DustTransition tr1 = new Particle.DustTransition(Color.PURPLE,Color.BLUE,1.3f);
                            Particle.DustTransition tr2 = new Particle.DustTransition(Color.SILVER,Color.PURPLE,0.9f);


                            for(int i = 0; i < 750; i++)
                            {
                                loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,loc,2,0,0,0,0,tr1,true);
                                loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,loc,5,0.2,0.2,0.2,0.2,tr2,true);


                                for(Entity entity : loc.getChunk().getEntities())
                                {
                                    if(entity instanceof LivingEntity trg && (trg.getLocation().distanceSquared(loc) < 2 || trg.getEyeLocation().distanceSquared(loc) < 2) && trg != player)
                                    {
                                        trg.damage(15);
                                        trg.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,trg.getLocation(),15,0.35,0.35,0.35,0.2,tr2,true);
                                        trg.getWorld().playSound(trg.getLocation(), Sound.ENTITY_WITHER_HURT,1,5);

                                        //

                                        for(int k = 0; k < 3; k++)
                                        {
                                            new BukkitRunnable() {


                                                @Override
                                                public void run() {
                                                    Vector dir = player.getLocation().getDirection().normalize().multiply(0.2).clone().rotateAroundY(Math.random() * Math.PI * 2).rotateAroundX(Math.random() * Math.PI * 2).rotateAroundZ(Math.random() * Math.PI * 2);
                                                    Location loc = trg.getEyeLocation().clone();


                                                    Particle.DustTransition tr1 = new Particle.DustTransition(Color.SILVER,Color.BLUE,0.7f);


                                                    for(int i = 0; i < 35; i++)
                                                    {
                                                        loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,loc,2,0,0,0,0,tr1,true);

                                                        for(Entity entity : loc.getChunk().getEntities())
                                                        {
                                                            if(entity instanceof LivingEntity trg && (trg.getLocation().distanceSquared(loc) < 2 || trg.getEyeLocation().distanceSquared(loc) < 2) && trg != player)
                                                            {
                                                                trg.damage(5);
                                                                trg.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,trg.getLocation(),5,0.35,0.35,0.35,0.2,tr1,true);
                                                            }
                                                        }

                                                        if(!loc.getBlock().isPassable())
                                                            break;

                                                        loc = loc.add(dir);
                                                    }



                                                    cancel();

                                                }
                                            }.runTaskTimer(main,0L,2L);
                                        }

                                        //

                                    }
                                }

                                if(!loc.getBlock().isPassable())
                                {
                                    break;
                                }


                                loc = loc.add(dir);
                            }


                            cancel();
                        }
                    }.runTaskTimer(main,0L,20L);

                    for(int k = 0; k < 15; k++)
                    {
                        new BukkitRunnable() {


                            @Override
                            public void run() {
                                Vector dir = player.getLocation().getDirection().normalize().multiply(0.2).clone().rotateAroundY(Math.random() * Math.PI * 2);
                                Location loc = player.getEyeLocation().clone();



                                Particle.DustTransition tr1 = new Particle.DustTransition(Color.BLACK,Color.BLUE,0.7f);


                                for(int i = 0; i < 35; i++)
                                {
                                    loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,loc,2,0,0,0,0,tr1,true);

                                    for(Entity entity : loc.getChunk().getEntities())
                                    {
                                        if(entity instanceof LivingEntity trg && (trg.getLocation().distanceSquared(loc) < 2 || trg.getEyeLocation().distanceSquared(loc) < 2) && trg != player)
                                        {
                                            trg.damage(5);
                                            trg.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,trg.getLocation(),5,0.35,0.35,0.35,0.2,tr1,true);
                                        }
                                    }

                                    if(!loc.getBlock().isPassable())
                                        break;

                                    loc = loc.add(dir);
                                }



                                cancel();

                            }
                        }.runTaskTimer(main,0L,2L);
                    }


                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                }
            }
        }
    }

}
