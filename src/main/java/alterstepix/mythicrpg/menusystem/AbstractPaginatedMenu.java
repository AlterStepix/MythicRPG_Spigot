package alterstepix.mythicrpg.menusystem;

import alterstepix.mythicrpg.util.PMU;
import org.bukkit.Material;

public abstract class AbstractPaginatedMenu extends AbstractMenu{
    protected int page = 0;
    protected int maxItemsPerPage = 28;
    protected int index = 0;

    public AbstractPaginatedMenu(PMU pmu) {
        super(pmu);
    }

    public void addMenuBorder(){
        inventory.setItem(48, MenuItemWrapper.item(Material.ARROW, "§bLeft","§bCurrent Page: "+(page+1)));

        inventory.setItem(49, MenuItemWrapper.item(Material.BARRIER, "§cClose"));

        inventory.setItem(50, MenuItemWrapper.item(Material.ARROW, "§bRight","§bCurrent Page: "+(page+1)));

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }

        inventory.setItem(17, super.FILLER_GLASS);
        inventory.setItem(18, super.FILLER_GLASS);
        inventory.setItem(26, super.FILLER_GLASS);
        inventory.setItem(27, super.FILLER_GLASS);
        inventory.setItem(35, super.FILLER_GLASS);
        inventory.setItem(36, super.FILLER_GLASS);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

}
