package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.misc.ParticleManager;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class Inquisitor implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    Cooldown thiscd;
    HashMap<Player, Location> tpLocs = new HashMap<Player,Location>();

    public Inquisitor(Mythicrpg main)
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
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Inquisition").get(1))) {
                if (thiscd.checkCD(player)) {

                    Location loc = player.getLocation();

                    if(!tpLocs.containsKey(player))
                    {
                        tpLocs.put(player,loc);
                    }
                    else
                    {
                        tpLocs.replace(player,loc);
                    }

                    Location startloc = player.getEyeLocation().clone();
                    Vector dir = player.getLocation().getDirection().normalize().multiply(0.1);

                    Location tploc = player.getLocation().add(player.getLocation().getDirection().normalize().multiply(7));

                    if(!tploc.getBlock().isPassable())
                    {
                        player.sendMessage("§6Cannot teleport into a wall.");
                    }
                    else
                    {
                        ParticleManager.strange_3D_shape(player.getLocation(), Particle.SOUL,1,true);
                        player.teleport(tploc);
                        ParticleManager.strange_3D_shape(player.getLocation(), Particle.SOUL,1,true);

                        for(int i = 0; i < 70; i++)
                        {
                            startloc = startloc.add(dir);
                            Bukkit.getLogger().info(startloc.toString());
                            startloc.getWorld().spawnParticle(Particle.SOUL,startloc,1,0,0,0,0,null,true);

                            for(Entity entity : startloc.getChunk().getEntities())
                            {
                                if(entity instanceof LivingEntity trg && trg.getLocation().distanceSquared(startloc) < 3 && trg != player)
                                {
                                    trg.damage(7);
                                }
                            }

                        }
                        thiscd.putCooldown(player,config.getInt("inquisitorCooldown"));
                    }

                }
                else
                {
                    if(tpLocs.containsKey(player))
                    {
                        ParticleManager.helix(player.getLocation(),1,Particle.SOUL,tpLocs.get(player),true);
                        player.teleport(tpLocs.get(player));
                        thiscd.resetCooldown(player);
                        ParticleManager.helix(player.getLocation(),1,Particle.SOUL,tpLocs.get(player),true);
                    }
                    else
                    {
                        player.sendMessage("§c[Mythic RPG] Something went wrong...");
                    }


                }
            }
        }
    }

}
