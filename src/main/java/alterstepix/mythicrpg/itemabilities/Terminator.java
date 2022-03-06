package alterstepix.mythicrpg.itemabilities;

import com.sun.media.jfxmedia.events.BufferListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Terminator implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Bukkit.getLogger().info("Interact Event");
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getLore() != null && p.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6LEFT CLICK CLICK: §eTermination")) {
                    e.setCancelled(true);
                    Arrow arrow = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
                    arrow.setDamage(1);
                    arrow.setShooter(p);
                    arrow.setVelocity(p.getLocation().getDirection().multiply(2));

                    e.setCancelled(true);
                    Arrow arrow2 = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
                    arrow2.setDamage(1);
                    arrow2.setShooter(p);
                    arrow2.setVelocity((p.getLocation().getDirection().multiply(2)).rotateAroundY(Math.toRadians(-10)));

                    e.setCancelled(true);
                    Arrow arrow3 = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
                    arrow3.setDamage(1);
                    arrow3.setShooter(p);
                    arrow3.setVelocity((p.getLocation().getDirection().multiply(2)).rotateAroundY(Math.toRadians(10)));
                }
            }
        }
    }
