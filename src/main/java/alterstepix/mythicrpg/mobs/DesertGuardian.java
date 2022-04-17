package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DesertGuardian {
    FileConfiguration config;
    Mythicrpg main;
    public DesertGuardian(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createDesertGuardian(Location location)
    {
        Husk guardian = location.getWorld().spawn(location,Husk.class);

        AttributeInstance Health = guardian.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        AttributeInstance Def = guardian.getAttribute(Attribute.GENERIC_ARMOR);

        Health.setBaseValue(config.getInt("DesertGuardianHealth"));
        Def.setBaseValue(40);
        guardian.setHealth(config.getInt("DesertGuardianHealth"));

        guardian.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));

        guardian.setCustomNameVisible(true);
        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                if(!guardian.isDead()) {
                    guardian.setCustomName(ColorUtil.ConvertToCustom(config.getString("DesertGuardianNametag")) + " §7["+Math.round(guardian.getHealth())+"/"+guardian.getMaxHealth()+"]");
                    if (i % 5 == 0)
                    {
                        for(Entity e : guardian.getNearbyEntities(7,7,7))
                        {
                            if(e instanceof LivingEntity trg)
                            {
                                trg.addPotionEffect(new PotionEffect(PotionEffectType.POISON,60,2,false,false,false));
                            }
                        }
                    }
                    i++;
                }
                else
                {
                    guardian.remove();
                    cancel();
                }
            }
        }.runTaskTimer(main,0L,20L);
    }
}
