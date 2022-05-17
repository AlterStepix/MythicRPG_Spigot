/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.guis;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.managers.ArmorSetsManager;
import alterstepix.mythicrpg.menusystem.AbstractPaginatedMenu;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.PMU;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArmorMenu extends AbstractPaginatedMenu {

    ArrayList<ItemStack> content;

    public ArmorMenu(PMU pmu) {
        super(pmu);
    }

    public void createContent()
    {
        content = new ArrayList<>();
        ArmorSetsManager m = new ArmorSetsManager(Mythicrpg.INSTANCE);
        m.init();
        content.addAll(List.of(
                m.GrandmasterArmorSet[0],
                m.GrandmasterArmorSet[1],
                m.GrandmasterArmorSet[2],
                m.GrandmasterArmorSet[3],
                m.MasterAssasinArmorSet[0],
                m.MasterAssasinArmorSet[1],
                m.MasterAssasinArmorSet[2],
                m.FrozenWarriorArmorSet[0],
                m.FrozenWarriorArmorSet[1],
                m.FrozenWarriorArmorSet[2],
                m.FrozenWarriorArmorSet[3],
                m.MythicWarriorArmorset[0],
                m.MythicWarriorArmorset[1],
                m.MythicWarriorArmorset[2],
                m.MythicWarriorArmorset[3],
                m.ThiefArmorset[0],
                m.ThiefArmorset[1],
                m.ThiefArmorset[2],
                m.ThiefArmorset[3],
                m.BeastArmorset[0],
                m.BeastArmorset[1],
                m.CorruptedMythicArmor[0],
                m.CorruptedMythicArmor[1],
                m.CorruptedMythicArmor[2],
                m.CorruptedMythicArmor[3],
                m.GlacialMythicArmor[0],
                m.GlacialMythicArmor[1],
                m.GlacialMythicArmor[2],
                m.GlacialMythicArmor[3]
        ));
    }

    @Override
    public String getMenuName() {
        return "Mythic Armor Gui";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        Player player = (Player)event.getWhoClicked();
        createContent();

        if(event.getCurrentItem().getType() != FILLER_GLASS.getType())
        {
            switch (event.getCurrentItem().getType())
            {
                case ARROW -> {
                    if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Right"))
                    {
                        if (!((index + 1) >= content.size())){
                            page = page + 1;
                            super.open();
                        }else{
                            player.sendMessage(ChatColor.GRAY + "You are already on the last page.");
                        }

                    }
                    else if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Left"))
                    {
                        if (page == 0){
                            player.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                        }else{
                            page = page - 1;
                            super.open();
                        }

                    }

                    setMenuItems();
                }
                case BARRIER -> {
                    player.closeInventory();
                }
                default -> {
                    player.getInventory().addItem(event.getCurrentItem());
                }
            }
        }


    }

    @Override
    public void setMenuItems() {
        inventory.clear();
        addMenuBorder();

        createContent();
        ArmorSetsManager m = new ArmorSetsManager(Mythicrpg.INSTANCE);
        m.init();
        //The thing you will be looping through to place items
        ///////////////////////////////////// Pagination loop template
        if(content != null && !content.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= content.size()) break;
                if (content.get(index) != null){
                    ///////////////////////////

                    ItemStack item = content.get(index);

                    inventory.addItem(item);

                    ////////////////////////
                }
            }
        }

    }
}
