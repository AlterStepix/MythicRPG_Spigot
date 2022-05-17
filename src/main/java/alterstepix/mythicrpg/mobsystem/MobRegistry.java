/*
Copyright 2022 AlterStepix

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.mobsystem;

import alterstepix.mythicrpg.mobsystem.classes.CustomMob;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;

public class MobRegistry implements Listener {
    public static HashMap<LivingEntity, CustomMob> mobs = new HashMap<>();

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event)
    {
        if(mobs.containsKey(event.getEntity()))
        {
            mobs.remove(event.getEntity());
        }
    }
}
