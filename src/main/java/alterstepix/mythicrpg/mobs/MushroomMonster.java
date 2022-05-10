package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.GetPlayerHead;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;


public class MushroomMonster implements Listener {

    Mythicrpg main;
    FileConfiguration config;

    public MushroomMonster(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createMushroomMonster(Location location)
    {
        Zombie monster = location.getWorld().spawn(location, Zombie.class);
        monster.setMaxHealth(config.getInt("MushroomMonsterHealth"));
        monster.setHealth(config.getInt("MushroomMonsterHealth"));

        AttributeInstance Def = monster.getAttribute(Attribute.GENERIC_ARMOR);
        Def.setBaseValue(60);

        monster.setCustomNameVisible(true);

        ItemStack cp = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta lMeta = (LeatherArmorMeta) cp.getItemMeta();
        lMeta.setColor(Color.RED);
        lMeta.setUnbreakable(true);

        cp.setItemMeta(lMeta);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        leggings.setItemMeta(lMeta);
        boots.setItemMeta(lMeta);

        monster.getEquipment().setBoots(boots);
        monster.getEquipment().setLeggings(leggings);
        monster.getEquipment().setChestplate(cp);
        monster.getEquipment().setHelmet(GetPlayerHead.GetCustomHead(GetPlayerHead.MushroomMosterHead));

        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                if(!monster.isDead())
                {
                    monster.setCustomName(ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("MushroomMonsterNametag")) + " §7[" + Math.round(monster.getHealth()) + "/" + monster.getMaxHealth() + "]");
                    if(i % 5 == 0)
                    {
                        for(Entity entity : monster.getNearbyEntities(12,12,12))
                        {
                            if(entity instanceof LivingEntity trg)
                            {
                                trg.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,60,1,false,false));
                            }
                        }
                    }
                    if(i % 7 == 0)
                    {
                        for (int i = 0; i < 3; i++)
                        {
                            ItemStack item = new ItemStack(Material.BROWN_MUSHROOM);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(i+"");
                            item.setItemMeta(meta);
                            Item web = monster.getWorld().dropItem(monster.getLocation(),item);
                            web.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3),ThreadLocalRandom.current().nextDouble(0.7, 1.2),0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI*2)));

                            web.setCustomName("§3BROWN_SHROOM");
                        }
                    }
                    if(i % 11 == 0)
                    {
                        for (int i = 0; i < 3; i++)
                        {
                            ItemStack item = new ItemStack(Material.RED_MUSHROOM);
                            ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(i+"");
                            item.setItemMeta(meta);
                            Item web = monster.getWorld().dropItem(monster.getLocation(),item);
                            web.setVelocity((new Vector(ThreadLocalRandom.current().nextDouble(0.1, 0.3),ThreadLocalRandom.current().nextDouble(0.7, 1.2),0)).rotateAroundY(ThreadLocalRandom.current().nextDouble(0, Math.PI*2)));

                            web.setCustomName("§3RED_SHROOM");
                        }
                    }

                    i++;
                }
                else
                {
                    monster.remove();
                    cancel();
                }
            }
        }.runTaskTimer(main,0L,20L);
    }

    @EventHandler
    public void Pickup_item(EntityPickupItemEvent e)
    {
        if(e.getEntity() instanceof Player p)
        {
            if(e.getItem().getCustomName() != null &&e.getItem().getCustomName().contains("§3BROWN_SHROOM"))
            {
                e.setCancelled(true);
                e.getItem().remove();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,60,3,false,false,false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,60,5,false,false,false));
                p.damage(2);

            }
            else if(e.getItem().getCustomName() != null &&e.getItem().getCustomName().contains("§3RED_SHROOM"))
            {
                e.setCancelled(true);
                e.getItem().remove();
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON,60,14,false,false,false));
                p.damage(7);

            }

        }
    }

}
