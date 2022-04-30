package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class WatchingEye {
    Mythicrpg main;
    FileConfiguration conig;

    public WatchingEye(Mythicrpg main)
    {
        this.main = main;
        this.conig = main.getConfig();
    }

    public LivingEntity[] summon(Location location)
    {
        Bat bat = location.getWorld().spawn(location,Bat.class);
        Guardian guardian = location.getWorld().spawn(location,Guardian.class);
        bat.addPassenger(guardian);
        bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,999999,1,false,false,false));
        bat.setInvulnerable(true);

        guardian.setMaxHealth(conig.getInt("WatchingEyeHealth"));
        guardian.setHealth(conig.getInt("WatchingEyeHealth"));

        guardian.setCustomNameVisible(true);
        guardian.setCustomName(ColorUtil.ConvertToCustom(conig.getString("WatchingEyeNametag")) + " §7["+Math.round(guardian.getHealth())+"/"+guardian.getMaxHealth()+"]");
        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                if(guardian.isDead())
                {
                    guardian.remove();
                    bat.remove();
                    cancel();
                }
                guardian.setCustomName(ColorUtil.ConvertToCustom(conig.getString("WatchingEyeNametag")) + " §7["+Math.round(guardian.getHealth())+"/"+guardian.getMaxHealth()+"]");
            }
        }.runTaskTimer(main,0L,20L);

        return new LivingEntity[]{bat,guardian};
    }
}
