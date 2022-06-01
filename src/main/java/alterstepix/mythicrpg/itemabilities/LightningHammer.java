package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.misc.ParticleManager;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class LightningHammer implements Listener {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    Cooldown thiscd;

    public LightningHammer(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        this.lib.Init();
        this.thiscd = new Cooldown();
        this.thiscd.init();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Thunderidol").get(1))) {
                if (thiscd.checkCD(player)) {
                    thiscd.putCooldown(player,config.getInt("lightningHammerCooldown"));

                    for(float i = 0; i < 180; i++)
                    {
                        ParticleManager.wave(player.getLocation().add(0,1,0),player.getLocation().getDirection().normalize().setY(0).rotateAroundY( 2*i / 360 * 2 * Math.PI), Particle.CLOUD);
                    }

                    for(Entity entity : player.getNearbyEntities(27,27,27))
                    {
                        if(entity instanceof LivingEntity trg)
                        {
                            trg.getWorld().strikeLightningEffect(trg.getLocation());
                            trg.damage(50);
                            trg.setVelocity(new Vector(0,0.2,0));
                            trg.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,trg.getLocation(),70,1,1,1,0,new Particle.DustTransition(Color.GRAY,Color.BLACK,2),true);
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
