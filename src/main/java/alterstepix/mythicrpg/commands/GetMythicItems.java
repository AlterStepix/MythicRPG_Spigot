package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GetMythicItems implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Inventory gui = Bukkit.createInventory(null,InventoryType.CHEST);
        ItemManager m = new ItemManager();
        gui.addItem(m.LightingAxe);
        gui.addItem(m.IdolsIncarnate);
        Player p = (Player)sender;
        p.openInventory(gui);
        return true;

    }
}
