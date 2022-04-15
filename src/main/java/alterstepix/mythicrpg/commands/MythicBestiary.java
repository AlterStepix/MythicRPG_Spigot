package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.guis.BestiaryEvent;
import alterstepix.mythicrpg.guis.BestiaryGui;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MythicBestiary implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p)
        {
            Bukkit.getServer().getPluginManager().callEvent(new BestiaryEvent(p));
        }
        else
        {

        }
        return true ;
    }
}
