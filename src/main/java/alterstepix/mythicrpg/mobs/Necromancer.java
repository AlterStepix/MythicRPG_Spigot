package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class Necromancer implements Listener {
    static Mythicrpg main;
    FileConfiguration config;

    public Necromancer(Mythicrpg main)
    {
        this.main = main;
        config = main.getConfig();
    }

    public void createNecromancer(Location location){
        int hp = config.getInt("NecromancerHealth");
        WitherSkeleton skeleton = location.getWorld().spawn(location, WitherSkeleton.class);

        skeleton.setCustomName(config.getString("NecromancerNametag"));
        skeleton.setCustomNameVisible(true);
        Attributable skeletonAt = skeleton;
        AttributeInstance attributeHP = skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHP.setBaseValue(hp);
        skeleton.setHealth(hp);
        AttributeInstance attributeSpeed = skeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(0.1);

        skeleton.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SHOVEL));
        skeleton.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
        skeleton.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
        skeleton.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
        skeleton.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
        skeleton.setCustomName(config.getString("NecromancerNametag") + " §7["+Math.round(skeleton.getHealth())+"/"+skeleton.getMaxHealth()+"]");
        new BukkitRunnable(){
            int i = 0;
            public void run()
            {
                if(!skeleton.isDead())
                {
                    if(skeleton.getTarget() != null){
                        if(i % 2 == 0)
                        {
                            FallingBlock fallingBlock = skeleton.getWorld().spawnFallingBlock(skeleton.getLocation().add(0,2,0),Material.SNOW_BLOCK, (byte) 0);
                            fallingBlock.setCustomName("Necromancer orb");
                            fallingBlock.setDropItem(false);
                            fallingBlock.setVelocity(skeleton.getTarget().getLocation().add(0,1,0).subtract(fallingBlock.getLocation()).toVector().multiply(0.5));
                            fallingBlock.getWorld().playSound(fallingBlock.getLocation(), Sound.ENTITY_WITHER_SHOOT,5,5);
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
                                                    PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, 20,1,false,false,false);
                                                    player.addPotionEffect(effect);
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
                        if(i % 10 == 0){
                            Random r = new Random();
                            skeleton.getWorld().playSound(skeleton.getLocation(),Sound.ENTITY_EVOKER_PREPARE_SUMMON,5,5);
                            for(int x = 0; x < 4; x++)
                            {
                                Skeleton summonedskeleton = skeleton.getWorld().spawn(skeleton.getLocation().add(r.nextInt(1 + 1) -1, 0, r.nextInt(1 + 1) -1),Skeleton.class);
                                summonedskeleton.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
                                summonedskeleton.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
                                summonedskeleton.setTarget(skeleton.getTarget());
                                skeleton.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME,summonedskeleton.getLocation(),1);
                            }
                        }
                        i++;
                    }
                }
                else {
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
            if(event.getEntity().getCustomName() != null && event.getEntity().getCustomName().equals("Necromancer orb"))
            {
                event.setCancelled(true);
                event.getEntity().remove();
            }
        }
    }
}
