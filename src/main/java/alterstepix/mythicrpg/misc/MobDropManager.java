package alterstepix.mythicrpg.misc;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.commands.GetRandomLoot;
import alterstepix.mythicrpg.managers.DropTable;
import alterstepix.mythicrpg.managers.ScrollManager;
import alterstepix.mythicrpg.util.RandomLootGenerator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

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
        double CommonChance = config.getInt("CommonChance")*0.01;
        double RareChance = config.getInt("RareChance")*0.01;
        double EpicChance = config.getInt("EpicChance")*0.01;
        double LegendaryChance = config.getInt("LegendaryChance")*0.01;
        double MythicChance = config.getInt("MythicChance")*0.01;
        double DivineChance = config.getInt("DivineChance")*0.01;

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
            if(Math.random() < 0.5)
                e.getDrops().add(RandomLootGenerator.getLootArmor(30));
            if(Math.random() < 0.5)
                e.getDrops().add(RandomLootGenerator.getLootSword(30));
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
            if(Math.random() < LegendaryChance)
                e.getDrops().add(Drops.shadyAura);
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
            if(Math.random() < 0.5)
                e.getDrops().add(RandomLootGenerator.getLootArmor(40));
            if(Math.random() < 0.5)
                e.getDrops().add(RandomLootGenerator.getLootSword(40));
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("AncientZombieNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < CommonChance)
                e.getDrops().add(Drops.ancientShard);
            if(Math.random() < RareChance)
                e.getDrops().add(Drops.decayedHeart);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("OverworldInvaderNamtetag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < RareChance)
                e.getDrops().add(Drops.destructiveShard);
            if(Math.random() < 0.5)
                e.getDrops().add(RandomLootGenerator.getLootArmor(35));
            if(Math.random() < 0.5)
                e.getDrops().add(RandomLootGenerator.getLootSword(35));
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("GhostNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < CommonChance)
                e.getDrops().add(Drops.ghostEssence);
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("NetherLordBossNametag").split("!")[1]))
        {
            e.getDrops().clear();
            for(int i = 0; i<3; i++)
                e.getDrops().add(Drops.netherEssence);
            for(int i = 0; i<5; i++)
                e.getDrops().add(Drops.witheredShard);
            for(int i = 0; i<2; i++)
                e.getDrops().add(Scrolls.ArrowStormScroll);
            for(int i = 0; i<2; i++)
                e.getDrops().add(Scrolls.NetherStormScroll);
            for(int i = 0; i<2; i++)
                e.getDrops().add(Scrolls.InfernalAuraScroll);
            for(int i = 0; i<2; i++)
                e.getDrops().add(Scrolls.HealingTotemScroll);
            e.getDrops().add(Drops.netherCatalyst);

            for(int i = 0; i < 3; i++)
                e.getDrops().add(RandomLootGenerator.getLootArmor(70));
            for(int i = 0; i < 3; i++)
                e.getDrops().add(RandomLootGenerator.getLootSword(70));
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("NetherHealerNametag").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("CursedEmperorBossNametag").split("!")[1]))
        {
            e.getDrops().clear();
            if(Math.random() < LegendaryChance)
                e.getDrops().add(Drops.cursedCrown);
            if(Math.random() < LegendaryChance)
                e.getDrops().add(Drops.cursedHeart);
            for(int i = 0; i < 5; i++)
                e.getDrops().add(Drops.cursedBone);

            for(int i = 0; i < 3; i++)
                e.getDrops().add(RandomLootGenerator.getLootArmor(70));
            for(int i = 0; i < 3; i++)
                e.getDrops().add(RandomLootGenerator.getLootSword(80));
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("RatNametag").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("LegionaryNametag").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("AncientPriestName").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("PhantomRiderNametag").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("FrozenSoulNametag").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("DesertGuardianNametag").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("RevenantArcherNametag").split("!")[1]))
        {
            e.getDrops().clear();
        }
        else if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().contains(config.getString("MushroomMonsterNamatag").split("!")[1]))
        {
            e.getDrops().clear();
        }
    }
}
