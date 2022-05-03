package alterstepix.mythicrpg.menusystem;

import alterstepix.mythicrpg.util.PMU;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractMenu implements InventoryHolder {

    protected Inventory inventory;
    protected PMU pmu;
    protected ItemStack FILLER_GLASS = MenuItemWrapper.item(Material.CYAN_STAINED_GLASS_PANE,"");

    public AbstractMenu(PMU pmu)
    {
        this.pmu = pmu;
    }

    public abstract String getMenuName();
    public abstract int getSlots();
    public abstract void handleMenu(InventoryClickEvent event);
    public abstract void setMenuItems();

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void open()
    {
        inventory = Bukkit.createInventory(this,getSlots(),getMenuName());
        this.setMenuItems();
        pmu.getOwner().openInventory(inventory);
    }

}
