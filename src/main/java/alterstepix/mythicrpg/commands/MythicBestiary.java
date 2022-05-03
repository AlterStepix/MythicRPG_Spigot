package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.guis.BestiaryMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MythicBestiary implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p)
        {
            BestiaryMenu menu = new BestiaryMenu(Mythicrpg.getPMU(p));
            menu.open();
        }
        else
        {

        }
        return true ;
    }
}
