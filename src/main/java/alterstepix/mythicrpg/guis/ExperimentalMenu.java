/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.guis;

import alterstepix.mythicrpg.menusystem.AbstractMenu;
import alterstepix.mythicrpg.menusystem.MenuItemWrapper;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.PMU;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ExperimentalMenu extends AbstractMenu {

    public ExperimentalMenu(PMU pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return "§cExperimental Menu";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        switch (event.getCurrentItem().getType()){
            case GREEN_BED -> {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().setHealth(0);
            }
        }

    }

    @Override
    public void setMenuItems() {
        ItemStack yes = MenuItemWrapper.item(Material.GREEN_BED, ColorUtil.ConvertToCustom("#123456!Name"),"Lore Line 1","§cEpic Lore Line 2", "§6Mythic §7Lore §1Line §e3");

        inventory.setItem(3,yes);
    }
}
