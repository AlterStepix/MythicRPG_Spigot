package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.GetPlayerHead;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class IceSpirit {

    Mythicrpg main;
    FileConfiguration config;

    public IceSpirit(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createIceSpirit(Location location)
    {
        Skeleton spirit = location.getWorld().spawn(location, Skeleton.class);

        spirit.setMaxHealth(config.getInt("IceSpiritHealth"));
        spirit.setHealth(config.getInt("IceSpiritHealth"));

        spirit.setCustomName(config.getString("IceSpiritNametag"));
        spirit.setCustomNameVisible(true);

        ItemStack helmet = GetPlayerHead.GetCustomHead(GetPlayerHead.FrozenSpiritHead);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta armormeta = (LeatherArmorMeta)chestplate.getItemMeta();
        armormeta.setColor(Color.fromRGB(119,181 ,255));
        armormeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,9,true);
        chestplate.setItemMeta(armormeta);
        leggings.setItemMeta(armormeta);
        boots.setItemMeta(armormeta);

        spirit.getEquipment().setHelmet(helmet);
        spirit.getEquipment().setChestplate(chestplate);
        spirit.getEquipment().setLeggings(leggings);
        spirit.getEquipment().setBoots(boots);
        spirit.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD,1));

        spirit.setCustomName(ColorUtil.ConvertToCustom(config.getString("IceSpiritNametag")) + " §7["+Math.round(spirit.getHealth())+"/"+spirit.getMaxHealth()+"]");

        new BukkitRunnable() {
            int i = 0;

            public void run() {
                spirit.setCustomName(ColorUtil.ConvertToCustom(config.getString("IceSpiritNametag")) + " §7[" + Math.round(spirit.getHealth()) + "/" + spirit.getMaxHealth() + "]");

                if (!spirit.isDead())
                {
                    if (i % 10 == 0)
                    {
                        for(Entity entity : spirit.getNearbyEntities(10,10,10))
                        {
                            if(entity instanceof Player)
                            {
                                Player trg = (Player) entity;
                                trg.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,100,3,false,false,false));
                                trg.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,100,1,true,true,true));
                                trg.damage(2);
                                spirit.getWorld().spawnParticle(Particle.SNOWBALL,spirit.getLocation(),5);
                                spirit.getWorld().playSound(spirit.getLocation(),Sound.ENTITY_SNOW_GOLEM_SHOOT,8,5);
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
