package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class AirSpirit {

    Mythicrpg main;
    FileConfiguration config;

    public AirSpirit(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createAirSpirit(Location location)
    {
        Skeleton spirit = location.getWorld().spawn(location, Skeleton.class);

        spirit.setMaxHealth(config.getInt("AirSpiritHealth"));
        spirit.setHealth(config.getInt("AirSpiritHealth"));

        spirit.setCustomName(config.getString("AirSpiritNametag"));
        spirit.setCustomNameVisible(true);

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta armormeta = (LeatherArmorMeta)helmet.getItemMeta();
        armormeta.setColor(Color.WHITE);
        armormeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);
        helmet.setItemMeta(armormeta);
        chestplate.setItemMeta(armormeta);
        leggings.setItemMeta(armormeta);
        boots.setItemMeta(armormeta);

        spirit.getEquipment().setHelmet(helmet);
        spirit.getEquipment().setChestplate(chestplate);
        spirit.getEquipment().setLeggings(leggings);
        spirit.getEquipment().setBoots(boots);
        spirit.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD,1));

        spirit.setCustomName(ColorUtil.ConvertToCustom(config.getString("AirSpiritNametag")) + " §7["+Math.round(spirit.getHealth())+"/"+spirit.getMaxHealth()+"]");

        new BukkitRunnable() {
        int i = 0;

            public void run() {
                spirit.setCustomName(ColorUtil.ConvertToCustom(config.getString("AirSpiritNametag")) + " §7[" + Math.round(spirit.getHealth()) + "/" + spirit.getMaxHealth() + "]");

                if (!spirit.isDead())
                {
                    if (i % 10 == 0 || i % 10 == 1 || i % 10 == 2)
                    {
                        for(Entity entity : spirit.getNearbyEntities(10,10,10))
                        {
                            if(entity instanceof Player)
                            {
                                Player trg = (Player) entity;
                                trg.setVelocity(spirit.getLocation().add(0,2,0).subtract(trg.getLocation()).toVector().normalize().setY(0.1));
                                spirit.getWorld().spawnParticle(Particle.CLOUD,spirit.getLocation(),5);
                                spirit.getWorld().playSound(spirit.getLocation(),Sound.ENTITY_PLAYER_ATTACK_CRIT,8,5);
                            }
                        }
                    }
                }
                else
                {
                    cancel();
                }


                i++;
            }

        }.runTaskTimer(main,0L,20L);

    }

}
