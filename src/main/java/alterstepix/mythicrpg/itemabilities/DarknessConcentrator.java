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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class DarknessConcentrator implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;
    Cooldown thiscd;
    FileConfiguration config;

    public DarknessConcentrator(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.thiscd = new Cooldown();
        this.thiscd.init();
        this.lib = new ItemLoreLibrary(main);
        this.lib.Init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("DarknessBeam").get(1))) {
            e.setCancelled(true);
            if (thiscd.checkCD(player)) {
                for(int i = 0; i<7; i++)
                {
                    ArmorStand s = player.getWorld().spawn(player.getLocation().add(new Vector(0, 1, 0)), ArmorStand.class);
                    s.setInvisible(true);
                    s.setInvulnerable(true);


                    s.setVelocity(player.getLocation().getDirection().normalize().multiply(2.7).multiply(new Vector(1, 0.7, 1)));
                    Double rotationY = ThreadLocalRandom.current().nextDouble(-1, 1)*0.12;
                    Double rotationZ = ThreadLocalRandom.current().nextDouble(-1, 1)*0.12;
                    s.setVelocity(s.getVelocity().rotateAroundY(rotationY));
                    s.setVelocity(s.getVelocity().rotateAroundZ(rotationZ));

                    thiscd.putCooldown(player, config.getInt("darknessConcentratorCooldown"));

                    new BukkitRunnable() {
                        public void run() {
                            for (Entity entity : s.getNearbyEntities(1, 1, 1)) {
                                if (entity instanceof LivingEntity trg) {
                                    if (trg != player && !(trg instanceof ArmorStand)) {
                                        if (trg.getLocation().distanceSquared(s.getLocation()) < 3) {
                                            trg.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,25,1,false,false,false));
                                            trg.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,60,5,false,false,false));
                                            trg.damage(15, player);
                                            trg.getWorld().spawnParticle(Particle.SQUID_INK, trg.getLocation(), 7,0,0,0,0.5);
                                        }
                                    }
                                }
                            }
                            if (s.isOnGround()) {
                                s.remove();
                                cancel();
                            }
                            s.getWorld().spawnParticle(Particle.SOUL, s.getLocation(), 12, 0, 0, 0, 0);

                        }
                    }.runTaskTimer(main, 0L, 1L);

                }

            } else {
                player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player) + 1));
            }
        }
    }
}
