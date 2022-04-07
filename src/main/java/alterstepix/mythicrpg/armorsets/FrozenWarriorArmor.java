package alterstepix.mythicrpg.armorsets;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class FrozenWarriorArmor implements Listener {

    ItemLoreLibrary lib;
    Mythicrpg main;

    public FrozenWarriorArmor(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player player)
        {
            if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null)
            {
                if(player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getBoots().getItemMeta() != null)
                {
                    if(player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null && player.getInventory().getBoots().getItemMeta().getLore() != null)
                    {
                        if(player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("FWA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("FWA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("FWA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("FWA").get(1)))
                        {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,60,2,true,true,true));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player player)
        {
            if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null)
            {
                if(player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getBoots().getItemMeta() != null)
                {
                    if(player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null && player.getInventory().getBoots().getItemMeta().getLore() != null)
                    {
                        if(player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("FWA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("FWA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("FWA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("FWA").get(1)))
                        {
                            if(e.getEntity() instanceof LivingEntity trg)
                            {
                                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("GiantHit").get(1))) {
                                    return;
                                }
                                trg.damage(6);
                            }
                        }
                    }
                }
            }
        }
    }

}
