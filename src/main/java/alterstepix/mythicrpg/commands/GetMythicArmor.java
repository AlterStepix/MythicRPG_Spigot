package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.guis.ArmorMenu;
import alterstepix.mythicrpg.managers.ArmorSetsManager;
import alterstepix.mythicrpg.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GetMythicArmor implements CommandExecutor {

    Mythicrpg main;
    private final int offset = 10;

    public GetMythicArmor(Mythicrpg main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player)
        {
            if(sender.isOp() || sender.hasPermission("mythicrpg.getmythicarmor"))
            {

                ArmorMenu menu = new ArmorMenu(Mythicrpg.getPMU(player));
                menu.open();

                player.sendMessage(Messages.CommandSuccess);
            }


        }
        else
        {
            Bukkit.getLogger().info(Messages.NotPlayer);
        }
        return true;
    }
}
