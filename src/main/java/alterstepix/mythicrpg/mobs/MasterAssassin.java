package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.WitherSkull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MasterAssassin {
    Mythicrpg main;
    FileConfiguration config;

    public MasterAssassin(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createMasterAssassin(Location loc)
    {
        int hp = config.getInt("MasterAssassinHealth");

        Skeleton skeleton = loc.getWorld().spawn(loc,Skeleton.class);
        skeleton.setCustomName(config.getString("MasterAssassinNametag"));
        skeleton.setCustomNameVisible(true);

        Attributable skeletonAt = skeleton;
        AttributeInstance attributeHP = skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHP.setBaseValue(hp);
        skeleton.setHealth(hp);
        AttributeInstance attributeSpeed = skeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(0.2);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta smeta = sword.getItemMeta();
        smeta.addEnchant(Enchantment.DAMAGE_ALL,3,true);
        sword.setItemMeta(smeta);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) leggings.getItemMeta();
        meta.setColor(Color.BLACK);
        leggings.setItemMeta(meta);
        boots.setItemMeta(meta);

        skeleton.getEquipment().setBoots(boots);
        skeleton.getEquipment().setLeggings(leggings);
        skeleton.getEquipment().setChestplate(chestplate);
        skeleton.getEquipment().setItemInMainHand(sword);

        skeleton.setCustomName(config.getString("MasterAssassinNametag") + " §7["+Math.round(skeleton.getHealth())+"/"+skeleton.getMaxHealth()+"]");

        new BukkitRunnable() {
            public void run()
            {
                int i = 0;
                if(!skeleton.isDead())
                {
                    if(skeleton.getTarget() != null)
                    {
                        if(i % 10 == 0)
                        {
                            Vector offset = skeleton.getTarget().getLocation().getDirection().setY(0).normalize().multiply(-1);
                            if(skeleton.getTarget().getLocation().add(offset).getBlock().getType() == Material.AIR)
                            {
                                skeleton.teleport(skeleton.getTarget().getLocation().add(offset));
                                skeleton.getWorld().playSound(skeleton.getLocation(), Sound.ENTITY_WITHER_SHOOT,5,5);
                                skeleton.getWorld().spawnParticle(Particle.SQUID_INK,skeleton.getLocation(),3);
                            }
                            else {
                                if (i % 40 == 0)
                                {
                                    skeleton.getTarget().setVelocity((skeleton.getLocation().add(0,2,0).subtract(skeleton.getTarget().getLocation()).toVector().multiply(0.275)));
                                    skeleton.getWorld().spawnParticle(Particle.SPELL_WITCH, skeleton.getLocation(),5);
                                    skeleton.getWorld().playSound(skeleton.getLocation(),Sound.BLOCK_ANVIL_BREAK,5,5);
                                }
                            }
;
                        }
                    }

                    i++;
                }
                else{
                    skeleton.remove();
                    cancel();
                }
                i++;
            }
        }.runTaskTimer(main,0L,20L);
    }
}
