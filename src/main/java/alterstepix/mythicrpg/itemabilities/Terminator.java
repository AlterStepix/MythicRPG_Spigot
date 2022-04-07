package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.meta.FireworkMeta;


public class Terminator implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    int abilityRadius;
    ItemLoreLibrary lib;
    Cooldown thiscd = new Cooldown();

    public Terminator(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.abilityRadius = this.config.getInt("terminatorAbilityRange");
        lib = new ItemLoreLibrary(main);
        lib.Init();
        thiscd.init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if ((e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) && !e.getPlayer().isSneaking()) {
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getLore() != null && p.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Termination").get(1))) {
                e.setCancelled(true);
                Arrow arrow = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
                arrow.setDamage(2);
                arrow.setPierceLevel(120);
                arrow.setVelocity(p.getLocation().getDirection().multiply(2));

                Arrow arrow2 = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
                arrow2.setDamage(2);
                arrow2.setPierceLevel(120);
                arrow2.setVelocity((p.getLocation().getDirection().multiply(2)).rotateAroundY(Math.toRadians(-10)));

                Arrow arrow3 = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
                arrow3.setDamage(2);
                arrow3.setPierceLevel(120);
                arrow3.setVelocity((p.getLocation().getDirection().multiply(2)).rotateAroundY(Math.toRadians(10)));
            }
        }
        else if ((e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) && e.getPlayer().isSneaking())
        {
            Player p = e.getPlayer();

            if (p.getInventory().getItemInMainHand().getItemMeta() != null && p.getInventory().getItemInMainHand().getItemMeta().getLore() != null && p.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Recall").get(1))) {
                if(thiscd.checkCD(p))
                {
                    e.setCancelled(true);
                    for(Entity en : p.getNearbyEntities(12,12,12))
                    {
                        if(en instanceof Arrow) {
                            Arrow arr = (Arrow) en;
                            if (arr.getVehicle() == null)
                            {
                                Firework f = arr.getWorld().spawn(arr.getLocation(), Firework.class);
                                FireworkMeta meta = f.getFireworkMeta();
                                meta.setPower(3);
                                f.setFireworkMeta(meta);
                                f.addPassenger(arr);
                            }

                        }
                    }
                    //
                    thiscd.putCooldown(p,config.getInt("terminatorCooldown"));
                }
                else
                {
                    p.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(p)+1));
                }

            }
        }

    }
        @EventHandler
        public void PlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent e)
        {
            Player p = e.getPlayer();
            if((e.getOffHandItem().getItemMeta() != null && e.getOffHandItem().getItemMeta().getLore() != null && e.getOffHandItem().getItemMeta().getLore().contains(lib.Lore.get("Annihilation").get(1))))
            {
                e.setCancelled(true);
                for(Entity en : p.getNearbyEntities(12,12,12))
                {
                    if(en instanceof Arrow)
                    {
                        Arrow arr = (Arrow)en;
                        arr.getWorld().createExplosion(arr.getLocation().getX(),arr.getLocation().getY(),arr.getLocation().getZ(),2,false,false);
                        arr.remove();
                    }
                }
            }
        }


    }

