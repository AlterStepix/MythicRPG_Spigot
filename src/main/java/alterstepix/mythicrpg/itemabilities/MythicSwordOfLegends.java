package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.checkerframework.checker.units.qual.C;


public class MythicSwordOfLegends implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;
    Cooldown thiscd;
    FileConfiguration config;


    public MythicSwordOfLegends(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
        thiscd = new Cooldown();
        thiscd.init();
        this.config = main.getConfig();
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player player)
        {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("MythicWeapon").get(1))) {
                e.setCancelled(true);
                if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null)
                {
                    if(player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getBoots().getItemMeta() != null)
                    {
                        if(player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null && player.getInventory().getBoots().getItemMeta().getLore() != null)
                        {
                            if(player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("MWA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("MWA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("MWA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("MWA").get(1)))
                            {
                                e.setCancelled(false);
                            }
                        }
                    }
                }
        }

        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
        {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("MythicWeapon").get(1))) {
                if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null) {
                    if (player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getBoots().getItemMeta() != null) {
                        if (player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null && player.getInventory().getBoots().getItemMeta().getLore() != null) {
                            if (player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get("MWA").get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get("MWA").get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get("MWA").get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get("MWA").get(1))) {
                                if(thiscd.checkCD(player))
                                {
                                    if(player.isOnGround())
                                    {
                                        player.getWorld().spawnParticle(Particle.FLAME,player.getLocation(),20,0,0,0,0.5);
                                        player.getWorld().createExplosion(player.getLocation(),1,false,false);
                                        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK,9,5);
                                        thiscd.putCooldown(player,config.getInt("mythicSwordOfLegendsCooldown"));
                                        ArmorStand holder = player.getWorld().spawn(player.getLocation().add(player.getLocation().getDirection().normalize().setY(0)).subtract(new Vector(0,0.6,0)), ArmorStand.class);
                                        holder.getEquipment().setItemInMainHand(player.getInventory().getItemInMainHand());
                                        holder.setMarker(true);
                                        holder.setInvulnerable(true);
                                        holder.setArms(true);
                                        holder.setInvisible(true);
                                        holder.setRightArmPose(new EulerAngle(Math.toRadians(90),Math.toRadians(0),Math.toRadians(0)));
                                        new BukkitRunnable()
                                        {
                                            int i = 0;
                                            public void run()
                                            {
                                                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,20,2,false,false,false));
                                                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,20,2,false,false,false));
                                                player.sendTitle("§6"+(6-i),"",0,20,0);
                                                if(i > 5)
                                                {
                                                    holder.remove();
                                                    cancel();
                                                }
                                                i++;
                                            }
                                        }.runTaskTimer(main,0L,20L);
                                    }
                                    else
                                    {
                                        player.sendMessage("§6[Mythic RPG] You must be on the ground to use this ability");
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
}
