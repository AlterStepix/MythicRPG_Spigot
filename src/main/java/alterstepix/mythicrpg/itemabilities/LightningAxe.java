package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LightningAxe implements Listener {
    private Mythicrpg main;

    public LightningAxe(Mythicrpg main)
    {
        this.main = main;
    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() instanceof LivingEntity && event.getDamager() instanceof Player)
        {
            Player player = (Player) event.getDamager();
            LivingEntity trg = (LivingEntity) event.getEntity();

            if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6RIGHT CLICK: §eThunderlord"))
            {
                player.getWorld().strikeLightningEffect(event.getEntity().getLocation());
                trg.damage(2);
            }

        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            Player player = e.getPlayer();
            if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6RIGHT CLICK: §eThunderlord")) {
                if (Cooldown.checkCooldown(e.getPlayer())) {
                    for (Entity entity : e.getPlayer().getNearbyEntities(10, 10, 10)) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity trg = (LivingEntity) entity;
                            player.getWorld().strikeLightningEffect(trg.getLocation());
                            trg.damage(6);
                            Cooldown.setCooldown(player, 3);
                        }
                    }
                }
                else
                {
                    player.sendMessage("§c[Mythic RPG]This item is on cooldown");
                }
            }
        }
    }
}


