package alterstepix.mythicrpg.misc;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.DropTable;
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
        double CommonChance = config.getInt("CommonChance")*0.01;
        double RareChance = config.getInt("RareChance")*0.01;
        double EpicChance = config.getInt("EpicChance")*0.01;

        DropTable Drops = new DropTable(main);
        Drops.init();

        if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("WitherSpiderNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < CommonChance)
                e.getDrops().add(Drops.witheredEye);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("ParasiteNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < RareChance)
                e.getDrops().add(Drops.parasiteHeart);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("InfectedZombieNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < CommonChance)
                e.getDrops().add(Drops.infectedFlesh);
            if(Math.random() < RareChance)
                e.getDrops().add(Drops.infectedHeart);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("MasterAssassinNametag").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("AirSpiritNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < CommonChance)
                e.getDrops().add(Drops.impulseShard);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("FireSpiritNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < CommonChance)
                e.getDrops().add(Drops.amberShard);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("IceSpiritNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < CommonChance)
                e.getDrops().add(Drops.frozenShard);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("SemiIdolNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < EpicChance)
                e.getDrops().add(Drops.lightningShard);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("AncientZombieNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < CommonChance)
                e.getDrops().add(Drops.ancientShard);
            if(Math.random() < RareChance)
                e.getDrops().add(Drops.decayedHeart);
        }

    }
}
