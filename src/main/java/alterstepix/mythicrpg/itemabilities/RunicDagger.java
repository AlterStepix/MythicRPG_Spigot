package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import alterstepix.mythicrpg.managers.ItemManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RunicDagger implements Listener {
    Mythicrpg main;
    ItemLoreLibrary lib;
    ItemManager manager;
    FileConfiguration config;

    public RunicDagger(Mythicrpg main)
    {
        this.main = main;
        lib = new ItemLoreLibrary(main);
        lib.Init();
        manager = new ItemManager(main);
        manager.init();
        config = main.getConfig();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicSwap").get(1))) {
                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicMolten").get(1))) {
                    player.getInventory().setItemInMainHand(manager.RuinicDagger[0]);
                }
                else if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicFrozen").get(1))) {
                    player.getInventory().setItemInMainHand(manager.RuinicDagger[1]);
                }
            }
        }
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player)
        {
            Player player = (Player)e.getDamager();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicMolten").get(1))) {
                if(e.getEntity() instanceof LivingEntity)
                {
                    LivingEntity trg = (LivingEntity) e.getEntity();
                    trg.setFireTicks(trg.getFireTicks()+60);
                    if(trg.getCustomName() != null && trg.getCustomName().contains(config.getString("IceSpiritNametag").split("!")[1]))
                    {
                        trg.damage(10);
                    }
                }
            }
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicFrozen").get(1))) {
                if(e.getEntity() instanceof LivingEntity)
                {
                    LivingEntity trg = (LivingEntity) e.getEntity();
                    trg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,2,false,false,false));
                    if(trg.getCustomName() != null && trg.getCustomName().contains(config.getString("FireSpiritNametag").split("!")[1]))
                    {
                        trg.damage(10);
                    }
                }
            }
        }
    }
}
