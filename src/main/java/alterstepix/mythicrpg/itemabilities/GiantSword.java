package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class GiantSword implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    Cooldown thiscd = new Cooldown();

    public GiantSword(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfiguration();
        lib = new ItemLoreLibrary(main);
        lib.Init();
        thiscd.init();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity)
        {
            LivingEntity target = (LivingEntity) e.getEntity();
            Player player = (Player)e.getDamager();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("GiantHit").get(1))) {
                if (thiscd.checkCD(player)) {

                    target.damage(8+target.getMaxHealth()*0.05);
                    target.setVelocity(target.getLocation().add(0,2,0).subtract(player.getLocation()).toVector().setY(0.5).normalize().multiply(2));
                    target.getWorld().playSound(target.getLocation(), Sound.BLOCK_ANVIL_DESTROY,5,5);
                    this.thiscd.putCooldown(player,config.getInt("giantSwordCooldown"));
                }
                else
                {
                    e.setCancelled(true);
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                }
            }
        }
    }

}
