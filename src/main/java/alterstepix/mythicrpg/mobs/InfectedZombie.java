package alterstepix.mythicrpg.mobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class InfectedZombie implements Listener {
    public void createInfectedZombie(Location location)
    {
        Zombie infected = location.getWorld().spawn(location, Zombie.class);
        infected.setCustomName("InfectedZombie");
        infected.setCustomNameVisible(true);
        Attributable infectedAt = infected;
        AttributeInstance attributeHP = infectedAt.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attributeHP.setBaseValue(30);
        infected.setHealth(30);
        AttributeInstance attributeSpeed = infectedAt.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attributeSpeed.setBaseValue(0.2);

        infected.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SHOVEL));
        infected.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        infected.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        infected.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));


    }

}
