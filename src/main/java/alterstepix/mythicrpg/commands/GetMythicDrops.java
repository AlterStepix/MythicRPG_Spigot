/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.guis.DropsMenu;
import alterstepix.mythicrpg.managers.DropTable;
import alterstepix.mythicrpg.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class GetMythicDrops implements CommandExecutor {
    Mythicrpg main;

    public GetMythicDrops(Mythicrpg main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player)
        {
            if(sender.isOp() || sender.hasPermission("mythicrpg.getmythicdrops"))
            {
                DropsMenu menu = new DropsMenu(Mythicrpg.getPMU(player));
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
