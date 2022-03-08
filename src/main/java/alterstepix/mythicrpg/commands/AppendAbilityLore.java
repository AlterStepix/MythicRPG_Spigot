package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemManager;
import alterstepix.mythicrpg.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AppendAbilityLore implements CommandExecutor, TabCompleter {

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
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
                if (p.isOp()) {
                    ItemStack mainhand = p.getInventory().getItemInMainHand();
                    ItemMeta meta = mainhand.getItemMeta();
                    List<String> lore = new ArrayList<String>();
                    int radius = this.config.getInt("terminatorAbilityRange");
                    switch (args[0]) {
                        case "Curse":
                            if (meta.hasLore())
                                lore = meta.getLore();

                            lore.add("");
                            lore.add("§6ITEM ABILITY: §eCurse");
                            lore.add("§7Applies strong debuffs to your enemies on hit");
                            meta.setLore(lore);
                            mainhand.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(mainhand);
                            p.sendMessage(Messages.CommandSuccess);
                            break;
                        case "Thunderlord":
                            if (meta.hasLore())
                                lore = meta.getLore();
                            int cooldown = this.config.getInt("healingSwordCooldown");
                            lore.add("");
                            lore.add("§6RIGHT CLICK: §eThunderlord");
                            lore.add("§7Strikes all nearby enemies with lightning");
                            lore.add("§8Cooldown: " + cooldown + "s");
                            meta.setLore(lore);
                            mainhand.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(mainhand);
                            p.sendMessage(Messages.CommandSuccess);
                            break;
                        case "LightningPower":
                            if (meta.hasLore())
                                lore = meta.getLore();

                            lore.add("");
                            lore.add("§6ITEM ABILITY: §eLightning Power");
                            lore.add("§7Strikes your enemies with lightning on hit");
                            meta.setLore(lore);
                            mainhand.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(mainhand);
                            p.sendMessage(Messages.CommandSuccess);
                            break;
                        case "Termination":
                            if (meta.hasLore())
                                lore = meta.getLore();

                            lore.add("");
                            lore.add("§6LEFT CLICK: §eTermination");
                            lore.add("§7Instantly shoots 3 arrow");
                            meta.setLore(lore);
                            mainhand.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(mainhand);
                            p.sendMessage(Messages.CommandSuccess);
                            break;
                        case "Recall":
                            if (meta.hasLore())
                                lore = meta.getLore();

                            lore.add("");
                            lore.add("§6SNEAK + LEFT CLICK: §eRecall");
                            lore.add("§7Sends all arrows in a " + radius + " blocks radius to the sky");
                            meta.setLore(lore);
                            mainhand.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(mainhand);
                            p.sendMessage(Messages.CommandSuccess);
                            break;
                        case "Annihilation":
                            if (meta.hasLore())
                                lore = meta.getLore();

                            lore.add("");
                            lore.add("§6KEYBOARD F: §eAnnihilation");
                            lore.add("§7Explodes all arrows in a " + radius + " blocks radius");
                            meta.setLore(lore);
                            mainhand.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(mainhand);
                            p.sendMessage(Messages.CommandSuccess);
                            break;
                        case "Healing":
                            if (meta.hasLore())
                                lore = meta.getLore();

                            lore.add("");
                            lore.add("§6RIGHT CLICK: §eHealing");
                            lore.add("§7Heals you in exchange for hunger");
                            meta.setLore(lore);
                            mainhand.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(mainhand);
                            p.sendMessage(Messages.CommandSuccess);
                            break;

                        case "FrozenBreathe":
                            if (meta.hasLore())
                                lore = meta.getLore();


                            int r = config.getInt("frozenWandRadius");
                            int cooldownFW = config.getInt("frozenWandCooldown");
                            lore.add("");
                            lore.add("§6RIGHT CLICK: §eFrozen Breathe");
                            lore.add("§7Debuffs your enemies in a "+r+" radius");
                            lore.add("§8Cooldown: "+ cooldownFW + "s");

                            meta.setLore(lore);
                            mainhand.setItemMeta(meta);
                            p.getInventory().setItemInMainHand(mainhand);
                            p.sendMessage(Messages.CommandSuccess);
                            break;


                        default:
                            p.sendMessage("§c[mrpg] Unknown ability name");
                            break;
                    }

                }
                else
                {
                    p.sendMessage(Messages.NotOperator);
                }
            }
            else
            {
                p.sendMessage("§c[mrpg] The ability name is missing");
            }
        }
        else{
            Bukkit.getLogger().info(Messages.NotPlayer);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> Abilities = new ArrayList<>();
        Abilities.add("Annihilation");
        Abilities.add("Healing");
        Abilities.add("Recall");
        Abilities.add("Termination");
        Abilities.add("LightningPower");
        Abilities.add("Thunderlord");
        Abilities.add("FrozenBreathe");
        Abilities.add("Curse");

        return Abilities;

    }
}
