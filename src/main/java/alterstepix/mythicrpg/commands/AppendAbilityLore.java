package alterstepix.mythicrpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AppendAbilityLore implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 1)
        {
            if (args[0].toLowerCase()=="curse")
            {


            }

            return true;
        }


        return false;
    }
}
