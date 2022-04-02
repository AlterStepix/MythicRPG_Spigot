package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.Messages;
import alterstepix.mythicrpg.managers.ScrollManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GetMythicScrolls implements CommandExecutor {
    Mythicrpg main;

    public GetMythicScrolls(Mythicrpg main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
        {
            if(sender.isOp() || sender.hasPermission("mythicrpg.getmythicscrolls"))
            {
                Inventory gui = Bukkit.createInventory(null, InventoryType.CHEST);
                ScrollManager m = new ScrollManager(main);
                m.init();

                gui.addItem(m.ArrowStormScroll);
                gui.addItem(m.NetherStormScroll);
                gui.addItem(m.HealingTotemScroll);

                Player p = (Player)sender;
                p.openInventory(gui);
                p.sendMessage(Messages.CommandSuccess);
            }


        }
        else
        {
            Bukkit.getLogger().info(Messages.NotPlayer);
        }
        return true;
    }
}
