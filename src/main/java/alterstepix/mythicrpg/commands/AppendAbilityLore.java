package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AppendAbilityLore implements CommandExecutor {

    Mythicrpg main;
    ItemManager m;
    public AppendAbilityLore(Mythicrpg main)
    {
        this.main = main;
        m = new ItemManager(main);
        m.init();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 1)
        {
            Player p = (Player)sender;
            switch(args[0])
            {
                case "Curse":
                    ItemStack mainhand = p.getInventory().getItemInMainHand();
                    ItemMeta meta = mainhand.getItemMeta();
                    List<String> lore = new ArrayList<String>();
                    if(meta.hasLore())
                        lore=meta.getLore();

                    lore.add("");
                    lore.add("§6ITEM ABILITY: §eCurse");
                    lore.add("§7Applies strong debuffs to your enemies on hit");
                    meta.setLore(lore);
                    mainhand.setItemMeta(meta);
                    p.getInventory().setItemInMainHand(mainhand);
                    break;
                default:
                    break;
            }



            return true;
        }


        return false;
    }
}
