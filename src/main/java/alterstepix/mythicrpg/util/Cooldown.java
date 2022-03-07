package alterstepix.mythicrpg.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Cooldown {
    public static HashMap<UUID, Double> cd;

    public Cooldown() {}

    public static void init() {
        cd = new HashMap<UUID, Double>();
    }

    public static boolean checkCD(Player player) {
        return !cd.containsKey(player.getUniqueId()) || cd.get(player.getUniqueId()) <= (double)System.currentTimeMillis();
    }

    public static int getCooldownTime(Player player)
    {
        if(cd.containsKey(player.getUniqueId()))
        {
            double delay = cd.get(player.getUniqueId());
            return (int)((delay - System.currentTimeMillis()) / 1000);
        }
        else
            return 0;
    }

    public static void putCooldown(Player player, int seconds) {
        double delay = (double)(System.currentTimeMillis() + (long)(seconds * 1000));
        cd.put(player.getUniqueId(), delay);
    }


}