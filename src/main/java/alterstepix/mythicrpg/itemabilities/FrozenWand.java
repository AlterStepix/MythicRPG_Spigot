package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

public class FrozenWand implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    Cooldown thiscd = new Cooldown();;

    public FrozenWand(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        thiscd.init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6RIGHT CLICK: §eFrozen Breathe")) {
                if (thiscd.checkCD(player)) {

                    int radius = config.getInt("frozenWandRadius");

                    for (Entity entity : player.getNearbyEntities(radius, radius, radius)) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity trg = (LivingEntity) entity;
                            PotionEffect potionEffect = new PotionEffect(PotionEffectType.WEAKNESS, 60, 1, true, true, true);
                            PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.SLOW, 60, 1, true, true, true);
                            trg.addPotionEffect(potionEffect);
                            trg.addPotionEffect(potionEffect2);
                            trg.getWorld().spawnParticle(Particle.WATER_SPLASH, trg.getLocation(), 10);
                        }
                    }

                    Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 3.5, player.getLocation().getZ());
                    player.getWorld().spawnParticle(Particle.SNOWBALL, player.getLocation(), 5);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SNOW_GOLEM_SHOOT, 8, 5);
                    thiscd.putCooldown(player,config.getInt("frozenWandCooldown"));

                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                }
            }
        }
    }

}


