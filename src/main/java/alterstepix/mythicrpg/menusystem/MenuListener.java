package alterstepix.mythicrpg.menusystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;


public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e)
    {
        Player player = (Player) e.getWhoClicked();

        InventoryHolder holder = e.getClickedInventory().getHolder();

        if(holder instanceof AbstractMenu menu)
        {
            e.setCancelled(true);

            if(e.getCurrentItem() == null)
                return;

            menu.handleMenu(e);
        }
    }
}
