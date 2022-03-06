package alterstepix.mythicrpg.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

// taken from Epic Items

public class Cooldown {
    public static HashMap<UUID, Double> cd;

    public Cooldown() {
    }

    public static void init() {
        cd = new HashMap<UUID, Double>();
    }

    public static boolean checkCooldown(Player player) {
        return !cd.containsKey(player.getUniqueId()) || cd.get(player.getUniqueId()) <= (double)System.currentTimeMillis();
    }

    public static void setCooldown(Player player, int seconds) {
        double delay = (double)(System.currentTimeMillis() + (long)(seconds * 1000));
        cd.put(player.getUniqueId(), delay);
    }


}