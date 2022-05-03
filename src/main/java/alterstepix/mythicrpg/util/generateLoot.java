package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.managers.DropTable;
import alterstepix.mythicrpg.managers.ScrollManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class generateLoot {
    FileConfiguration config;
    Mythicrpg main;

    DropTable Drops;
    ScrollManager Scrolls;

    public generateLoot(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();

        Drops = new DropTable(main);
        Drops.init();
        Scrolls = new ScrollManager(main);
        Scrolls.init();

    }

    public List<ItemStack> getLoot(String name)
    {

        List<ItemStack> result= new ArrayList<>();
        name = ChatColor.stripColor(name);
        double CommonChance = config.getInt("CommonChance")*0.01;
        double RareChance = config.getInt("RareChance")*0.01;
        double EpicChance = config.getInt("EpicChance")*0.01;
        double LegendaryChance = config.getInt("LegendaryChance")*0.01;
        double MythicChance = config.getInt("MythicChance")*0.01;
        double DivineChance = config.getInt("DivineChance")*0.01;

        if(name != null && name.contains(config.getString("WitherSpiderNametag").split("!")[1]))
        {
            if(Math.random() < CommonChance)
                result.add(Drops.witheredEye);
        }
        else if(name != null && name.contains(config.getString("ParasiteNametag").split("!")[1]))
        {
            if(Math.random() < RareChance)
                result.add(Drops.parasiteHeart);
            if(Math.random() < 0.5)
                result.add(RandomLootGenerator.getLootArmor(30));
            if(Math.random() < 0.5)
                result.add(RandomLootGenerator.getLootSword(30));
        }
        else if(name != null && name.contains(config.getString("InfectedZombieNametag").split("!")[1]))
        {
            if(Math.random() < CommonChance)
                result.add(Drops.infectedFlesh);
            if(Math.random() < RareChance)
                result.add(Drops.infectedHeart);
        }
        else if(name != null && name.contains(config.getString("MasterAssassinNametag").split("!")[1]))
        {
            if(Math.random() < LegendaryChance)
                result.add(Drops.shadyAura);
        }
        else if(name != null && name.contains(config.getString("AirSpiritNametag").split("!")[1]))
        {
            if(Math.random() < CommonChance)
                result.add(Drops.impulseShard);
        }
        else if(name != null && name.contains(config.getString("FireSpiritNametag").split("!")[1]))
        {
            if(Math.random() < CommonChance)
                result.add(Drops.amberShard);
        }
        else if(name != null && name.contains(config.getString("IceSpiritNametag").split("!")[1]))
        {
            if(Math.random() < CommonChance)
                result.add(Drops.frozenShard);
        }
        else if(name != null && name.contains(config.getString("SemiIdolNametag").split("!")[1]))
        {
            if(Math.random() < EpicChance)
                result.add(Drops.lightningShard);
            if(Math.random() < 0.5)
                result.add(RandomLootGenerator.getLootArmor(40));
            if(Math.random() < 0.5)
                result.add(RandomLootGenerator.getLootSword(40));
        }
        else if(name != null && name.contains(config.getString("AncientZombieNametag").split("!")[1]))
        {
            if(Math.random() < CommonChance)
                result.add(Drops.ancientShard);
            if(Math.random() < RareChance)
                result.add(Drops.decayedHeart);
        }
        else if(name != null && name.contains(config.getString("OverworldInvaderNamtetag").split("!")[1]))
        {
            if(Math.random() < RareChance)
                result.add(Drops.destructiveShard);
            if(Math.random() < 0.5)
                result.add(RandomLootGenerator.getLootArmor(35));
            if(Math.random() < 0.5)
                result.add(RandomLootGenerator.getLootSword(35));
        }
        else if(name != null && name.contains(config.getString("GhostNametag").split("!")[1]))
        {
            if(Math.random() < CommonChance)
                result.add(Drops.ghostEssence);
        }
        else if(name != null && name.contains(config.getString("NetherLordBossNametag").split("!")[1]))
        {
            for(int i = 0; i<3; i++)
                result.add(Drops.netherEssence);
            for(int i = 0; i<5; i++)
                result.add(Drops.witheredShard);
            for(int i = 0; i<2; i++)
                result.add(Scrolls.ArrowStormScroll);
            for(int i = 0; i<2; i++)
                result.add(Scrolls.NetherStormScroll);
            for(int i = 0; i<2; i++)
                result.add(Scrolls.InfernalAuraScroll);
            for(int i = 0; i<2; i++)
                result.add(Scrolls.HealingTotemScroll);
            result.add(Drops.netherCatalyst);

            for(int i = 0; i < 3; i++)
                result.add(RandomLootGenerator.getLootArmor(70));
            for(int i = 0; i < 3; i++)
                result.add(RandomLootGenerator.getLootSword(70));
        }
        else if(name != null && name.contains(config.getString("NetherHealerNametag").split("!")[1]))
        {

        }
        else if(name != null && name.contains(config.getString("CursedEmperorBossNametag").split("!")[1]))
        {
            if(Math.random() < LegendaryChance)
                result.add(Drops.cursedCrown);
            if(Math.random() < LegendaryChance)
                result.add(Drops.cursedHeart);
            for(int i = 0; i < 5; i++)
                result.add(Drops.cursedBone);

            for(int i = 0; i < 3; i++)
                result.add(RandomLootGenerator.getLootArmor(70));
            for(int i = 0; i < 3; i++)
                result.add(RandomLootGenerator.getLootSword(80));
        }
        else if(name != null && name.contains(config.getString("RatNametag").split("!")[1]))
        {

        }
        else if(name != null && name.contains(config.getString("LegionaryNametag").split("!")[1]))
        {

        }
        else if(name != null && name.contains(config.getString("AncientPriestName").split("!")[1]))
        {

        }
        else if(name != null && name.contains(config.getString("PhantomRiderNametag").split("!")[1]))
        {

        }
        else if(name != null && name.contains(config.getString("FrozenSoulNametag").split("!")[1]))
        {

        }
        else if(name != null && name.contains(config.getString("DesertGuardianNametag").split("!")[1]))
        {

        }
        else if(name != null && name.contains(config.getString("RevenantArcherNametag").split("!")[1]))
        {

        }
        else if(name != null && name.contains(config.getString("MushroomMonsterNamatag").split("!")[1]))
        {

        }
        return result;
    }
}
