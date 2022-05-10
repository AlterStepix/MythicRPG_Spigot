package alterstepix.mythicrpg.mobsystem.abilityhandlers;

import alterstepix.mythicrpg.mobsystem.MobRegistry;
import alterstepix.mythicrpg.mobsystem.abilities.Vampire;
import alterstepix.mythicrpg.mobsystem.classes.CustomMob;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DynamicAbilityHandler implements Listener {

    @EventHandler
    public void onEntityHitEvent(EntityDamageByEntityEvent event)
    {
        if(event.getDamager() instanceof LivingEntity le_damager)
        {
            if(MobRegistry.mobs.containsKey(le_damager))
            {
                CustomMob c_damager = MobRegistry.mobs.get(le_damager);

                // Vampire

                if(c_damager instanceof Vampire v_damager)
                {
                    if (event.getEntity() instanceof LivingEntity target)
                    {
                        if (Math.random() < v_damager.VAMPIRE_INFO().Chance / 100)
                        {
                            if (target.getMaxHealth() - target.getHealth() > v_damager.VAMPIRE_INFO().HealAmount)
                            {
                                target.setHealth(target.getHealth() + v_damager.VAMPIRE_INFO().HealAmount);
                            }
                            else
                            {
                                target.setHealth(target.getMaxHealth());
                            }

                            le_damager.getWorld().playSound(le_damager.getLocation(),v_damager.VAMPIRE_INFO().Sound,v_damager.VAMPIRE_INFO().SoundVolume,v_damager.VAMPIRE_INFO().SoundPitch);
                            le_damager.getWorld().spawnParticle(v_damager.VAMPIRE_INFO().Particle, le_damager.getLocation().add(v_damager.VAMPIRE_INFO().ParticleOffset),v_damager.VAMPIRE_INFO().ParticleAmount,0,0,0);

                        }
                    }

                }

            }
        }
    }
}



