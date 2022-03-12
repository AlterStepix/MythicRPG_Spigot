package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class FuriousAxe implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    Cooldown thiscd = new Cooldown();
    ItemLoreLibrary lib;

    public FuriousAxe(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        thiscd.init();
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event)
    {
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Throw").get(1))) {
                if (thiscd.checkCD(player)) {
                    thiscd.putCooldown(player,config.getInt("furiousAxeCooldown"));

                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0,0.5,0), EntityType.ARMOR_STAND);
                    armorStand.setArms(true);
                    armorStand.setGravity(false);
                    armorStand.setVisible(false);
                    armorStand.setMarker(true);
                    armorStand.setItemInHand(player.getInventory().getItemInMainHand());
                    armorStand.setRightArmPose(new EulerAngle(Math.toRadians(90),Math.toRadians(0),Math.toRadians(0)));

                    Location destination = player.getLocation().add(player.getLocation().getDirection().multiply(7));
                    Vector vector = destination.subtract(player.getLocation()).toVector();

                    new BukkitRunnable(){
                        int distance = 30;
                        int i = 0;

                        public void run()
                        {
                            EulerAngle rot = armorStand.getRightArmPose();
                            armorStand.setRightArmPose(rot.add(5,0,0));

                            armorStand.teleport(armorStand.getLocation().add(vector.normalize()));

                            if(armorStand.getTargetBlockExact(1) != null && !armorStand.getTargetBlockExact(1).isPassable())
                            {
                                if(!armorStand.isDead())
                                {
                                    armorStand.remove();
                                    cancel();

                                }
                            }
                            for(Entity entity : armorStand.getLocation().getChunk().getEntities()) {
                                if (!armorStand.isDead())
                                {
                                    if(armorStand.getLocation().distanceSquared(entity.getLocation()) <= 1)
                                    {
                                        if(entity != player && entity != armorStand)
                                        {
                                            if(entity instanceof LivingEntity)
                                            {
                                                LivingEntity trg = (LivingEntity) entity;
                                                trg.damage(2,player);
                                                armorStand.remove();
                                            }
                                        }
                                    }
                                }
                            }
                            if(i > distance)
                            {
                                armorStand.remove();
                            }
                            i++;
                        }
                    }.runTaskTimer(main,0L,1L);

                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                }
            }
        }
    }
}
