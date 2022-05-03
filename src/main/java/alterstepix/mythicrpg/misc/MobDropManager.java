package alterstepix.mythicrpg.misc;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.commands.GetRandomLoot;
import alterstepix.mythicrpg.managers.DropTable;
import alterstepix.mythicrpg.managers.ScrollManager;
import alterstepix.mythicrpg.util.RandomLootGenerator;
import alterstepix.mythicrpg.util.generateLoot;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class MobDropManager implements Listener {

    FileConfiguration config;
    Mythicrpg main;

    DropTable Drops;
    ScrollManager Scrolls;

    public MobDropManager(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();

        Drops = new DropTable(main);
        Drops.init();
        Scrolls = new ScrollManager(main);
        Scrolls.init();

    }

    @EventHandler
    public void onMobDrop(EntityDeathEvent e)
    {
        if(e.getEntity().getCustomName() != null)
        {
            generateLoot loot = new generateLoot(main);
            e.getDrops().clear();
            e.getDrops().addAll(loot.getLoot(e.getEntity().getCustomName()));
        }
    }
}
