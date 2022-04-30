package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.GetPlayerHead;
import alterstepix.mythicrpg.util.MobItemCreator;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Cyclops {
    FileConfiguration config;
    Mythicrpg main;
    public Cyclops(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public LivingEntity summon(Location location)
    {
        Skeleton skeleton = location.getWorld().spawn(location, Skeleton.class);
        skeleton.setMaxHealth(config.getInt("CyclopsHealth"));
        skeleton.setHealth(config.getInt("CyclopsHealth"));
        skeleton.getEquipment().setHelmet(GetPlayerHead.GetCustomHead(GetPlayerHead.CyclopsHead));
        if(Math.random() < 0.3)
        {
            skeleton.getEquipment().setChestplate(MobItemCreator.leather(Material.LEATHER_CHESTPLATE,0,162,176,121));
            skeleton.getEquipment().setLeggings(MobItemCreator.leather(Material.LEATHER_LEGGINGS,0,162,176,121));
            skeleton.getEquipment().setBoots(MobItemCreator.leather(Material.LEATHER_BOOTS,0,162,176,121));
            skeleton.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.STONE_SWORD,0));
        }
        else if(Math.random() < 0.6)
        {
            skeleton.getEquipment().setChestplate(MobItemCreator.leather(Material.LEATHER_CHESTPLATE,0,121,123,176));
            skeleton.getEquipment().setLeggings(MobItemCreator.leather(Material.LEATHER_LEGGINGS,0,121,123,176));
            skeleton.getEquipment().setBoots(MobItemCreator.leather(Material.LEATHER_BOOTS,0,121,123,176));
            skeleton.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.STONE_AXE,0));
        }
        else
        {
            skeleton.getEquipment().setChestplate(MobItemCreator.leather(Material.LEATHER_CHESTPLATE,0,121,176,165));
            skeleton.getEquipment().setLeggings(MobItemCreator.leather(Material.LEATHER_LEGGINGS,0,121,176,165));
            skeleton.getEquipment().setBoots(MobItemCreator.leather(Material.LEATHER_BOOTS,0,121,176,165));
            skeleton.getEquipment().setItemInMainHand(MobItemCreator.weapon(Material.IRON_SWORD,0));
        }


        AttributeInstance def = skeleton.getAttribute(Attribute.GENERIC_ARMOR);
        def.setBaseValue(40);

        skeleton.setCustomNameVisible(true);

        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                if(!skeleton.isDead())
                {
                    skeleton.setCustomName((ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("CyclopsNametag")) + " §7[" + Math.round(skeleton.getHealth()) + "/" + skeleton.getMaxHealth() + "]"));
                    if(i%6==0 && skeleton.getTarget() != null)
                    {
                        Guardian lasser = skeleton.getWorld().spawn(skeleton.getEyeLocation(),Guardian.class);
                        lasser.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,999999,1,false,false,false));
                        lasser.setGravity(false);
                        lasser.setInvulnerable(true);
                        lasser.setAI(false);
                        lasser.setTarget(skeleton.getTarget());
                        lasser.setLaser(true);
                        new BukkitRunnable()
                        {
                            int k = 0;
                            @Override
                            public void run() {
                                if(skeleton.isDead())
                                {
                                    lasser.remove();
                                    cancel();
                                }
                                if(skeleton.getTarget() != null)
                                {
                                    skeleton.getTarget().damage(1,skeleton);
                                    lasser.teleport(skeleton.getEyeLocation());
                                }
                                if(k > 20)
                                {
                                    lasser.remove();
                                    cancel();
                                }
                                k++;
                            }
                        }.runTaskTimer(main,0L,2L);

                    }
                    if(i % 8 == 0 && skeleton.getTarget() != null)
                    {
                        skeleton.getWorld().playSound(skeleton.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 5, 5);
                        skeleton.getWorld().spawnParticle(Particle.CRIT_MAGIC,skeleton.getLocation(),10);
                        skeleton.setVelocity(skeleton.getTarget().getLocation().subtract(skeleton.getLocation()).toVector().multiply(0.275).add(new Vector(0,0.6,0)));
                        skeleton.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,80,3,false,false,false));
                    }
                    i++;
                }
                else
                {
                    skeleton.remove();
                    cancel();
                }

            }
        }.runTaskTimer(main,0L,20L);

        return skeleton;
    }
}
