package alterstepix.mythicrpg.mobsystem.classes;


import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.mobsystem.MobRegistry;
import alterstepix.mythicrpg.mobsystem.abilities.BlockThrower;
import alterstepix.mythicrpg.mobsystem.info.CustomMobInfo;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomMob {

    public abstract CustomMobInfo info();

    public LivingEntity create(Location location)
    {
        Mob mob = (Mob) location.getWorld().spawnEntity(location,info().Type);

        MobRegistry.mobs.put(mob,this);

        if(info().Helmet != null) {mob.getEquipment().setHelmet(info().Helmet);}
        if(info().Chestplate != null) {mob.getEquipment().setChestplate(info().Chestplate);}
        if(info().Leggings != null) {mob.getEquipment().setLeggings(info().Leggings);}
        if(info().Boots != null) {mob.getEquipment().setBoots(info().Boots);}
        if(info().Mainhand != null) {mob.getEquipment().setItemInMainHand(info().Mainhand);}

        if(info().Effects != null)
        {
            for(PotionEffect effect : info().Effects)
            {
                mob.addPotionEffect(effect);
            }
        }

        AttributeInstance mobHealth = mob.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        mobHealth.setBaseValue(info().MaxHealth);
        mob.setHealth(info().MaxHealth);
        AttributeInstance mobArmor = mob.getAttribute(Attribute.GENERIC_ARMOR);
        mobArmor.setBaseValue(info().Defence);
        AttributeInstance mobSpeed = mob.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        mobSpeed.setBaseValue(info().Speed);

        mob.setCustomNameVisible(true);
        new BukkitRunnable()
        {
            @Override
            public void run() {
                mob.setCustomName(ChatColor.of(info().NametagColor)+info().Nametag+" §c"+(int)mob.getHealth()+"♥/"+(int)mobHealth.getBaseValue()+"♥");
            }
        }.runTaskTimer(Mythicrpg.INSTANCE,0L,20L);

        if(this instanceof BlockThrower thrower)
        {
            new BukkitRunnable()
            {
                int i = 0;
                @Override
                public void run() {

                    if(i % thrower.BLOCK_THROWER_INFO().cooldown == thrower.BLOCK_THROWER_INFO().starting)
                    {
                        FallingBlock fallingBlock = mob.getWorld().spawnFallingBlock(mob.getLocation().add(0,2,0), thrower.BLOCK_THROWER_INFO().material, (byte) 0);
                        fallingBlock.setCustomName("##!never_place");
                        fallingBlock.setDropItem(false);
                        fallingBlock.setVelocity(mob.getTarget().getLocation().add(0,1,0).subtract(fallingBlock.getLocation()).toVector().multiply(0.5));

                        fallingBlock.getWorld().playSound(fallingBlock.getLocation(),thrower.BLOCK_THROWER_INFO().Sound,thrower.BLOCK_THROWER_INFO().SoundVolume,thrower.BLOCK_THROWER_INFO().SoundPitch);
                        fallingBlock.getWorld().spawnParticle(thrower.BLOCK_THROWER_INFO().Particle, fallingBlock.getLocation().add(thrower.BLOCK_THROWER_INFO().ParticleOffset),thrower.BLOCK_THROWER_INFO().ParticleAmount,0,0,0);

                        //

                        new BukkitRunnable(){
                            public void run()
                            {
                                if(!fallingBlock.isDead())
                                {
                                    fallingBlock.getWorld().spawnParticle(thrower.BLOCK_THROWER_INFO().Particle, fallingBlock.getLocation().add(thrower.BLOCK_THROWER_INFO().ParticleOffset),1,0,0,0);
                                    for(Entity entity : fallingBlock.getNearbyEntities(2,2,2))
                                    {
                                        if(entity instanceof Player){
                                            if(fallingBlock.getLocation().distanceSquared(entity.getLocation()) < 1)
                                            {
                                                Player player = (Player) entity;
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
                        }.runTaskTimer(Mythicrpg.INSTANCE,0L,2L);

                        //

                    }

                    i++;
                }
            }.runTaskTimer(Mythicrpg.INSTANCE,0L,20L);
        }

        return mob;

    }

}
