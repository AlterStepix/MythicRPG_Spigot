package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import alterstepix.mythicrpg.managers.ItemManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
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

public class RunicDagger implements Listener {
    Mythicrpg main;
    ItemLoreLibrary lib;
    ItemManager manager;
    FileConfiguration config;

    public RunicDagger(Mythicrpg main)
    {
        this.main = main;
        lib = new ItemLoreLibrary(main);
        lib.Init();
        manager = new ItemManager(main);
        manager.init();
        config = main.getConfig();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicSwap").get(1))) {
                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicMolten").get(1))) {

                    Particle.DustTransition tr = new Particle.DustTransition(Color.BLUE,Color.AQUA,1.5f);
                    player.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,player.getEyeLocation(),25,1,0.4,1,tr);

                    player.getInventory().setItemInMainHand(manager.RuinicDagger[0]);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("§bFrozen"));
                }
                else if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicFrozen").get(1))) {

                    Particle.DustTransition tr = new Particle.DustTransition(Color.RED,Color.ORANGE,1.5f);
                    player.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,player.getEyeLocation(),25,1,0.4,1,tr);

                    player.getInventory().setItemInMainHand(manager.RuinicDagger[1]);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("§6Molten"));
                }
            }
        }
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e)
    {
        if(e.getDamager() instanceof Player)
        {
            Player player = (Player)e.getDamager();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicMolten").get(1))) {
                if(e.getEntity() instanceof LivingEntity)
                {
                    LivingEntity trg = (LivingEntity) e.getEntity();
                    trg.setFireTicks(trg.getFireTicks()+60);
                    if(trg.getCustomName() != null && trg.getCustomName().contains(config.getString("IceSpiritNametag").split("!")[1]))
                    {
                        trg.damage(10);
                    }

                    new BukkitRunnable()
                    {
                        int i = 0;
                        @Override
                        public void run() {

                            if(trg.isDead())
                                cancel();

                            Particle.DustTransition tr = new Particle.DustTransition(Color.RED,Color.ORANGE,0.3f);
                            trg.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,trg.getEyeLocation(),35,0.3,0.4,0.3,tr);

                            trg.damage(3);
                            if(i > 3)
                                cancel();

                            i++;
                        }

                    }.runTaskTimer(main,0L,5L);
                }
            }
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("RunicFrozen").get(1))) {
                if(e.getEntity() instanceof LivingEntity)
                {
                    LivingEntity trg = (LivingEntity) e.getEntity();
                    trg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,2,false,false,false));
                    if(trg.getCustomName() != null && trg.getCustomName().contains(config.getString("FireSpiritNametag").split("!")[1]))
                    {
                        trg.damage(10);
                    }

                    new BukkitRunnable()
                    {
                        int i = 0;
                        @Override
                        public void run() {

                            if(trg.isDead())
                                cancel();

                            Particle.DustTransition tr = new Particle.DustTransition(Color.BLUE,Color.AQUA,0.3f);
                            trg.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,trg.getEyeLocation(),30,0.3,0.4,0.3,tr);

                            trg.damage(6);
                            if(i > 3)
                                cancel();

                            i++;
                        }

                    }.runTaskTimer(main,0L,25L);
                }
            }
        }
    }
}
