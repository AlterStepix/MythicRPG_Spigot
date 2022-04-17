package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
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

        if(sender instanceof Player)
        {
            if(sender.isOp() || sender.hasPermission("mythicrpg.getmythicarmor"))
            {
                Inventory gui = Bukkit.createInventory(null, InventoryType.CHEST);
                ArmorSetsManager m = new ArmorSetsManager(main);
                m.init();

                gui.addItem(m.GrandmasterArmorSet[0]);
                gui.addItem(m.GrandmasterArmorSet[1]);
                gui.addItem(m.GrandmasterArmorSet[2]);
                gui.addItem(m.GrandmasterArmorSet[3]);

                gui.addItem(m.MasterAssasinArmorSet[0]);
                gui.addItem(m.MasterAssasinArmorSet[1]);
                gui.addItem(m.MasterAssasinArmorSet[2]);

                gui.addItem(m.FrozenWarriorArmorSet[0]);
                gui.addItem(m.FrozenWarriorArmorSet[1]);
                gui.addItem(m.FrozenWarriorArmorSet[2]);
                gui.addItem(m.FrozenWarriorArmorSet[3]);

                gui.addItem(m.MythicWarriorArmorset[0]);
                gui.addItem(m.MythicWarriorArmorset[1]);
                gui.addItem(m.MythicWarriorArmorset[2]);
                gui.addItem(m.MythicWarriorArmorset[3]);

                gui.addItem(m.ThiefArmorset[0]);
                gui.addItem(m.ThiefArmorset[1]);
                gui.addItem(m.ThiefArmorset[2]);
                gui.addItem(m.ThiefArmorset[3]);

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
