package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.mobs.WitherSpider;
import alterstepix.mythicrpg.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SummonMythicMob implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1)
        {
            if(sender instanceof Player){
                Player player = (Player)sender;
                if(player.isOp())
                {
                    switch (args[0])
                    {
                        case "WitherSpider":
                            WitherSpider.createLeapingSpider(player.getLocation());
                            break;
                    }
                }
                else
                {
                    player.sendMessage(Messages.NotOperator);
                }

            }
            else
            {
                Bukkit.getLogger().info("[mrpg] To use this command from console / command block you need to specify the location");
            }

        }

        return false;
    }
}
