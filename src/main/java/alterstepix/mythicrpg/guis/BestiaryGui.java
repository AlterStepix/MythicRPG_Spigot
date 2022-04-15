package alterstepix.mythicrpg.guis;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.BestiaryPageBuilder;
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
        WitherSpiderDesc.add("§7Subscribe to AlterStepix");
        ArrayList<String> WitherSpiderDrops = new ArrayList<>();
        WitherSpiderDrops.add("§7LMAo");
        ItemStack WitherSpiderPage = builder.create(config.getString("WitherSpiderNametag"),3,config.getInt("WitherSpiderHealth"),WitherSpiderDesc,WitherSpiderDrops);

        ArrayList<String> ParasiteDesc = new ArrayList<>();
        WitherSpiderDesc.add("§7Subscribe to AlterStepix");
        ArrayList<String> ParasiteDrops = new ArrayList<>();
        WitherSpiderDrops.add("§7LMAo");
        ItemStack ParasitePage = builder.create(config.getString("ParasiteNametag"),10,config.getInt("ParasiteHealth"),ParasiteDesc,ParasiteDrops);

        gui.addItem(WitherSpiderPage);
        gui.addItem(ParasitePage);
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
