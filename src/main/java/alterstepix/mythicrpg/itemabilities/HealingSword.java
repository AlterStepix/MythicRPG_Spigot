package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HealingSword implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            Player player = e.getPlayer();


            if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6RIGHT CLICK: §eHealing")) {
                int hunger = player.getFoodLevel();
                if(hunger>0) //Hunger > 0
                {
                    player.setHealth(20);
                    if(hunger>6)
                    {
                        player.setFoodLevel(hunger-6);
                        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SLIME_BLOCK_PLACE, 10, 10);
                        player.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 5);
                    }else{
                        player.setFoodLevel(0);
                        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SLIME_BLOCK_PLACE, 10, 1);
                        player.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, player.getLocation().add(0,2,0), 5);
                    }

                }



            }
        }
    }

}
