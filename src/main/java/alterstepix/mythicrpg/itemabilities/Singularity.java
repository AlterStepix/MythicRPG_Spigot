package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Singularity implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;
    FileConfiguration config;

    public Singularity(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onShoot(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Singularity").get(1))) {
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                CrossbowMeta meta = (CrossbowMeta) player.getInventory().getItemInMainHand().getItemMeta();
                if(meta.hasChargedProjectiles()) {
                    e.setCancelled(true);
                    meta.setChargedProjectiles(null);
                    player.getInventory().getItemInMainHand().setItemMeta(meta);

                    ItemStack item = new ItemStack(Material.ENDER_PEARL);
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName("§3GravityTrap" + Math.random());
                    item.setItemMeta(itemMeta);
                    Item web = player.getWorld().dropItem(player.getLocation(), item);
                    web.setVelocity(player.getLocation().getDirection().multiply(2));
                    web.setCustomName("§3GravityTrap" + Math.random());
                    web.setPickupDelay(99999999);

                    new BukkitRunnable()
                    {
                        int i = 0;
                        @Override
                        public void run() {
                            i++;
                            if(!web.isDead())
                            {
                                web.getWorld().spawnParticle(Particle.SQUID_INK,web.getLocation(),2);
                                for(Entity entity : web.getNearbyEntities(8,8,8))
                                {
                                    if(entity instanceof LivingEntity trg && entity != player)
                                    {
                                        trg.setVelocity(web.getLocation().add(0,1,0).subtract(trg.getLocation()).toVector().multiply(0.1));
                                        if(trg.getLocation().distanceSquared(web.getLocation())<1)
                                        {
                                            web.getWorld().createExplosion(web.getLocation(),3,false,false);
                                            trg.damage(10);
                                            web.remove();
                                        }
                                    }
                                }
                                if(i % 50 == 0)
                                {
                                    web.getWorld().createExplosion(web.getLocation(),2,false,false);
                                    web.remove();
                                }
                            }
                            else
                            {
                                web.remove();
                                cancel();
                            }
                        }
                    }.runTaskTimer(main,0L,2L);

                }
            }
        }
    }
}
