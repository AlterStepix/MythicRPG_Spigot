package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.guis.BestiaryMenu;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BestiaryBook implements Listener {
    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;
    public BestiaryBook(Mythicrpg main)
    {
        this.main = main;
        this.config =main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();

            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Bestiary").get(1))) {
                BestiaryMenu menu = new BestiaryMenu(Mythicrpg.getPMU(player));
                menu.open();
            }
        }
    }
}
