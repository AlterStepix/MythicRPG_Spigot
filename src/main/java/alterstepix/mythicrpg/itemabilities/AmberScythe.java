package alterstepix.mythicrpg.itemabilities;


import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.misc.ParticleSpawner;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AmberScythe implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;
    Cooldown thiscd = new Cooldown();
    FileConfiguration config;

    public AmberScythe(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        this.lib.Init();
        this.thiscd.init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("FireFury").get(1))) {
                if(thiscd.checkCD(player))
                {
                    new BukkitRunnable()
                    {
                        int i = 0;
                        public void run()
                        {
                            Fireball fire = player.getWorld().spawn(player.getLocation().add(player.getLocation().getDirection().normalize().multiply(2.5)),Fireball.class);
                            fire.setDirection(player.getLocation().getDirection());
                            fire.setVelocity(player.getLocation().getDirection());

                            new BukkitRunnable()
                            {

                                @Override
                                public void run() {
                                    fire.getWorld().spawnParticle(Particle.LAVA,fire.getLocation(),10,0.5,0.5,0.5);
                                    if(fire.isDead())
                                        cancel();
                                    if(!fire.isValid())
                                        cancel();
                                }
                            }.runTaskTimer(main,0L,2L);

                            i++;
                            if(i > 2)
                            {
                                cancel();
                            }
                        }
                    }.runTaskTimer(main,0L,15L);
                    thiscd.putCooldown(player,config.getInt("amberScytheCooldown"));

                    Location location = player.getEyeLocation();






                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (thiscd.getCooldownTime(player)+1));
                }



            }
        }
    }

}
