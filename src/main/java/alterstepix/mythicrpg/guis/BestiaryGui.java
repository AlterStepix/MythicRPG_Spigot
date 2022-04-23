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
        WitherSpiderDesc.add("§7This is dangerous mob summoned by a "+config.getString("NetherLordBossNametag").split("!")[1]+".");
        WitherSpiderDesc.add("§7It jumps on you, withers you and slows you down with its webs.");
        ArrayList<String> WitherSpiderDrops = new ArrayList<>();
        WitherSpiderDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("WitheredEyeName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack WitherSpiderPage = builder.create(config.getString("WitherSpiderNametag"),3,config.getInt("WitherSpiderHealth"),WitherSpiderDesc,WitherSpiderDrops);

        //Parasite
        ArrayList<String> ParasiteDesc = new ArrayList<>();
        ParasiteDesc.add("§7This is a very dangerous mob that summons mobs "+(config.getString("InfectedZombieNametag").split("!"))[1]+"s.");
        ParasiteDesc.add("§7It also trows its orbs at the opponent.");
        ArrayList<String> ParasiteDrops = new ArrayList<>();
        ParasiteDrops.add(ColorUtil.ConvertToCustom(config.getString("RareRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("ParasiteHeartName"))+" with "+config.getInt("RareChance")+"%"+" chance");
        ParasiteDrops.add("§7Other:");
        ParasiteDrops.add("§730-th level armor with 50% chance");
        ParasiteDrops.add("§730-th level sword with 50% chance");
        ItemStack ParasitePage = builder.create(config.getString("ParasiteNametag"),10,config.getInt("ParasiteHealth"),ParasiteDesc,ParasiteDrops);

        // Infected Zombie
        ArrayList<String> InfectedZombieDesc = new ArrayList<>();
        InfectedZombieDesc.add("§7This is dangerous mob summoned by a "+config.getString("ParasiteNametag").split("!")[1]+".");
        InfectedZombieDesc.add("§7It also can heal from heating you.");
        ArrayList<String> InfectedZombieDrops = new ArrayList<>();
        InfectedZombieDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("InfectedFleshName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        InfectedZombieDrops.add(ColorUtil.ConvertToCustom(config.getString("RareRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("InfectedHeartName"))+" with "+config.getInt("RareChance")+"%"+" chance");
        ItemStack InfectedZombiePage = builder.create(config.getString("InfectedZombieNametag"),2,config.getInt("InfectedZombieHealth"),InfectedZombieDesc,InfectedZombieDrops);

        // Master Assassin
        ArrayList<String> MasterAssassinDesc = new ArrayList<>();
        MasterAssassinDesc.add("§7This is a very dangerous mob that can teleport behind your back.");
        ArrayList<String> MasterAssassinDrops = new ArrayList<>();
        MasterAssassinDrops.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("ShadyAura"))+" with "+config.getInt("LegendaryChance")+"%"+" chance");
        ItemStack MasterAssassinPage = builder.create(config.getString("MasterAssassinNametag"),4,config.getInt("MasterAssassinHealth"),MasterAssassinDesc,MasterAssassinDrops);

        // Air Spirit
        ArrayList<String> AirSpiritDesc = new ArrayList<>();
        AirSpiritDesc.add("§7This is dangerous mob summoned by a "+config.getString("SemiIdolNametag").split("!")[1]+".");
        AirSpiritDesc.add("§7It also can pull opponent towards itself.");
        ArrayList<String> AirSpiritDrops = new ArrayList<>();
        AirSpiritDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("ImpulseShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack AirSpiritPage = builder.create(config.getString("AirSpiritNametag"),5,config.getInt("AirSpiritHealth"),AirSpiritDesc,AirSpiritDrops);

        // Fire Spirit
        ArrayList<String> FireSpiritDesc = new ArrayList<>();
        FireSpiritDesc.add("§7This is dangerous mob summoned by a "+config.getString("SemiIdolNametag").split("!")[1]+".");
        FireSpiritDesc.add("§7It also ignites opponent for 6 seconds.");
        ArrayList<String> FireSpiritDrops = new ArrayList<>();
        FireSpiritDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("AmberShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack FireSpiritPage = builder.create(config.getString("FireSpiritNametag"),5,config.getInt("FireSpiritHealth"),FireSpiritDesc,FireSpiritDrops);

        // Ice Spirit
        ArrayList<String> IceSpiritDesc = new ArrayList<>();
        IceSpiritDesc.add("§7This is dangerous mob summoned by a "+config.getString("SemiIdolNametag").split("!")[1]+".");
        IceSpiritDesc.add("§7It also gives the opponent a weakness and a slow.");
        ArrayList<String> IceSpiritDrops = new ArrayList<>();
        IceSpiritDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("FrozenShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack IceSpiritPage = builder.create(config.getString("IceSpiritNametag"),5,config.getInt("IceSpiritHealth"),IceSpiritDesc,IceSpiritDrops);

        //Semi Idol
        ArrayList<String> SemiIdolDesc = new ArrayList<>();
        SemiIdolDesc.add("§7This is mob that summons: ");
        SemiIdolDesc.add((config.getString("AirSpiritNametag").split("!"))[1]+"s, "+(config.getString("FireSpiritNametag").split("!"))[1]+"s and "+(config.getString("IceSpiritNametag").split("!"))[1]+"s.");
        SemiIdolDesc.add("§7It also summons lighting bolts at the opponent.");
        ArrayList<String> SemiIdolDrops = new ArrayList<>();
        SemiIdolDrops.add(ColorUtil.ConvertToCustom(config.getString("EpicRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("LightningShardName"))+" with "+config.getInt("EpicChance")+"%"+" chance");
        SemiIdolDrops.add("§7Other:");
        SemiIdolDrops.add("§740-th level armor with 50% chance");
        SemiIdolDrops.add("§740-th level sword with 50% chance");
        ItemStack SemiIdolPage = builder.create(config.getString("SemiIdolNametag"),12,config.getInt("SemiIdolHealth"),SemiIdolDesc,SemiIdolDrops);

        // Ancient Zombie
        ArrayList<String> AncientZombieDesc = new ArrayList<>();
        AncientZombieDesc.add("§7It gives the opponent a weakness.");
        ArrayList<String> AncientZombieDrops = new ArrayList<>();
        AncientZombieDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("AncientShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        AncientZombieDrops.add(ColorUtil.ConvertToCustom(config.getString("RareRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("DecayedHeartName"))+" with "+config.getInt("RareChance")+"%"+" chance");
        ItemStack AncientZombiePage = builder.create(config.getString("AncientZombieNametag"),3,config.getInt("AncientZombieHealth"),AncientZombieDesc,AncientZombieDrops);

        //Overworld Invader
        ArrayList<String> OverworldInvaderDesc = new ArrayList<>();
        OverworldInvaderDesc.add("§7It can pull opponent towards itself.");
        OverworldInvaderDesc.add("§7It can also shoot fireballs.");
        ArrayList<String> OverworldInvaderDrops = new ArrayList<>();
        OverworldInvaderDrops.add(ColorUtil.ConvertToCustom(config.getString("RareRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("DestructiveShard"))+" with "+config.getInt("RareChance")+"%"+" chance");
        OverworldInvaderDrops.add("§7Other:");
        OverworldInvaderDrops.add("§735-th level armor with 50% chance");
        OverworldInvaderDrops.add("§735-th level sword with 50% chance");
        ItemStack OverworldInvaderPage = builder.create(config.getString("OverworldInvaderNamtetag"),9,config.getInt("AncientZombieHealth"),OverworldInvaderDesc,OverworldInvaderDrops);

        // Ghost
        ArrayList<String> GhostDesc = new ArrayList<>();
        GhostDesc.add("§7It gives the opponent blindness and a slow.");
        ArrayList<String> GhostDrops = new ArrayList<>();
        GhostDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("GhostEssence"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack GhostPage = builder.create(config.getString("GhostNametag"),4,config.getInt("GhostHealth"),GhostDesc,GhostDrops);

        //Witherus Netherlord
        ArrayList<String> WitherusNetherlordDesc = new ArrayList<>();
        WitherusNetherlordDesc.add("§7This is a very dangerous boss that can do many different things.");
        WitherusNetherlordDesc.add("§7It also has three phases.");
        WitherusNetherlordDesc.add("§7In order to kill this boss you need to do hard teamwork.");
        ArrayList<String> WitherusNetherlordDrops = new ArrayList<>();
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("NetherEssenceName"))+" in the amount of three pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("WitheredShardName"))+" in the amount of fife pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("ArrowStormName"))+" in the amount of two pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("NetherStormScroll"))+" in the amount of two pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("InfernalAuraScroll"))+" in the amount of two pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("HealingTotemScroll"))+" in the amount of two pieces");
        WitherusNetherlordDrops.add(ColorUtil.ConvertToCustom(config.getString("NetherCatalystName")));
        WitherusNetherlordDrops.add("§7Other:");
        WitherusNetherlordDrops.add("§770-th level armor in the amount of three pieces");
        WitherusNetherlordDrops.add("§770-th level sword in the amount of three pieces");
        ItemStack WitherusNetherlordPage = builder.create(config.getString("NetherLordBossNametag"),25,config.getInt("NetherLordBossHealth"),WitherusNetherlordDesc,WitherusNetherlordDrops);

        //Cursed Emperor
        ArrayList<String> CursedEmperorDesc = new ArrayList<>();
        CursedEmperorDesc.add("§7This is a very dangerous boss that summons many different mobs.");
        CursedEmperorDesc.add("§7In order to kill this boss you need to do hard teamwork.");
        ArrayList<String> CursedEmperorDrops = new ArrayList<>();
        CursedEmperorDrops.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("CursedCrownName"))+" with "+config.getInt("LegendaryChance")+"%"+" chance");
        CursedEmperorDrops.add(ColorUtil.ConvertToCustom(config.getString("LegendaryRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("CursedHeartName"))+" with "+config.getInt("LegendaryChance")+"%"+" chance");
        CursedEmperorDrops.add(ColorUtil.ConvertToCustom(config.getString("CursedBoneName"))+" in the amount of fife pieces");
        ItemStack CursedEmperorPage = builder.create(config.getString("CursedEmperorBossNametag"),20,config.getInt("CursedEmperorBossHealth"),CursedEmperorDesc,CursedEmperorDrops);

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
