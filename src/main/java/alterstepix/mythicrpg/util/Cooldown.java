/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

// Taken from Epic Items

public class Cooldown {
    public HashMap<UUID, Double> cd;

    public Cooldown() {}

    public void init() {
        cd = new HashMap<UUID, Double>();
    }

    public boolean checkCD(Player player) {
        return !cd.containsKey(player.getUniqueId()) || cd.get(player.getUniqueId()) <= (double)System.currentTimeMillis();
    }

    public int getCooldownTime(Player player)
    {
        if(cd.containsKey(player.getUniqueId()))
        {
            double delay = cd.get(player.getUniqueId());
            return (int)((delay - System.currentTimeMillis()) / 1000);
        }
        else
            return 0;
    }

    public void putCooldown(Player player, int seconds) {
        double delay = (double)(System.currentTimeMillis() + (long)(seconds * 1000));
        cd.put(player.getUniqueId(), delay);
    }


}