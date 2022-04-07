package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.C;


public class FlamingWhip implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;
    Cooldown thiscd;
    FileConfiguration config;

    public FlamingWhip(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
        this.thiscd = new Cooldown();
        this.thiscd.init();
        this.config = main.getConfig();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("FlamingArc").get(1))) {
            e.setCancelled(true);
            if(thiscd.checkCD(player))
            {
                ArmorStand s = player.getWorld().spawn(player.getLocation().add(new Vector(0,1,0)),ArmorStand.class);
                s.setInvisible(true);
                s.setInvulnerable(true);


                s.setVelocity(player.getLocation().getDirection().normalize().multiply(2.7).multiply(new Vector(1,0.4,1)));

                thiscd.putCooldown(player,config.getInt("flamingWhipCooldown"));

                new BukkitRunnable()
                {
                    public void run()
                    {
                        for(Entity entity : s.getNearbyEntities(1,1,1))
                        {
                            if(entity instanceof LivingEntity trg) {
                                if (trg != player && !(trg instanceof ArmorStand))
                                {
                                    if(trg.getLocation().distanceSquared(s.getLocation()) < 3)
                                    {
                                        trg.setFireTicks(trg.getFreezeTicks() + 60);
                                        trg.damage(5);
                                        trg.getWorld().spawnParticle(Particle.LAVA,trg.getLocation(),10);
                                        s.remove();
                                        cancel();
                                    }
                                }
                            }
                        }
                        if(s.isOnGround())
                        {
                            s.remove();
                            cancel();
                        }
                        s.getWorld().spawnParticle(Particle.FLAME,s.getLocation(),4,0,0,0,0);

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
