package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

//Item creation step by step:
// 1. Add ItemStack to ItemManager
// 2. Add settings to config
// 3. Add ItemStack to GetMythicItems command
// 4. Add item lore to AppendAbilityLore command
// 5. Add new event handlers to itemabilities package


public class ItemManager {
    public static ItemStack LightingAxe;
    public static ItemStack IdolsIncarnate;
    public static ItemStack Terminator;
    public static ItemStack HealingSword;
    public static ItemStack FrozenWand;
    public static ItemStack ImpulseSword;

    Mythicrpg main;
    FileConfiguration config;

    public ItemManager(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfiguration();
    }

    public void init()
    {
        this.createLightingAxe();
        this.createIdolsIncarnate();
        this.createTerminator();
        this.createHealingSword();
        this.createFrozenWand();
        this.createImpulseSword();
    }

    public void createLightingAxe()
    {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(this.config.getString("lightingAxe"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        int cooldown = this.config.getInt("lightningAxeCooldown");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§6RIGHT CLICK: §eThunderlord");
        lore.add("§7Strikes all nearby enemies with lightning");
        lore.add("§8Cooldown: "+ cooldown + "s");
        lore.add("");
        lore.add("§6ITEM ABILITY: §eLightning Power");
        lore.add("§7Strikes your enemies with lightning on hit");

        meta.setLore(lore);
        item.setItemMeta(meta);

        LightingAxe = item;
    }
    public void createIdolsIncarnate()
    {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(this.config.getString("idolsIncarnate"));
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

    public void createTerminator()
    {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        int radius = this.config.getInt("terminatorAbilityRange");
        meta.setDisplayName(this.config.getString("terminator"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§6LEFT CLICK: §eTermination");
        lore.add("§7Instantly shoots 3 arrow");
        lore.add("");
        lore.add("§6SNEAK + LEFT CLICK: §eRecall");
        lore.add("§7Sends all arrows in a "+radius+" blocks radius to the sky");
        lore.add("");
        lore.add("§6KEYBOARD F: §eAnnihilation");
        lore.add("§7Explodes all arrows in a "+radius+" blocks radius");


        meta.setLore(lore);
        item.setItemMeta(meta);

        Terminator = item;

    }
    public void createHealingSword()
    {
        ItemStack item = new ItemStack(Material.GOLDEN_AXE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(this.config.getString("healingSword"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§6RIGHT CLICK: §eHealing");
        lore.add("§7Heals you in exchange for hunger");


        meta.setLore(lore);
        item.setItemMeta(meta);

        HealingSword = item;

    }

    public void createFrozenWand()
    {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(this.config.getString("frozenWand"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        int r = config.getInt("frozenWandRadius");
        int cooldown = config.getInt("frozenWandCooldown");
        lore.add("");
        lore.add("§6RIGHT CLICK: §eFrozen Breathe");
        lore.add("§7Debuffs your enemies in a "+r+" block radius");
        lore.add("§8Cooldown: "+ cooldown + "s");

        meta.setLore(lore);
        item.setItemMeta(meta);

        FrozenWand = item;
    }

    public void createImpulseSword()
    {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(this.config.getString("impulseSword"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        int r = config.getInt("impulseSwordRadius");
        int cooldown = config.getInt("impulseSwordCooldown");
        lore.add("");
        lore.add("§6SNEAK + RIGHT CLICK: §ePush");
        lore.add("§7Pushes your enemies from you in a "+r+" block radius");
        lore.add("§8Cooldown: "+ cooldown + "s");
        lore.add("");
        lore.add("§6RIGHT CLICK: §ePull");
        lore.add("§7Pulls your enemies towards you in a "+r+" block radius");
        lore.add("§8Cooldown: "+ cooldown + "s");

        meta.setLore(lore);
        item.setItemMeta(meta);

        ImpulseSword = item;
    }

}
