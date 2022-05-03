package alterstepix.mythicrpg.guis;

import alterstepix.mythicrpg.menusystem.AbstractMenu;
import alterstepix.mythicrpg.menusystem.MenuItemWrapper;
import alterstepix.mythicrpg.util.PMU;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class NonOPBestiaryPageMenu extends AbstractMenu {

    ItemStack page;
    public NonOPBestiaryPageMenu(PMU pmu, ItemStack page) {
        super(pmu);
        this.page = page;
    }

    @Override
    public String getMenuName() {
        return "Page options";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        switch (event.getSlot())
        {
            case 3 -> {
                ItemStack newpage = page.clone();
                ItemMeta meta = newpage.getItemMeta();
                List<String> nm = meta.getLore();
                nm.remove(nm.size()-1);
                nm.remove(nm.size()-1);
                meta.setLore(nm);
                newpage.setItemMeta(meta);

                player.getInventory().addItem(newpage);
            }
            case 5 -> {
                player.closeInventory();
            }
            default -> {

            }
        }
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(4,page);
        inventory.setItem(3,MenuItemWrapper.item(Material.CHEST,"§aTake the page", "§eClick add this page to your inventory"));
        inventory.setItem(5,MenuItemWrapper.item(Material.BARRIER, "§cClose", "§eClick to close the menu"));
    }
}
