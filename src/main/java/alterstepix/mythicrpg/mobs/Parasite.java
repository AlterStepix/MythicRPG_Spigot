package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
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

import java.util.Random;

public class Parasite implements Listener {
    static Mythicrpg main;
    FileConfiguration config;

    public Parasite(Mythicrpg main)
    {
        this.main = main;
        config = main.getConfig();
    }

    public void createNecromancer(Location location){
        int hp = config.getInt("ParasiteHealth");
        Zombie skeleton = location.getWorld().spawn(location, Zombie.class);

        skeleton.setCustomName(ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("ParasiteNametag")));
        skeleton.setCustomNameVisible(true);
        Attributable skeletonAt = skeleton;
        AttributeInstance attributeHP = skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHP.setBaseValue(hp);
        skeleton.setHealth(hp);
        AttributeInstance attributeSpeed = skeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(0.1);

        ItemStack cp = new ItemStack(Material.LEATHER_CHESTPLATE,1);
        LeatherArmorMeta meta = (LeatherArmorMeta)cp.getItemMeta();
        meta.setColor(Color.GREEN);
        cp.setItemMeta(meta);

        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS,1);
        ItemMeta meta2 = boots.getItemMeta();
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,8,true);
        boots.setItemMeta(meta2);

        skeleton.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
        skeleton.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        skeleton.getEquipment().setChestplate(cp);
        skeleton.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        skeleton.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        skeleton.setCustomName(ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("ParasiteNametag")) + " §7["+Math.round(skeleton.getHealth())+"/"+skeleton.getMaxHealth()+"]");
        new BukkitRunnable(){
            int i = 0;
            int k = 0;
            public void run()
            {
                if(!skeleton.isDead())
                {

                    skeleton.setCustomName(ColorUtil.ConvertToCustom(config.getString("MiniBossPrefix")) + ColorUtil.ConvertToCustom(config.getString("ParasiteNametag")) + " §7["+Math.round(skeleton.getHealth())+"/"+skeleton.getMaxHealth()+"]");

                    if(skeleton.getTarget() != null){
                        if(i % 2 == 0)
                        {
                            FallingBlock fallingBlock = skeleton.getWorld().spawnFallingBlock(skeleton.getLocation().add(0,2,0),Material.JUNGLE_LEAVES, (byte) 0);
                            fallingBlock.setCustomName("Parasite orb");
                            fallingBlock.setDropItem(false);
                            fallingBlock.setVelocity(skeleton.getTarget().getLocation().add(0,1,0).subtract(fallingBlock.getLocation()).toVector().multiply(0.5));
                            fallingBlock.getWorld().playSound(fallingBlock.getLocation(), Sound.BLOCK_COMPOSTER_FILL_SUCCESS,5,5);
                            new BukkitRunnable(){
                                public void run()
                                {
                                    if(!fallingBlock.isDead())
                                    {
                                        fallingBlock.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME,fallingBlock.getLocation(),1);
                                        for(Entity entity : fallingBlock.getNearbyEntities(2,2,2))
                                        {
                                            if(entity instanceof Player){
                                                if(fallingBlock.getLocation().distanceSquared(entity.getLocation()) < 1)
                                                {
                                                    Player player = (Player) entity;
                                                    PotionEffect effect = new PotionEffect(PotionEffectType.POISON, 40,1,false,false,false);
                                                    PotionEffect effect1 = new PotionEffect(PotionEffectType.HUNGER, 200, 1,false,false,false);
                                                    player.addPotionEffect(effect);
                                                    player.addPotionEffect(effect1);
                                                    player.damage(4,skeleton);
                                                    player.getWorld().spawnParticle(Particle.SPELL_WITCH,player.getLocation(),5);
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
                            if(i % 10 == 0)
                            {
                                Random r = new Random();
                                skeleton.getWorld().playSound(skeleton.getLocation(),Sound.ENTITY_EVOKER_PREPARE_SUMMON,5,5);
                                for(int x = 0; x < 3; x++)
                                {
                                    InfectedZombie summoned = new InfectedZombie(main);
                                    summoned.createInfectedZombie(skeleton.getLocation().add(r.nextInt(1 + 1) -1, 0, r.nextInt(1 + 1) -1));
                                    summoned.setTarget(skeleton.getTarget());
                                }
                            }
                        }
                    if(i % 30 == 0)
                    {
                        Bukkit.getLogger().info("Heal");
                        skeleton.setAI(false);
                        new BukkitRunnable()
                        {
                            int j = 0;
                            public void run()
                            {
                                try
                                {
                                    skeleton.setHealth(skeleton.getHealth()+skeleton.getMaxHealth()*0.05);
                                    skeleton.getWorld().spawnParticle(Particle.HEART,skeleton.getLocation().add(0,2,0),3);
                                }
                                catch (java.lang.IllegalArgumentException exep)
                                {
                                }

                                j++;
                                if(j >= 4)
                                    cancel();
                            }
                        }.runTaskTimer(main,0L,20L);
                        skeleton.setAI(true);
                    }

                        i++;
                    }
                    else{
                    skeleton.getWorld().spawnParticle(Particle.ASH,skeleton.getLocation(),10);
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
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("Parasite orb"))
            {
                event.setCancelled(true);
                event.getEntity().remove();
            }
        }
    }
}
