package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack LightingAxe;
    public static ItemStack IdolsIncarnate;
    static Mythicrpg main;

    public void init(Mythicrpg main)
    {
        this.main = main;
    }

    public static void createLightingAxe()
    {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(main.getConfig().getString("lightning_axe_name"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("§6RIGHT CLICK: §eThunderlord");
        lore.add("§7Strikes all nearby enemies with lightning");

        meta.setLore(lore);
        item.setItemMeta(meta);

        LightingAxe = item;

    }
    public static void createIdolsIncarnate()
    {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Idols Incarnate");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD+"EpicLore");
        lore.add(ChatColor.YELLOW+"Gives you some speed");

        meta.setLore(lore);
        item.setItemMeta(meta);

        IdolsIncarnate = item;

    }

}
