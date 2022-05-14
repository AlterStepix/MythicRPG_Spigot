package alterstepix.mythicrpg.misc;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleSpawner {

    public static void fire_spiral(Location location, int radius, long tickspeed, int iter, Player player)
    {

        final double[] y = {0};

        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run() {
                double x = Math.cos(y[0]) * radius * 0.05;
                double z = Math.sin(y[0]) * radius * 0.05;
                player.spawnParticle(Particle.VILLAGER_HAPPY,location.add(x,y[0]/10,z),30,0,0,0,0);
                y[0] = y[0] + 0.1;

                i++;
                Bukkit.getLogger().info(""+i);

                if(i >= iter)
                    cancel();
            }
        }.runTaskTimerAsynchronously(Mythicrpg.INSTANCE,0L,tickspeed);
    }

}
