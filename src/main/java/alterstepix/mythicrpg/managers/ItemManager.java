package alterstepix.mythicrpg.managers;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class ItemManager {
    public ItemStack LightingAxe;
    public ItemStack IdolsIncarnate;
    public ItemStack Terminator;
    public ItemStack HealingSword;
    public ItemStack FrozenWand;
    public ItemStack ImpulseSword;
    public ItemStack AmberScythe;
    public ItemStack MilkPotion;
    public ItemStack GiantSword;
    public ItemStack FuriousAxe;
    public ItemStack AirBurner;
    public ItemStack[] RuinicDagger;

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary lib;

    public ItemManager(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfiguration();
        this.lib = new ItemLoreLibrary(main);
        this.lib.Init();
    }

    public void init()
    {
        this.createLightingAxe();
        this.createIdolsIncarnate();
        this.createTerminator();
        this.createHealingSword();
        this.createFrozenWand();
        this.createImpulseSword();
        this.createAmberScythe();
        this.createMilkPotion();
        this.createGiantSword();
        this.createFuriousAxe();
        this.createAirBurner();
        this.createRunicDagger();
    }

    public void createLightingAxe()
    {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("lightingAxe")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("Thunderlord"))
            lore.add(l);
        for(String l : lib.Lore.get("LightningPower"))
            lore.add(l);

        meta.setLore(lore);
        item.setItemMeta(meta);

        LightingAxe = item;
    }
    public void createIdolsIncarnate()
    {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("idolsIncarnate")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        List<String> lore = lib.Lore.get("Curse");

        meta.setLore(lore);
        item.setItemMeta(meta);

        IdolsIncarnate = item;

    }

    public void createTerminator()
    {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("terminator")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("Termination"))
            lore.add(l);
        for(String l : lib.Lore.get("Recall"))
            lore.add(l);
        for(String l : lib.Lore.get("Annihilation"))
            lore.add(l);



        meta.setLore(lore);
        item.setItemMeta(meta);

        Terminator = item;

    }
    public void createHealingSword()
    {
        ItemStack item = new ItemStack(Material.GOLDEN_AXE, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("healingSword")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("Healing"))
            lore.add(l);



        meta.setLore(lore);
        item.setItemMeta(meta);

        HealingSword = item;

    }

    public void createFrozenWand()
    {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("frozenWand")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("FrozenBreathe"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        FrozenWand = item;
    }

    public void createImpulseSword()
    {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("impulseSword")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("Push"))
            lore.add(l);
        for(String l : lib.Lore.get("Pull"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        ImpulseSword = item;
    }
    public void createAmberScythe()
    {
        ItemStack item = new ItemStack(Material.GOLDEN_HOE, 1);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("amberScythe")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("FireFury"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        AmberScythe = item;
    }

    public void createMilkPotion()
    {
        ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);

        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("milkPotion")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("Milk"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        MilkPotion = item;
    }
    public void createGiantSword()
    {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);

        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("giantSword")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("GiantHit"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        GiantSword = item;
    }
    public void createFuriousAxe()
    {
        ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);

        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("furiousAxe")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("Throw"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        FuriousAxe = item;
    }
    public void createAirBurner()
    {
        ItemStack item = new ItemStack(Material.FLINT_AND_STEEL, 1);

        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("airBurner")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("AirBurner"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        AirBurner = item;
    }
    public void createRunicDagger()
    {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);

        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("runicDaggerFrozen")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("RunicSwap"))
            lore.add(l);
        for(String l : lib.Lore.get("RunicFrozen"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);



        ItemStack item2 = new ItemStack(Material.GOLDEN_SWORD, 1);

        ItemMeta meta2 = item.getItemMeta();

        meta2.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("runicDaggerMolten")));
        meta2.setUnbreakable(true);
        meta2.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore2 = new ArrayList<>();

        for(String l : lib.Lore.get("RunicSwap"))
            lore2.add(l);
        for(String l : lib.Lore.get("RunicMolten"))
            lore2.add(l);


        meta2.setLore(lore2);
        item2.setItemMeta(meta2);

        RuinicDagger = new ItemStack[]{item,item2};
    }

}
