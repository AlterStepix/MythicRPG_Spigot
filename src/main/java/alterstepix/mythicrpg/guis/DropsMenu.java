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
import alterstepix.mythicrpg.managers.DropTable;
import alterstepix.mythicrpg.managers.ItemManager;
import alterstepix.mythicrpg.menusystem.AbstractPaginatedMenu;
import alterstepix.mythicrpg.util.PMU;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DropsMenu extends AbstractPaginatedMenu {
    ArrayList<ItemStack> content = new ArrayList<>();

    private void createContent() {
        content = new ArrayList<>();
        DropTable m = new DropTable(Mythicrpg.INSTANCE);
        m.init();
        content.addAll(List.of(
                m.amberShard,
                m.frozenShard,
                m.impulseShard,
                m.infectedFlesh,
                m.witheredEye,
                m.ancientShard,
                m.witheredShard,
                m.cursedBone,
                m.ghostEssence,
                m.infectedHeart,
                m.decayedHeart,
                m.destructiveShard,
                m.parasiteHeart,
                m.netherEssence,
                m.lightningShard,
                m.netherCatalyst,
                m.cursedCrown,
                m.cursedHeart,
                m.shadyAura

        ));
    }

    public DropsMenu(PMU pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return "Mythic Drops Menu";
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
