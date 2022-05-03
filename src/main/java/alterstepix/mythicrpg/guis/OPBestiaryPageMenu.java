package alterstepix.mythicrpg.guis;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.menusystem.AbstractMenu;
import alterstepix.mythicrpg.menusystem.MenuItemWrapper;
import alterstepix.mythicrpg.util.PMU;
import alterstepix.mythicrpg.util.generateLoot;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class OPBestiaryPageMenu extends AbstractMenu {
    ItemStack page;
    public OPBestiaryPageMenu(PMU pmu, ItemStack page) {
        super(pmu);
        this.page = page;
    }

    @Override
    public String getMenuName() {
        return "Page options §c(Operator Ver.)";
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
                ItemStack newpage = page;
                ItemMeta meta = newpage.getItemMeta();
                List<String> nm = meta.getLore();
                nm.remove(nm.size()-1);
                nm.remove(nm.size()-1);
                meta.setLore(nm);
                newpage.setItemMeta(meta);

                player.getInventory().addItem(newpage);
            }
            case 6 -> {
                player.closeInventory();
            }
            case 2 -> {
                generateLoot loot = new generateLoot(Mythicrpg.INSTANCE);

                for(ItemStack item :loot.getLoot(page.getItemMeta().getDisplayName()))
                {
                    player.getInventory().addItem(item);
                }
            }
            case 5 -> {
                TextComponent text = new TextComponent("");
                text.setText("/summonmythicmob "+ (ChatColor.stripColor(page.getItemMeta().getDisplayName()).replace(" ","")));
                text.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, ("/summonmythicmob "+ ChatColor.stripColor(page.getItemMeta().getDisplayName()).replace(" ",""))));
                text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to copy").create()));
                text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, ""));
                player.spigot().sendMessage(text);
            }
            default -> {

            }
        }
    }

    @Override
    public void setMenuItems() {
        inventory.setItem(4,page);
        inventory.setItem(3, MenuItemWrapper.item(Material.CHEST,"§aTake the page", "§eClick add this page to your inventory"));
        inventory.setItem(2, MenuItemWrapper.item(Material.ROTTEN_FLESH,"§aGenerate loot", "§eClick to get randomized mob drops"));
        inventory.setItem(6,MenuItemWrapper.item(Material.BARRIER, "§cClose", "§eClick to close the menu"));
        inventory.setItem(5,MenuItemWrapper.item(Material.SPAWNER, "§aSummon Command", "§eClick to get the command that summons this mob"));
    }
}
