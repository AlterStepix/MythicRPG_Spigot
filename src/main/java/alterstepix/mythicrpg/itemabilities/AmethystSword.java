package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.misc.ParticleManager;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AmethystSword implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    Cooldown thiscd;

    public AmethystSword(Mythicrpg main)
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
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Soundwave").get(1))) {
                if (thiscd.checkCD(player)) {
                    thiscd.putCooldown(player,config.getInt("amethystSwordCooldown"));

                    Vector dir = player.getLocation().getDirection().normalize().clone();
                    Location[] loc = new Location[] { player.getEyeLocation().clone() };

                    new BukkitRunnable()
                    {
                        int i = 0;
                        @Override
                        public void run() {
                            if(i > 40)
                                cancel();

                            loc[0].getWorld().playSound(loc[0], Sound.BLOCK_AMETHYST_CLUSTER_HIT,5,5);
                            ParticleManager.circle_instant(loc[0],0.7f, Particle.NOTE,loc[0].add(dir),true);

                            for(Entity entity : loc[0].getChunk().getEntities()) {
                                if (entity instanceof LivingEntity trg && (trg.getLocation().distanceSquared(loc[0]) < 3 || trg.getEyeLocation().distanceSquared(loc[0]) < 3) && trg != player) {
                                    trg.damage(10,player);
                                }
                            }

                            if(!loc[0].getBlock().isPassable())
                            {
                                loc[0].getWorld().spawnParticle(Particle.NOTE,loc[0],100,3.5,3.5,3.5,0,null,true);
                                for(Entity entity : loc[0].getChunk().getEntities()) {
                                    if (entity instanceof LivingEntity trg && (trg.getLocation().distanceSquared(loc[0]) < 15 || trg.getEyeLocation().distanceSquared(loc[0]) < 15) && trg != player) {
                                        trg.damage(20);
                                    }
                                }
                                cancel();
                            }

                            loc[0] = loc[0].add(dir);
                            i++;
                        }
                    }.runTaskTimer(main,0L,1L);
                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                }
            }
        }
    }

}
