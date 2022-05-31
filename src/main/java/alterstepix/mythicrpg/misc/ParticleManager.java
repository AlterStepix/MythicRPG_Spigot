
/*
    Credits to SteeZyyy for most content in this file
*/

package alterstepix.mythicrpg.misc;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.TreeSpecies;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ParticleManager {

    private static Vector rotate(Vector v, Location loc)
    {
        double yawRadians = loc.getYaw()/180.0*Math.PI;
        double pitchRadians = loc.getPitch()/180.0*Math.PI;

        return v.rotateAroundX(pitchRadians).rotateAroundY(-yawRadians);
    }


    public static void helix(Location location, int r, Particle p, Location rotatedTo)
    {
        new BukkitRunnable(){
            double t = 0;
            Location loc = location.clone();

            public void run()
            {
                t = t + Math.PI/8;
                double x = r*Math.cos(t);
                double y = t;
                double z = r*Math.sin(t);

                Vector v = new Vector(x,y,z);
                v = rotate(v,rotatedTo);

                loc = loc.add(v);

                loc.getWorld().spawnParticle(p,loc,5,0.05,0.05,0.05,0);

                loc = loc.subtract(v);

                if(t > Math.PI*4)
                    cancel();


            }
        }.runTaskTimer(Mythicrpg.INSTANCE,0L,1L);
    }
    public static void circle(Location location, int r, Particle p, Location rotatedTo)
    {
        new BukkitRunnable(){
            double t = 0;
            Location loc = location.clone();

            public void run() {
                t = t + Math.PI / 8;
                double x = r * Math.cos(t);
                double y = r * Math.sin(t);
                double z = 0;

                Vector v = new Vector(x,y,z);
                v = rotate(v,rotatedTo);

                loc = loc.add(v);

                loc.getWorld().spawnParticle(p, loc, 5, 0.05, 0.05, 0.05, 0);

                loc = loc.subtract(v);

                if (t > Math.PI * 4)
                    cancel();
            }
        }.runTaskTimer(Mythicrpg.INSTANCE,0L,1L);
    }

    public static void circle_dust(Location location, int r, Particle.DustTransition tr, Location rotatedTo,boolean force)
    {
        new BukkitRunnable(){
            double t = 0;
            Location loc = location.clone();

            public void run() {
                t = t + Math.PI / 8;
                double x = r * Math.cos(t);
                double y = r * Math.sin(t);
                double z = 0;

                Vector v = new Vector(x,y,z);
                v = rotate(v,rotatedTo);

                loc = loc.add(v);

                loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, 5, 0.05, 0.05, 0.05, 0,tr,force);

                loc = loc.subtract(v);

                if (t > Math.PI * 4)
                    cancel();
            }
        }.runTaskTimer(Mythicrpg.INSTANCE,0L,1L);
    }

    public static void circle_dust_instant(Location location, float r, Particle.DustTransition tr, Location rotatedTo, boolean force)
    {
        double t = 0;
        Location loc = location.clone();

        while (true)
        {
            t = t + Math.PI / 8;
            double x = r * Math.cos(t);
            double y = r * Math.sin(t);
            double z = 0;

            Vector v = new Vector(x,y,z);
            v = rotate(v,rotatedTo);

            loc = loc.add(v);

            loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, 1, 0, 0, 0, 0,tr,force);

            loc = loc.subtract(v);

            if (t > Math.PI * 4)
                break;
        }
    }

}
