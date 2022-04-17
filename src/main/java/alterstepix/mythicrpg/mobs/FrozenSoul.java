package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.GetPlayerHead;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class FrozenSoul implements Listener {

    Mythicrpg main;
    FileConfiguration config;

    public FrozenSoul(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createFrozenSoul(Location location)
    {
        Skeleton skeleton = location.getWorld().spawn(location,Skeleton.class);

        AttributeInstance Health = skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        Health.setBaseValue(config.getInt("FrozenSoulHealth"));
        skeleton.setHealth(config.getInt("FrozenSoulHealth"));

        AttributeInstance Def = skeleton.getAttribute(Attribute.GENERIC_ARMOR);
        Def.setBaseValue(40);

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = chestplate.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,20,true);
        chestplate.setItemMeta(meta);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta lmeta = (LeatherArmorMeta) leggings.getItemMeta();
        lmeta.setUnbreakable(true);
        lmeta.setColor(Color.AQUA);
        lmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,20,true);
        leggings.setItemMeta(lmeta);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        boots.setItemMeta(lmeta);

        ItemStack sword = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setUnbreakable(true);
        swordMeta.addEnchant(Enchantment.DAMAGE_ALL,15,true);
        sword.setItemMeta(meta);

        skeleton.getEquipment().setHelmet(GetPlayerHead.GetCustomHead(GetPlayerHead.FrozenSoulHead));
        skeleton.getEquipment().setChestplate(chestplate);
        skeleton.getEquipment().setLeggings(leggings);
        skeleton.getEquipment().setBoots(boots);
        skeleton.getEquipment().setItemInMainHand(sword);

        skeleton.setCustomNameVisible(true);
        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                if(!skeleton.isDead()) {
                    skeleton.setCustomName(ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("FrozenSoulNametag")) + " §7[" + Math.round(skeleton.getHealth()) + "/" + skeleton.getMaxHealth() + "]");
                    if (i % 5 == 0) {
                        if (skeleton.getTarget() != null) {
                            skeleton.getTarget().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 3, false, false, false));
                            for (Entity e : skeleton.getNearbyEntities(7, 7, 7)) {
                                if (e instanceof LivingEntity) {
                                    ((LivingEntity) e).damage(6);
                                }
                            }
                        }
                    }
                    if (i % 2 == 0)
                    {
                        if(skeleton.getTarget() != null)
                        {
                            FallingBlock fallingBlock = skeleton.getWorld().spawnFallingBlock(skeleton.getLocation().add(0, 2, 0), Material.PACKED_ICE, (byte) 0);
                            fallingBlock.setCustomName("Frozen orb");
                            fallingBlock.setDropItem(false);
                            fallingBlock.setVelocity(skeleton.getTarget().getLocation().add(0, 1, 0).subtract(fallingBlock.getLocation()).toVector().multiply(0.5));
                            fallingBlock.getWorld().playSound(fallingBlock.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_BREAK, 5, 5);
                            new BukkitRunnable(){
                                public void run()
                                {
                                    if(!fallingBlock.isDead())
                                    {
                                        fallingBlock.getWorld().spawnParticle(Particle.SNOWFLAKE,fallingBlock.getLocation(),3);
                                        for(Entity entity : fallingBlock.getNearbyEntities(2,2,2))
                                        {
                                            if(entity instanceof Player){
                                                if(fallingBlock.getLocation().distanceSquared(entity.getLocation()) < 1)
                                                {
                                                    Player player = (Player) entity;
                                                    PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, 60,3,true,true,true);
                                                    PotionEffect effect1 = new PotionEffect(PotionEffectType.WEAKNESS, 40, 0,true,true,true);
                                                    player.addPotionEffect(effect);
                                                    player.addPotionEffect(effect1);
                                                    player.damage(4,skeleton);
                                                    player.getWorld().spawnParticle(Particle.SNOWBALL,player.getLocation(),5);
                                                    fallingBlock.remove();
                                                    cancel();
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        cancel();
                                    }
                                }
                            }.runTaskTimer(main,0L,2L);
                        }

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

    }

    @EventHandler
    public void onChange(EntityChangeBlockEvent event)
    {
        if(event.getEntity() instanceof FallingBlock)
        {
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("Frozen orb"))
            {
                event.setCancelled(true);
                event.getEntity().remove();
            }
        }
    }

}
