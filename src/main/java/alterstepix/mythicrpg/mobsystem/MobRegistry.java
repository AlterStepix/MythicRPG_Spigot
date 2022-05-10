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
