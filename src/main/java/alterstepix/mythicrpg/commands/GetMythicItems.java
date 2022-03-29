package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.ItemManager;
import alterstepix.mythicrpg.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GetMythicItems implements CommandExecutor {

    Mythicrpg main;

    public GetMythicItems(Mythicrpg main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player)
        {
            if(sender.isOp() || sender.hasPermission("mythicrpg.getmythicitems"))
            {
                Inventory gui = Bukkit.createInventory(null,InventoryType.CHEST);
                ItemManager m = new ItemManager(main);
                m.init();
                gui.addItem(m.LightingAxe);
                gui.addItem(m.IdolsIncarnate);
                gui.addItem(m.Terminator);
                gui.addItem(m.HealingSword);
                gui.addItem(m.FrozenWand);
                gui.addItem(m.ImpulseSword);
                gui.addItem(m.AmberScythe);
                gui.addItem(m.MilkPotion);
                gui.addItem(m.GiantSword);
                gui.addItem(m.FuriousAxe);
                gui.addItem(m.AirBurner);
                gui.addItem(m.RuinicDagger[0]);

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
