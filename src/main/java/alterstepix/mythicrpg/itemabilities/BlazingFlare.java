package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.misc.ParticleManager;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class BlazingFlare implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    Cooldown thiscd;

    public BlazingFlare(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        this.lib.Init();
        this.thiscd = new Cooldown();
        this.thiscd.init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("BlazingFlare").get(1))) {
                if(thiscd.checkCD(player))
                {
                    e.setCancelled(true);

                    thiscd.putCooldown(player,config.getInt("blazingFlareCooldown"));

                    Vector dir = player.getLocation().getDirection().normalize().clone();

                    Location loc = player.getEyeLocation().clone();
                    loc.getWorld().playSound(loc, Sound.ENTITY_WITHER_SHOOT,5,5);

                    Particle.DustTransition tr = new Particle.DustTransition(Color.RED,Color.YELLOW,1.4f);

                    Particle.DustTransition tr_big = new Particle.DustTransition(Color.RED,Color.ORANGE,2.5f);
                    Particle.DustTransition tr_medium = new Particle.DustTransition(Color.RED,Color.ORANGE,2.0f);

                    ParticleManager.circle_dust(loc.clone(),3,tr_big,loc.clone().add(dir),false);
                    ParticleManager.circle_dust(loc.clone().add(dir),2,tr_medium,loc.clone().add(dir).add(dir),false);

                    for(int i = 0; i < 60; i++)
                    {
                        ParticleManager.circle_dust_instant(loc,0.7f,tr,loc.add(dir),true);


                        for(Entity entity : loc.getChunk().getEntities())
                        {
                            if(entity instanceof LivingEntity trg && (trg.getLocation().distanceSquared(loc) < 4 || trg.getEyeLocation().distanceSquared(loc) < 4) && trg != player)
                            {
                                trg.damage(75,player);
                                trg.damage(trg.getMaxHealth()*0.2);
                                trg.addPotionEffect(new PotionEffect( PotionEffectType.CONFUSION, 60, 1, false,false,false ));
                                trg.setFireTicks(trg.getFreezeTicks()+120);

                                new BukkitRunnable()
                                {
                                    int i = 0;
                                    @Override
                                    public void run() {

                                        trg.damage(35,player);
                                        trg.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,trg.getEyeLocation(),30,0.4,0.4,0.4,0,new Particle.DustTransition(Color.GRAY,Color.BLACK,1.7f),true );

                                        if(i > 5 || trg.isDead())
                                            cancel();

                                        i++;
                                    }
                                }.runTaskTimer(main,0L,20L);

                                trg.getWorld().spawnParticle(Particle.LAVA,trg.getLocation(),25,0.35,0.35,0.35,0.2,null,true);
                                ParticleManager.helix(loc,1,Particle.FLAME,loc.add(dir),false);
                                trg.getWorld().playSound(trg.getLocation(), Sound.ENTITY_ITEM_BREAK,1,5);
                            }
                        }

                        if(!loc.getBlock().isPassable())
                        {
                            loc.getWorld().spawnParticle(Particle.LAVA,loc,5,1,1,1);
                        }

                        loc = loc.add(dir);
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
