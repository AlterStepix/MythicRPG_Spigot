package alterstepix.mythicrpg.armorsets;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.C;


public class MasterAssassinArmor implements Listener {
    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    Cooldown thiscd;

    public MasterAssassinArmor(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        lib = new ItemLoreLibrary(main);
        lib.Init();
        thiscd = new Cooldown();
        thiscd.init();
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e)
    {
        Player player = e.getPlayer();
        if(e.isSneaking())
        {
            if(player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null)
            {
                if(player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getBoots().getItemMeta() != null)
                {
                    if(player.getInventory().getChestplate().getItemMeta().getLore() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null && player.getInventory().getBoots().getItemMeta().getLore() != null)
                    {
                        if(player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("MAA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("MAA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("MAA").get(1)))
                        {
                            if(thiscd.checkCD(player))
                            {
                                LivingEntity closest = null;
                                Double closestD = 9999999.0;
                                for(Entity entity : player.getNearbyEntities(7,7,7))
                                {
                                    if(entity instanceof LivingEntity trg)
                                    {
                                        if(trg.getLocation().distanceSquared(player.getLocation())<closestD)
                                        {
                                            closest = trg;
                                            closestD = trg.getLocation().distanceSquared(player.getLocation());
                                        }
                                    }
                                }
                                if(closest != null)
                                {
                                    if(player.getWorld().getBlockAt(closest.getLocation().add(closest.getLocation().getDirection().setY(0).normalize().multiply(-1))).getType() == Material.AIR)
                                    {
                                        player.teleport(closest.getLocation().add(closest.getLocation().getDirection().setY(0).normalize().multiply(-1)));
                                        thiscd.putCooldown(player,config.getInt("MasterAssassinArmorCooldown"));
                                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_SHOOT,5,5);
                                        player.getWorld().spawnParticle(Particle.SQUID_INK,player.getLocation(),3);
                                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,60,6,false,false,true));
                                    }
                                    else
                                    {
                                        player.sendMessage("§6[Mythic RPG] Cannot teleport into a block");
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
        }

    }

}
