package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
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

    Mythicrpg main;
    ItemLoreLibrary lib;

    public HealingSword(Mythicrpg main)
    {
        this.main = main;
        lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            Player player = e.getPlayer();

            if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Healing").get(1))) {
                int hunger = player.getFoodLevel();
                if(hunger>0)
                {
                    if(hunger>8)
                    {
                        player.setFoodLevel(hunger-8);
                        player.getWorld().spawnParticle(Particle.TOTEM, player.getLocation(), 5);
                        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_HONEY_BLOCK_BREAK, 15, 5);
                        player.setHealth(20);
                    }
                    else
                    {
                        player.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 15, 10);
                    }

                }

            }
        }
    }

}
