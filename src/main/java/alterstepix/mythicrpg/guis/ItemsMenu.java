package alterstepix.mythicrpg.guis;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.managers.ArmorSetsManager;
import alterstepix.mythicrpg.managers.ItemManager;
import alterstepix.mythicrpg.menusystem.AbstractPaginatedMenu;
import alterstepix.mythicrpg.util.PMU;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemsMenu extends AbstractPaginatedMenu {
    ArrayList<ItemStack>content = new ArrayList<>();
    public ItemsMenu(PMU pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return "Mythic Items Gui";
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

    private void createContent() {
        content = new ArrayList<>();
        ItemManager m = new ItemManager(Mythicrpg.INSTANCE);
        m.init();
        content.addAll(List.of(
                m.BestiaryBook,
                m.IdolsIncarnate,
                m.Terminator,
                m.HealingSword,
                m.FrozenWand,
                m.ImpulseSword,
                m.AmberScythe,
                m.MilkPotion,
                m.GiantSword,
                m.FuriousAxe,
                m.AirBurner,
                m.RuinicDagger[0],
                m.MythicSwordofLegends,
                m.FlamingWhip,
                m.DarknessConcentrator,
                m.InfectedSword,
                m.SwordOfGrowth,
                m.Singularity,
                m.CorruptedMythicIdolsIncarnate
        ));
    }
}

