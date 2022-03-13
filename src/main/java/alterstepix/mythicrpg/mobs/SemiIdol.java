package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.IronGolem;
import org.bukkit.scheduler.BukkitRunnable;

public class SemiIdol {
    Mythicrpg main;
    FileConfiguration config;

    public SemiIdol(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void createSemiIdol(Location location)
    {
        IronGolem golem = location.getWorld().spawn(location,IronGolem.class);

        new BukkitRunnable()
        {
            public void run()
            {

            }
        }.runTaskTimer(main,0L,20L);
    }
}
