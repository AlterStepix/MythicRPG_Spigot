package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
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

public class AirBurner implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    Cooldown thiscd = new Cooldown();

    public AirBurner(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        lib = new ItemLoreLibrary(main);
        lib.Init();
        thiscd.init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            Player player = e.getPlayer();

            if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("AirBurner").get(1))) {
                if (thiscd.checkCD(player))
                {
                    int radius = config.getInt("airBurnerRadius");
                    for (Entity entity : player.getNearbyEntities(radius, radius, radius)) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity le = (LivingEntity) entity;
                            le.setFireTicks(le.getFireTicks()+120);
                            player.getWorld().spawnParticle(Particle.LAVA, player.getLocation(), 15);
                            player.getWorld().playSound(player.getLocation(), Sound.BLOCK_FIRE_AMBIENT, 8, 5);
                            thiscd.putCooldown(player,config.getInt("airBurnerCooldown"));
                        }
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
