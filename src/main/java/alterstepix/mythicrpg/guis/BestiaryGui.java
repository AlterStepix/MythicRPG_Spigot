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
        ParasiteDrops.add("§730-th level armor with 30% chance");
        ParasiteDrops.add("§730-th level sword with 30% chance");
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
        WitherSpiderDesc.add("§7This is dangerous mob summoned by a "+config.getString("SemiIdolNametag").split("!")[1]+".");
        AirSpiritDesc.add("§7It also can pull towards itself.");
        ArrayList<String> AirSpiritDrops = new ArrayList<>();
        AirSpiritDrops.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity"))+": "+ColorUtil.ConvertToCustom(config.getString("ImpulseShardName"))+" with "+config.getInt("CommonChance")+"%"+" chance");
        ItemStack AirSpiritPage = builder.create(config.getString("AirSpiritNametag"),5,config.getInt("AirSpiritHealth"),AirSpiritDesc,AirSpiritDrops);

        gui.addItem(WitherSpiderPage);
        gui.addItem(ParasitePage);
        gui.addItem(InfectedZombiePage);
        gui.addItem(MasterAssassinPage);
        gui.addItem(AirSpiritPage);
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
