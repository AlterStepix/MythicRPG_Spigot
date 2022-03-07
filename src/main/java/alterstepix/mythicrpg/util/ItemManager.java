package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack LightingAxe;
    public static ItemStack IdolsIncarnate;
    public static ItemStack Terminator;
    public static ItemStack HealingSword;

    public static void init()
    {
        createLightingAxe();
        createIdolsIncarnate();
        createTerminator();
        createHealingSword();
    }

    public static void createLightingAxe()
    {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§9Lighting Axe");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§6RIGHT CLICK: §eThunderlord");
        lore.add("§7Strikes all nearby enemies with lightning");
        lore.add("§9Cooldown: 3s");
        lore.add("");
        lore.add("§6ITEM ABILITY: §eLightning Power");
        lore.add("§7Strikes your enemies with lightning on hit");

        meta.setLore(lore);
        item.setItemMeta(meta);

        LightingAxe = item;
    }
    public static void createIdolsIncarnate()
    {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§cIdols Incarnate");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§6ITEM ABILITY: §eCurse");
        lore.add("§7Applies strong debuffs to your enemies on hit");

        meta.setLore(lore);
        item.setItemMeta(meta);

        IdolsIncarnate = item;

    }

    public static void createTerminator()
    {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§dTerminator");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§6LEFT CLICK: §eTermination");
        lore.add("§7Instantly shoots 3 arrow");
        lore.add("");
        lore.add("§6SNEAK + LEFT CLICK: §eRecall");
        lore.add("§7Sends all arrows in a 12x12x12 radius to the sky");
        lore.add("");
        lore.add("§6KEYBOARD F: §eAnnihilation");
        lore.add("§7Explodes all arrows in a 12x12x12 radius");



        meta.setLore(lore);
        item.setItemMeta(meta);

        Terminator = item;

    }
    public static void createHealingSword()
    {
        ItemStack item = new ItemStack(Material.GOLDEN_AXE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§dHealing Sword");
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§6RIGHT CLICK: §eHealing");
        lore.add("§7Applies strong errors to your console on hit\"");


        meta.setLore(lore);
        item.setItemMeta(meta);

        HealingSword = item;

    }


}
