package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.GetPlayerHead;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.concurrent.ThreadLocalRandom;

public class RevenantArcher implements Listener {
    Mythicrpg main;
    FileConfiguration config;

    public RevenantArcher(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createRevenantArcher(Location location)
    {
        Skeleton archer = location.getWorld().spawn(location,Skeleton.class);

        AttributeInstance Health = archer.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        AttributeInstance Def = archer.getAttribute(Attribute.GENERIC_ARMOR);

        Health.setBaseValue(config.getInt("RevenantArcherHealth"));
        Def.setBaseValue(20);
        archer.setHealth(config.getInt("RevenantArcherHealth"));

        archer.getEquipment().setHelmet(GetPlayerHead.GetCustomHead(GetPlayerHead.RevenantArcherHead));

        ItemStack cp = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack lg = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

        LeatherArmorMeta l_Meta = (LeatherArmorMeta) lg.getItemMeta();
        l_Meta.setColor(Color.BLACK);
        l_Meta.setUnbreakable(true);

        lg.setItemMeta(l_Meta);
        boots.setItemMeta(l_Meta);
        cp.setItemMeta(l_Meta);

        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setUnbreakable(true);
        bowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK,2,true);
        bow.setItemMeta(bowMeta);

        archer.getEquipment().setChestplate(cp);
        archer.getEquipment().setLeggings(lg);
        archer.getEquipment().setBoots(boots);
        archer.getEquipment().setItemInMainHand(bow);

        archer.setCustomNameVisible(true);

        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                if(!archer.isDead())
                {
                    archer.setCustomName(ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("RevenantArcherNametag")) + " §7["+Math.round(archer.getHealth())+"/"+archer.getMaxHealth()+"]");
                    if(archer.getTarget() != null)
                    {
                        if(archer.getTarget().getLocation().distanceSquared(archer.getLocation()) < 16)
                        {
                            while (true)
                            {
                                double offsetX = ThreadLocalRandom.current().nextDouble(-6, 6);
                                double offsetZ = ThreadLocalRandom.current().nextDouble(-6, 6);
                                if(archer.getWorld().getBlockAt(archer.getLocation().add(offsetX,0,offsetZ)).isPassable() || archer.getWorld().getBlockAt(archer.getLocation().add(offsetX,0,offsetZ)).getType() == Material.AIR)
                                {
                                    archer.teleport(archer.getLocation().add(offsetX,0,offsetZ));
                                    archer.getWorld().spawnParticle(Particle.SQUID_INK,archer.getLocation(),5);
                                    archer.getWorld().playSound(archer.getLocation(), Sound.ENTITY_WITHER_SHOOT,5,5);
                                    break;
                                }
                            }

                        }
                    }
                    i++;
                }
                else
                {
                    archer.remove();
                    cancel();
                }
            }
        }.runTaskTimer(main,0L,20L);

    }

    @EventHandler
    public void onHitEvent(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof LivingEntity entity) {
            if (entity.getCustomName() != null && entity.getCustomName().contains(config.getString("RevenantArcherNametag").split("!")[1])) {
                Monster archer = (Monster) entity;
                if(archer.getTarget() != null)
                {
                    if(archer.getTarget().getLocation().distanceSquared(archer.getLocation()) < 16)
                    {
                        while (true)
                        {
                            double offsetX = ThreadLocalRandom.current().nextDouble(-6, 6);
                            double offsetZ = ThreadLocalRandom.current().nextDouble(-6, 6);
                            if(archer.getWorld().getBlockAt(archer.getLocation().add(offsetX,0,offsetZ)).isPassable() || archer.getWorld().getBlockAt(archer.getLocation().add(offsetX,0,offsetZ)).getType() == Material.AIR)
                            {
                                archer.teleport(archer.getLocation().add(offsetX,0,offsetZ));
                                archer.getWorld().spawnParticle(Particle.SQUID_INK,archer.getLocation(),5);
                                archer.getWorld().playSound(archer.getLocation(), Sound.ENTITY_WITHER_SHOOT,5,5);
                                break;
                            }
                        }

                    }
                }
            }
        }
    }
}
