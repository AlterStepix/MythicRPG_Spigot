package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class LightningAxe implements Listener {

    int cd;
    Mythicrpg main;
    FileConfiguration config;
    Cooldown thiscd = new Cooldown();

    public LightningAxe(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.cd = this.config.getInt("lightningAxeCooldown");
        thiscd.init();
    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() instanceof LivingEntity && event.getDamager() instanceof Player)
        {
            Player player = (Player) event.getDamager();
            LivingEntity trg = (LivingEntity) event.getEntity();

            if(player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§6ITEM ABILITY: §eLightning Power"))
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
                if (thiscd.checkCD(player)) {
                    for (Entity entity : e.getPlayer().getNearbyEntities(10, 10, 10)) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity trg = (LivingEntity) entity;
                            player.getWorld().strikeLightningEffect(trg.getLocation());
                            trg.damage(6);
                            thiscd.putCooldown(player, cd);
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


