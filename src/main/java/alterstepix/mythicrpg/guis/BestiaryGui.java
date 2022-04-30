package alterstepix.mythicrpg.guis;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.BestiaryPageBuilder;
import alterstepix.mythicrpg.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;


public class BestiaryGui implements Listener {

    FileConfiguration config;

    public BestiaryGui(Mythicrpg main)
    {
        this.config = main.getConfig();
    }

    private Inventory gui;

    public void createGui(Player player)
    {
        gui = Bukkit.createInventory(null, InventoryType.CHEST);

        BestiaryPageBuilder builder = new BestiaryPageBuilder();


        // Wither Spider
        ArrayList<String> WitherSpiderDesc = new ArrayList<>();
        WitherSpiderDesc.add("§7This is a special type of spider summoned by a "+config.getString("NetherLordBossNametag").split("!")[1]+".");
        WitherSpiderDesc.add("§7This spider is very agile and can jump on you. When it hits you it withers you.");
        WitherSpiderDesc.add("§7Also it throws a lot of web on death, making it a very dangerous when there is a lot of them.");
        WitherSpiderDesc.add("§7Next time you will fight "+config.getString("NetherLordBossNametag").split("!")[1]+" "+"you should take a crowd control weapon.");
        ArrayList<String> WitherSpiderDrops = new ArrayList<>();
        WitherSpiderDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("WitheredEyeName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack WitherSpiderPage = builder.create(config.getString("WitherSpiderNametag"),3,config.getInt("WitherSpiderHealth"),WitherSpiderDesc,WitherSpiderDrops);

        //Parasite
        ArrayList<String> ParasiteDesc = new ArrayList<>();
        ParasiteDesc.add("§7This is a strange type of zombie that can spawn "+(config.getString("InfectedZombieNametag").split("!"))[1]+"s.");
        ParasiteDesc.add("§7It also throws its mysterious orbs that cause the opponent to suffer.");
        ArrayList<String> ParasiteDrops = new ArrayList<>();
        ParasiteDrops.add(ColorUtil.ConvertToCustom(config.getString("RareRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("ParasiteHeartName"))+" with "+config.getInt("RareChance")+"%"+" chance");
        ParasiteDrops.add("§7Other:");
        ParasiteDrops.add("§730-th level armor with 50% chance");
        ParasiteDrops.add("§730-th level sword with 50% chance");
        ItemStack ParasitePage = builder.create(config.getString("ParasiteNametag"),10,config.getInt("ParasiteHealth"),ParasiteDesc,ParasiteDrops);

        // Infected Zombie
        ArrayList<String> InfectedZombieDesc = new ArrayList<>();
        InfectedZombieDesc.add("§7This is a infected type of zombie that can be summoned by "+config.getString("ParasiteNametag").split("!")[1]+".");
        InfectedZombieDesc.add("§7This zombie can drain your health in exchange for its health, so you should be careful.");
        ArrayList<String> InfectedZombieDrops = new ArrayList<>();
        InfectedZombieDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("InfectedFleshName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        InfectedZombieDrops.add(ColorUtil.ConvertToCustom(config.getString("RareRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("InfectedHeartName"))+" with "+config.getInt("RareChance")+"%"+" chance");
        ItemStack InfectedZombiePage = builder.create(config.getString("InfectedZombieNametag"),2,config.getInt("InfectedZombieHealth"),InfectedZombieDesc,InfectedZombieDrops);

        // Master Assassin
        ArrayList<String> MasterAssassinDesc = new ArrayList<>();
        MasterAssassinDesc.add("§7This is powerful undead that can teleport behind your back.");
        MasterAssassinDesc.add("§7If you will try to stay near the wall this dangerous assassin can push from from it.");
        ArrayList<String> MasterAssassinDrops = new ArrayList<>();
        MasterAssassinDrops.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("ShadyAura"))+" with "+config.getInt("LegendaryChance")+"%"+" chance");
        ItemStack MasterAssassinPage = builder.create(config.getString("MasterAssassinNametag"),4,config.getInt("MasterAssassinHealth"),MasterAssassinDesc,MasterAssassinDrops);

        // Air Spirit
        ArrayList<String> AirSpiritDesc = new ArrayList<>();
        AirSpiritDesc.add("§7This is a Spirit that represents air and can be summoned by "+config.getString("SemiIdolNametag").split("!")[1]+".");
        AirSpiritDesc.add("§7It can pull you towards it using the force of air.");
        ArrayList<String> AirSpiritDrops = new ArrayList<>();
        AirSpiritDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("ImpulseShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack AirSpiritPage = builder.create(config.getString("AirSpiritNametag"),5,config.getInt("AirSpiritHealth"),AirSpiritDesc,AirSpiritDrops);

        // Fire Spirit
        ArrayList<String> FireSpiritDesc = new ArrayList<>();
        FireSpiritDesc.add("§7This is a Spirit that represents fire and can be summoned by "+config.getString("SemiIdolNametag").split("!")[1]+".");
        FireSpiritDesc.add("§7Using the power of fire it can ignite its opponents.");
        ArrayList<String> FireSpiritDrops = new ArrayList<>();
        FireSpiritDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("AmberShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack FireSpiritPage = builder.create(config.getString("FireSpiritNametag"),5,config.getInt("FireSpiritHealth"),FireSpiritDesc,FireSpiritDrops);

        // Ice Spirit
        ArrayList<String> IceSpiritDesc = new ArrayList<>();
        IceSpiritDesc.add("§7This is a Spirit that represents water and can be summoned by "+config.getString("SemiIdolNametag").split("!")[1]+".");
        IceSpiritDesc.add("§7It gives the opponent a weakness and a slow.");
        ArrayList<String> IceSpiritDrops = new ArrayList<>();
        IceSpiritDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("FrozenShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack IceSpiritPage = builder.create(config.getString("IceSpiritNametag"),5,config.getInt("IceSpiritHealth"),IceSpiritDesc,IceSpiritDrops);

        //Semi Idol
        ArrayList<String> SemiIdolDesc = new ArrayList<>();
        SemiIdolDesc.add("§7This is a very powerful mob that summons: ");
        SemiIdolDesc.add("§7"+(config.getString("AirSpiritNametag").split("!"))[1]+"s, "+(config.getString("FireSpiritNametag").split("!"))[1]+"s and "+(config.getString("IceSpiritNametag").split("!"))[1]+"s.");
        SemiIdolDesc.add("§7Using the powers from above it can strike the player with lightnings.");
        SemiIdolDesc.add("§7The battle with it takes a lot of time and effort, with Spirits being the biggest threat.");
        ArrayList<String> SemiIdolDrops = new ArrayList<>();
        SemiIdolDrops.add(ColorUtil.ConvertToCustom(config.getString("EpicRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("LightningShardName"))+" with "+config.getInt("EpicChance")+"%"+" chance");
        SemiIdolDrops.add("§7Other:");
        SemiIdolDrops.add("§740-th level armor with 50% chance");
        SemiIdolDrops.add("§740-th level sword with 50% chance");
        ItemStack SemiIdolPage = builder.create(config.getString("SemiIdolNametag"),12,config.getInt("SemiIdolHealth"),SemiIdolDesc,SemiIdolDrops);

        // Ancient Zombie
        ArrayList<String> AncientZombieDesc = new ArrayList<>();
        AncientZombieDesc.add("§7This type of zombie was walking in world long before players.");
        AncientZombieDesc.add("§7It will apply strong weakness to the opponent on hit, but it is harmless without debuffs");
        ArrayList<String> AncientZombieDrops = new ArrayList<>();
        AncientZombieDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("AncientShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        AncientZombieDrops.add(ColorUtil.ConvertToCustom(config.getString("RareRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("DecayedHeartName"))+" with "+config.getInt("RareChance")+"%"+" chance");
        ItemStack AncientZombiePage = builder.create(config.getString("AncientZombieNametag"),3,config.getInt("AncientZombieHealth"),AncientZombieDesc,AncientZombieDrops);

        //Overworld Invader
        ArrayList<String> OverworldInvaderDesc = new ArrayList<>();
        OverworldInvaderDesc.add("§7It is a warlike nether creature, that tries to conquer the overworld.");
        OverworldInvaderDesc.add("§7It can pull the opponent towards it and shoot fireballs to cause destruction.");
        ArrayList<String> OverworldInvaderDrops = new ArrayList<>();
        OverworldInvaderDrops.add(ColorUtil.ConvertToCustom(config.getString("RareRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("DestructiveShard"))+" with "+config.getInt("RareChance")+"%"+" chance");
        OverworldInvaderDrops.add("§7Other:");
        OverworldInvaderDrops.add("§735-th level armor with 50% chance");
        OverworldInvaderDrops.add("§735-th level sword with 50% chance");
        ItemStack OverworldInvaderPage = builder.create(config.getString("OverworldInvaderNamtetag"),9,config.getInt("AncientZombieHealth"),OverworldInvaderDesc,OverworldInvaderDrops);

        // Ghost
        ArrayList<String> GhostDesc = new ArrayList<>();
        GhostDesc.add("§7This is a strange creature. No one can tell how it looks, ");
        GhostDesc.add("§7because it applies strong blindness and slowness.");
        GhostDesc.add("§7And when there is a lot of them they can kill the opponent with fear.");
        ArrayList<String> GhostDrops = new ArrayList<>();
        GhostDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("GhostEssence"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack GhostPage = builder.create(config.getString("GhostNametag"),4,config.getInt("GhostHealth"),GhostDesc,GhostDrops);

        //Witherus Netherlord
        ArrayList<String> WitherusNetherlordDesc = new ArrayList<>();
        WitherusNetherlordDesc.add("§7This is a very dangerous and powerful nether creature.");
        WitherusNetherlordDesc.add("§7The battle with it consists of 3 phases with rapidly increasing difficulty.");
        WitherusNetherlordDesc.add("§7In order to kill this boss you need to do hard teamwork.");
        ArrayList<String> WitherusNetherlordDrops = new ArrayList<>();
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("NetherEssenceName"))+" in the amount of three pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("WitheredShardName"))+" in the amount of fife pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("ArrowStormName"))+" in the amount of two pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("NetherStormScroll"))+" in the amount of two pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("InfernalAuraScroll"))+" in the amount of two pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("HealingTotemScroll"))+" in the amount of two pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("NetherCatalystName"))+" x1");
        WitherusNetherlordDrops.add("§7Other:");
        WitherusNetherlordDrops.add("§770-th level armor in the amount of three pieces");
        WitherusNetherlordDrops.add("§770-th level sword in the amount of three pieces");
        ItemStack WitherusNetherlordPage = builder.create(config.getString("NetherLordBossNametag"),25,config.getInt("NetherLordBossHealth"),WitherusNetherlordDesc,WitherusNetherlordDrops);

        //Cursed Emperor
        ArrayList<String> CursedEmperorDesc = new ArrayList<>();
        CursedEmperorDesc.add("§7Legend has it that he was once the emperor of a great empire,");
        CursedEmperorDesc.add("§7but being a dictator and tyrant, now he cursed forever.");
        ArrayList<String> CursedEmperorDrops = new ArrayList<>();
        CursedEmperorDrops.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("CursedCrownName"))+" with "+config.getInt("LegendaryChance")+"%"+" chance");
        CursedEmperorDrops.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("CursedHeartName"))+" with "+config.getInt("LegendaryChance")+"%"+" chance");
        CursedEmperorDrops.add(ColorUtil.ConvertToCustom(config.getString("CursedBoneName"))+" in the amount of fife pieces");
        ItemStack CursedEmperorPage = builder.create(config.getString("CursedEmperorBossNametag"),20,config.getInt("CursedEmperorBossHealth"),CursedEmperorDesc,CursedEmperorDrops);

        // Phantom Rider
        ArrayList<String> PhantomRiderDesc = new ArrayList<>();
        PhantomRiderDesc.add("§7Zombie on a chicken or skeleton on a spider? Stray on a phantom.");
        PhantomRiderDesc.add("§7Might be problematic to kill.");
        ArrayList<String> PhantomRiderDrops = new ArrayList<>();
        PhantomRiderDrops.add("§7None");
        ItemStack PhantomRiderPage = builder.create(config.getString("PhantomRiderNametag"),3,config.getInt("PhantomRiderHealth"),PhantomRiderDesc,PhantomRiderDrops);

        // Frozen Soul
        ArrayList<String> FrozenSoulDesc = new ArrayList<>();
        FrozenSoulDesc.add("§7Once a brave hero now frozen forever.");
        FrozenSoulDesc.add("§7This soul slows opponents with its aura and it can shoot frozen shards that debuff the opponent.");
        ArrayList<String> FrozenSoulDrops = new ArrayList<>();
        FrozenSoulDrops.add("§7Not now");
        ItemStack FrozenSoulPage = builder.create(config.getString("FrozenSoulNametag"),9,config.getInt("FrozenSoulHealth"),FrozenSoulDesc,FrozenSoulDrops);

        // Desert Guardian
        ArrayList<String> DesertGuardianDesc = new ArrayList<>();
        DesertGuardianDesc.add("§7This is a desert type of zombie that was wandering the deserts long time ago.");
        DesertGuardianDesc.add("§7Now its a rare type of zombie that can poison its opponents.");
        ArrayList<String> DesertGuardianDrops = new ArrayList<>();
        DesertGuardianDrops.add("§7None");
        ItemStack DesertGuardianPage = builder.create(config.getString("DesertGuardianNametag"),2,config.getInt("DesertGuardianHealth"),DesertGuardianDesc,DesertGuardianDrops);

        // Revenant Archer
        ArrayList<String> RevenantArcherDesc = new ArrayList<>();
        RevenantArcherDesc.add("§7This is a strong undead assassin that uses bow instead of sword.");
        RevenantArcherDesc.add("§7Being similar to the "+config.getString("MasterAssassinNametag").split("!")[1]+" it can teleport");
        RevenantArcherDesc.add("§7away from the opponent.");
        ArrayList<String> RevenantArcherDrops = new ArrayList<>();
        RevenantArcherDrops.add("§7None");
        ItemStack RevenantArcherPage = builder.create(config.getString("RevenantArcherNametag"),8,config.getInt("RevenantArcherHealth"),RevenantArcherDesc,RevenantArcherDrops);

        // Mushroom Monster
        ArrayList<String> MushroomMonsterDesc = new ArrayList<>();
        MushroomMonsterDesc.add("§7This is a very strange creature that is similar to "+ config.getString("ParasiteNametag").split("!")[1]);
        MushroomMonsterDesc.add("§7Instead of creating armies of zombies it creates tons of mushroom, that");
        MushroomMonsterDesc.add("§7can seriously hurt the player.");
        ArrayList<String> MushroomMonsterDrops = new ArrayList<>();
        MushroomMonsterDrops.add("§7None");
        ItemStack MushroomMonsterPage = builder.create(config.getString("MushroomMonsterNamatag"),9,config.getInt("MushroomMonsterHealth"),MushroomMonsterDesc,MushroomMonsterDrops);

        gui.addItem(WitherSpiderPage);
        gui.addItem(ParasitePage);
        gui.addItem(InfectedZombiePage);
        gui.addItem(MasterAssassinPage);
        gui.addItem(AirSpiritPage);
        gui.addItem(FireSpiritPage);
        gui.addItem(IceSpiritPage);
        gui.addItem(SemiIdolPage);
        gui.addItem(AncientZombiePage);
        gui.addItem(OverworldInvaderPage);
        gui.addItem(GhostPage);
        gui.addItem(WitherusNetherlordPage);
        gui.addItem(CursedEmperorPage);
        gui.addItem(PhantomRiderPage);
        gui.addItem(FrozenSoulPage);
        gui.addItem(DesertGuardianPage);
        gui.addItem(RevenantArcherPage);
        gui.addItem(MushroomMonsterPage);

        player.openInventory(gui);
    }

    @EventHandler
    public void guiClick(InventoryClickEvent e)
    {
        if(!e.getInventory().equals(gui))
            return;

        e.setCancelled(true);

        Player p = (Player)e.getWhoClicked();

        if(e.getSlot() == 0)
            p.sendMessage("ok");
    }

    @EventHandler
    public void openGuiEvent(BestiaryEvent e)
    {
        createGui(e.getPlayer());
    }
}
