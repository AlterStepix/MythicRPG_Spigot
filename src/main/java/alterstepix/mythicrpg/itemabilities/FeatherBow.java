package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
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
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.net.http.WebSocket;

public class FeatherBow implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    Cooldown thiscd;

    public FeatherBow(Mythicrpg main)
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
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("FeatherBow").get(1))) {
                e.setCancelled(true);

                Vector dir = player.getLocation().getDirection().normalize().multiply(0.1).clone();
                Location loc = player.getEyeLocation().clone();
                loc.getWorld().playSound(loc,Sound.ENTITY_WITHER_SHOOT,5,5);

                Particle.DustTransition tr = new Particle.DustTransition(Color.WHITE,Color.SILVER,1.2f);

                for(int i = 0; i < 1500; i++)
                {
                    loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,loc,1,0,0,0,0,tr,true);


                    for(Entity entity : loc.getChunk().getEntities())
                    {
                        if(entity instanceof LivingEntity trg && (trg.getLocation().distanceSquared(loc) < 2 || trg.getEyeLocation().distanceSquared(loc) < 2) && trg != player)
                        {
                            trg.damage(5);
                            trg.getWorld().spawnParticle(Particle.CLOUD,trg.getLocation(),25,0.35,0.35,0.35,0.2,null,true);
                            trg.getWorld().playSound(trg.getLocation(), Sound.ENTITY_ITEM_BREAK,1,5);
                        }
                    }

                    if(!loc.getBlock().isPassable())
                        break;

                    loc = loc.add(dir);
                }

            }
        }
    }

    @EventHandler
    public void onShoot(ProjectileLaunchEvent event)
    {

    }
}
