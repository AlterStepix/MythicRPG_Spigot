package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.util.RandomLootGenerator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetRandomLoot implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 1)
        {
            if(args[0].equals("sword"))
            {
                ItemStack i = RandomLootGenerator.getLootSword(Integer.parseInt(args[1]));
                if(sender instanceof Player p)
                {
                    p.getInventory().addItem(i);
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
