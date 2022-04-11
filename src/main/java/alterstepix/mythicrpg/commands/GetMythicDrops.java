package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.managers.DropTable;
import alterstepix.mythicrpg.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GetMythicDrops implements CommandExecutor {
    Mythicrpg main;

    public GetMythicDrops(Mythicrpg main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
        {
            if(sender.isOp() || sender.hasPermission("mythicrpg.getmythicdrops"))
            {
                Inventory gui = Bukkit.createInventory(null, InventoryType.CHEST);
                DropTable m = new DropTable(main);
                m.init();
                gui.addItem(m.amberShard);
                gui.addItem(m.frozenShard);
                gui.addItem(m.impulseShard);
                gui.addItem(m.infectedFlesh);
                gui.addItem(m.witheredEye);
                gui.addItem(m.ancientShard);
                gui.addItem(m.witheredShard);
                gui.addItem(m.infectedHeart);
                gui.addItem(m.decayedHeart);
                gui.addItem(m.parasiteHeart);
                gui.addItem(m.netherEssence);
                gui.addItem(m.lightningShard);
                gui.addItem(m.netherCatalyst);
                gui.addItem(m.cursedCrown);

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
