package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.misc.ParticleManager;
import alterstepix.mythicrpg.util.Checker;
import alterstepix.mythicrpg.util.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Color;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ShadowKatana implements Listener {

    Mythicrpg main;
    FileConfiguration config;

    public ShadowKatana(Mythicrpg main) {
        this.main = main;

    }


    @EventHandler
    public void entityDamageByPlayerEvent(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player player) {
            if(Checker.isHoldingItem(player,"ShadowKatana")) {
                if (event.getEntity() instanceof LivingEntity trg) {

                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT, 5, 5);
                    player.setVelocity(player.getLocation().getDirection().normalize().multiply(2).setY(0.2));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 3, false, false, false));
                    player.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, player.getEyeLocation(), 25, 0.3, 0.3, 0.3, new Particle.DustTransition(Color.GRAY, Color.BLACK, 1.5f));


                    ParticleManager.helix(trg.getLocation(), 1, Particle.SPELL_WITCH, trg.getLocation(), true);

                    for(Entity e : player.getNearbyEntities(2,2,2)) {
                        if(e instanceof LivingEntity trg2) {
                            trg2.damage(35);
                            trg.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1, false, false, false));
                            trg.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 1, false, false, false));
                        }
                    }

                    trg.damage(45);
                    trg.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1, false, false, false));
                    trg.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1, false, false, false));
                }


            }
        }
    }
}



