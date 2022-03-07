package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AppendAbilityLore implements CommandExecutor {

    Mythicrpg main;
    ItemManager m;
    FileConfiguration config;
    public AppendAbilityLore(Mythicrpg main)
    {
        this.main = main;
        m = new ItemManager(main);
        m.init();
        this.config = main.getConfiguration();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 1)
        {
            Player p = (Player)sender;
            ItemStack mainhand = p.getInventory().getItemInMainHand();
            ItemMeta meta = mainhand.getItemMeta();
            List<String> lore = new ArrayList<String>();
            switch(args[0])
            {
                case "Curse":
                    if(meta.hasLore())
                        lore=meta.getLore();

                    lore.add("");
                    lore.add("§6ITEM ABILITY: §eCurse");
                    lore.add("§7Applies strong debuffs to your enemies on hit");
                    meta.setLore(lore);
                    mainhand.setItemMeta(meta);
                    p.getInventory().setItemInMainHand(mainhand);
                    break;
                case "Thunderlord":
                    if(meta.hasLore())
                        lore=meta.getLore();
                    int cooldown = this.config.getInt("healingSwordCooldown");
                    lore.add("");
                    lore.add("§6RIGHT CLICK: §eThunderlord");
                    lore.add("§7Strikes all nearby enemies with lightning");
                    lore.add("§8Cooldown: "+ cooldown + "s");
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
