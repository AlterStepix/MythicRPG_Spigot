package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Cooldown;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class CorruptedMythicIdolsIncarnate implements Listener {
    Cooldown cd;
    Mythicrpg main;
    ItemLoreLibrary lib;

    public CorruptedMythicIdolsIncarnate(Mythicrpg main)
    {
        this.main = main;
        lib = new ItemLoreLibrary(main);
        lib.Init();
        this.cd = new Cooldown();
        cd.init();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity && event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            LivingEntity entity = (LivingEntity) event.getEntity();

            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("CorruptedCurse").get(1))) {
                PotionEffect WEff = new PotionEffect(PotionEffectType.WITHER, 100, 7, true, true, true);
                PotionEffect CEff = new PotionEffect(PotionEffectType.CONFUSION, 400, 1, true, true, true);
                PotionEffect SEff = new PotionEffect(PotionEffectType.SLOW, 60, 3, true, true, true);
                PotionEffect SDEff = new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 3, true, true, true);
                PotionEffect HEff = new PotionEffect(PotionEffectType.HUNGER, 200, 6, true, true, true);
                PotionEffect PEff = new PotionEffect(PotionEffectType.POISON, 100, 7, true, true, true);
                PotionEffect BEff = new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, true, true, true);
                PotionEffect WEEff = new PotionEffect(PotionEffectType.WEAKNESS, 100, 3, true, true, true);

                double a = Math.random();
                if (a < 0.5) {
                    entity.addPotionEffect(WEff); //WITHER
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 0.8) {
                    entity.addPotionEffect(CEff); //CONFUSION
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 0.9) {
                    entity.addPotionEffect(SEff); //SLOW
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 0.9) {
                    entity.addPotionEffect(SDEff); //SLOW_DIGGING
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 1) {
                    entity.addPotionEffect(HEff); //HUNGER
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 0.6) {
                    entity.addPotionEffect(PEff); //POISON
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 0.6) {
                    entity.setFireTicks(entity.getFireTicks() + 80); //FIRE
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.LAVA, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 0.7) {
                    entity.addPotionEffect(BEff); //BLINDNESS
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 0.5) {
                    entity.getWorld().strikeLightningEffect(entity.getLocation()); // LIGHTNING
                    entity.damage(16);
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }
                a = Math.random();
                if (a < 0.7) {
                    entity.addPotionEffect(WEEff); //WEAKNESS
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                }

            }
        }
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("CorruptedCurse").get(1))) {
                if (cd.checkCD(player)) {
                    if(player.isOnGround())
                    {
                        cd.putCooldown(player,15);
                        Giant totem = player.getWorld().spawn(player.getLocation(),Giant.class);
                        totem.setInvisible(true);
                        totem.setInvulnerable(true);
                        totem.setCollidable(false);
                        totem.setCustomName("Dinnerbone");
                        totem.getEquipment().setItemInMainHand(player.getInventory().getItemInMainHand());
                        totem.setAI(false);

                        new BukkitRunnable()
                        {
                            int i = 1;
                            @Override
                            public void run() {
                                for(Entity entity : totem.getNearbyEntities(12,12,12))
                                {
                                    if(entity instanceof LivingEntity target && entity != player)
                                    {
                                        PotionEffect PEff = new PotionEffect(PotionEffectType.POISON, 100, 7, true, true, true);
                                        PotionEffect WEff = new PotionEffect(PotionEffectType.WITHER, 100, 7, true, true, true);
                                        target.damage(30);
                                        if (Math.random() < 0.8) {
                                            target.addPotionEffect(PEff); //POISON
                                            target.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                                            target.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                                            target.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                                        }
                                        if (Math.random() < 0.8) {
                                            target.addPotionEffect(WEff); //WITHER
                                            target.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                                            target.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation(), 15);
                                            target.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE, 15, 5);
                                        }
                                    }
                                }
                                if(i > 6)
                                {
                                    totem.remove();
                                    cancel();
                                }
                                player.sendTitle("§5"+(7-i),"",5,15,5);
                                totem.getWorld().spawnParticle(Particle.SPELL_WITCH,totem.getEyeLocation(),25);
                                i++;
                            }
                        }.runTaskTimer(main,0L,20L);
                    }
                }
                else
                {
                    player.sendMessage("§c[Mythic RPG] This item is on cooldown for " + (cd.getCooldownTime(player)+1));
                }
            }
        }
    }
}
