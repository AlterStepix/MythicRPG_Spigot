package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;


public class InfectedZombie implements Listener {

    Mythicrpg main;
    FileConfiguration config;

    public InfectedZombie(Mythicrpg main)
    {
        this.main = main;
        config = main.getConfig();
    }

    public void createInfectedZombie(Location location)
    {
        int hp = config.getInt("InfectedZombieHealth");
        Zombie infected = location.getWorld().spawn(location, Zombie.class);
        infected.setCustomName(config.getString("InfectedZombieNametag"));
        infected.setCustomNameVisible(true);
        Attributable infectedAt = infected;
        AttributeInstance attributeHP = infectedAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHP.setBaseValue(hp);
        infected.setHealth(hp);
        AttributeInstance attributeSpeed = infectedAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(0.2);

        infected.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SHOVEL));
        infected.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        infected.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        infected.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));

        infected.setCustomName(config.getString("InfectedZombieNametag") + " §7["+Math.round(infected.getHealth())+"/"+infected.getMaxHealth()+"]");
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event)
    {
        if(event.getDamager().getCustomName() != null)
        {
            if(event.getEntity() instanceof LivingEntity) {
                LivingEntity trg = (LivingEntity) event.getEntity();
                if(event.getDamager().getCustomName().startsWith(config.getString("InfectedZombieNametag")))
                {
                        LivingEntity zombie = (LivingEntity) event.getDamager();
                        trg.damage(2);

                        if (zombie.getMaxHealth() - zombie.getHealth() > 4)
                        {
                            zombie.setHealth(zombie.getHealth() + 4);
                        }
                        zombie.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, zombie.getEyeLocation().getX(),zombie.getEyeLocation().getY()+1, zombie.getEyeLocation().getZ(),3);
                        zombie.getWorld().playSound(zombie.getLocation(),Sound.ENTITY_WITCH_DRINK,5,5);

                    }
                }
        }
    }

}
