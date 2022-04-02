package alterstepix.mythicrpg.scrolls;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class HealingTotemScroll implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;

    public HealingTotemScroll(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();


            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("HealingTotem").get(1))) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);

                Blaze healer;

                if(player.getWorld().getBlockAt(player.getLocation().add(0,3,0)).getType() == Material.AIR)
                {
                    healer = player.getLocation().getWorld().spawn(player.getLocation().add(0,3,0),Blaze.class);
                }
                else
                {
                    healer = player.getLocation().getWorld().spawn(player.getLocation(),Blaze.class);
                }

                healer.setCustomName(ColorUtil.ConvertToCustom(config.getString("NetherHealerNametag"))+" ["+healer.getHealth()+"/"+healer.getMaxHealth()+"]");
                healer.setCustomNameVisible(true);
                healer.setMaxHealth(config.getInt("NetherHealerHealth"));
                healer.setHealth(config.getInt("NetherHealerHealth"));

                AttributeInstance atArmor = healer.getAttribute(Attribute.GENERIC_ARMOR);
                atArmor.setBaseValue(35);

                Guardian laser = player.getWorld().spawn(player.getLocation(),Guardian.class);
                laser.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,9999999,1,false,false));
                laser.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,9999999,10,false,false));
                healer.addPassenger(laser);
                laser.setAI(false);
                healer.setAI(false);

                new BukkitRunnable()
                {
                    @Override
                    public void run() {
                        if(!healer.isDead())
                        {
                            healer.setCustomName(ColorUtil.ConvertToCustom(config.getString("NetherHealerNametag"))+" ["+healer.getHealth()+"/"+healer.getMaxHealth()+"]");
                            laser.setTarget(player);
                            laser.setLaser(true);
                            if(player.getMaxHealth() - player.getHealth() < 2)
                            {
                                player.setHealth(player.getHealth()+2);
                            }
                            healer.damage(4);
                            healer.getWorld().spawnParticle(Particle.VILLAGER_ANGRY,healer.getLocation(),5,0,0,0,0.3);
                        }
                        else
                        {
                            healer.remove();
                            laser.remove();
                            cancel();
                        }
                    }
                }.runTaskTimer(main,0L,20L);
            }
        }
    }
}
