package alterstepix.mythicrpg.misc;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.DropTabel;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobDropManager implements Listener {

    FileConfiguration config;
    Mythicrpg main;

    public MobDropManager(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    @EventHandler
    public void onMobDrop(EntityDeathEvent e)
    {
        DropTabel Drops = new DropTabel(main);
        Drops.Init();

        if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("FireSpiritNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < 0.5)
                e.getDrops().add(Drops.amberShard);
        }

    }
}
