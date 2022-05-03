package alterstepix.mythicrpg.armorsets;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;


public class BeastArmor implements Listener {

    HashMap<Player, Integer> combo = new HashMap<>();

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;

    public BeastArmor(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        this.lib.Init();
    }

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event)
    {
        if(event.getDamager() instanceof Player player)
        {
            if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null) {
                if (player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null) {
                    if (player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null) {
                        if (player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("BA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("BA").get(1))) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,80,3,true,true,true));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,80,3,true,true,true));
                            if(combo.containsKey(player))
                            {

                                if(combo.get(player) >= 5)
                                {
                                    combo.remove(player);
                                    player.sendTitle("§cSuperhit","",5,15,5);
                                    if(event.getEntity() instanceof LivingEntity target)
                                    {
                                        event.setDamage(event.getDamage()*5);
                                        target.setVelocity(new Vector(0,0.8,0));
                                        target.getWorld().playSound(target.getLocation(), Sound.BLOCK_ANVIL_BREAK,7,5);
                                    }
                                }
                                else
                                {
                                    combo.replace(player,combo.get(player)+1);
                                    player.sendTitle(""+combo.get(player)+" §e✦","",5,15,5);
                                }
                            }
                            else
                            {
                                combo.put(player,1);
                                player.sendTitle(""+combo.get(player)+" §e✦","",5,15,5);
                            }
                        }
                    }
                }
            }

        }
    }

    @EventHandler
    public void onAnyDamageEvent(EntityDamageEvent event)
    {
        if(event.getEntity() instanceof Player player)
        {
            if(event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)
            {
                if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null) {
                    if (player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null) {
                        if (player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null) {
                            if (player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("BA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("BA").get(1))) {
                                    event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
