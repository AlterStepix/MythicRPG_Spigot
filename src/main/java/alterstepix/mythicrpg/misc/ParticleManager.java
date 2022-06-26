
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


    public static void helix(Location location, float r, Particle p, Location rotatedTo, boolean force)
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

                loc.getWorld().spawnParticle(p,loc,5,0.05,0.05,0.05,0,null,force);

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


    public static void circle_instant(Location location, float r, Particle particle, Location rotatedTo, boolean force)
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

            loc.getWorld().spawnParticle(particle, loc, 1, 0, 0, 0, 0,null,force);

            loc = loc.subtract(v);

            if (t > Math.PI * 4)
                break;
        }
    }
    public static void wave(Location location, Vector dir, Particle particle)
    {
        double t = 0;
        Location loc = location.clone();

        while (true)
        {
            t = t+Math.PI / 16;

            double y = 0.2 * ( Math.sin(2*t) / (0.2*t) );

            loc = loc.add(new Vector(0,y,0));
            loc.getWorld().spawnParticle(particle, loc,1,0,0,0,0);
            loc = loc.subtract(new Vector(0,y,0));

            loc = loc.add(dir);

            if(t > Math.PI*4)
                return;

        }
    }

    public static void circle2(Location location, Particle particle, double radius, boolean force)
    {
        double t = 0;
        Location loc = location.clone();

        while (true)
        {
            t = t + Math.PI / 8;
            double x = radius * Math.cos(t);
            double y = 0;
            double z = radius * Math.sin(t);

            loc = loc.add(x,y,z);

            loc.getWorld().spawnParticle(particle, loc, 1, 0, 0, 0, 0,null,force);

            loc = loc.subtract(x,y,z);

            if (t > Math.PI * 4)
                break;
        }
    }

    public static void d2_vortex(Location location, Particle particle, double radius, boolean force)
    {
        double t = 0;
        Location loc = location.clone();

        while (true)
        {
            t = t + Math.PI / 8;
            double x = radius * Math.cos(t) / t;
            double y = 0;
            double z = radius * Math.sin(t) / t;

            loc = loc.add(x,y,z);

            loc.getWorld().spawnParticle(particle, loc, 1, 0, 0, 0, 0,null,force);

            loc = loc.subtract(x,y,z);

            if (t > Math.PI * 4)
                break;
        }
    }

    public static void d2_vortex(Location location, Particle.DustTransition particle, double radius, boolean force)
    {
        double t = 0;
        Location loc = location.clone();

        while (true)
        {
            t = t + Math.PI / 8;
            double x = radius * Math.cos(t) / t;
            double y = 0;
            double z = radius * Math.sin(t) / t;

            loc = loc.add(x,y,z);

            loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, 1, 0, 0, 0, 0,particle,force);

            loc = loc.subtract(x,y,z);

            if (t > Math.PI * 4)
                break;
        }
    }

    public static void d2_vortex_ni(Location location, Particle.DustTransition particle, double radius, boolean force)
    {

        new BukkitRunnable()
        {
            double t = 0;
            double v = 3;
            Location loc = location.clone();

            public void run()
            {
                t = t + Math.PI / 8;
                v = v + 0.1;
                double x = 3 * radius * Math.cos(t) / v;
                double y = 0.1;
                double z = 3 * radius * Math.sin(t) / v;

                loc = loc.add(x,y,z);

                loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, 1, 0, 0, 0, 0,particle,force);

                loc = loc.subtract(x,y,z);

                if (t > Math.PI * 8)
                    cancel();
            }

        }.runTaskTimer(Mythicrpg.INSTANCE,0L,1L);
    }

    public static void strange_3D_shape(Location location,Particle particle, float radius, boolean force)
    {
        double t = 0;

        while (true)
        {
            circle2(location.add(0,t/(Math.PI*Math.PI*Math.PI),0),particle, (Math.sin(t/Math.PI)*radius),force);
            t = t + Math.PI / 8;

            if(t > Math.PI * Math.PI)
                return;
        }
    }

}
