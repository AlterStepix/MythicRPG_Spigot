package alterstepix.mythicrpg.managers;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
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
    public ItemStack MythicSwordofLegends;
    public ItemStack FlamingWhip;
    public ItemStack DarknessConcentrator;
    public ItemStack InfectedSword;
    public ItemStack SwordOfGrowth;
    public ItemStack Singularity;

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
        this.createMythicSwordOfLegends();
        this.createFlamingWhip();
        this.createDarknessConcentrator();
        this.createInfectedSword();
        this.createSwordOfGrowth();
        this.createSingularity();
    }

    public void createLightingAxe()
    {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1234568);

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
        meta.setCustomModelData(1234567);

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

    public void createMythicSwordOfLegends()
    {
        ItemStack item = new ItemStack(Material.GOLDEN_SWORD, 1);

        ItemMeta meta = item.getItemMeta();

        meta.addEnchant(Enchantment.DAMAGE_ALL,25,true);
        meta.addEnchant(Enchantment.FIRE_ASPECT,10,true);

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("mythicSwordOfLegends")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("MythicWeapon"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        MythicSwordofLegends = item;
    }
    public void createFlamingWhip()
    {
        ItemStack item = new ItemStack(Material.FISHING_ROD, 1);

        ItemMeta meta = item.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("flamingWhip")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("FlamingArc"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        FlamingWhip = item;
    }
    public void createDarknessConcentrator()
    {
        ItemStack item = new ItemStack(Material.FISHING_ROD, 1);

        ItemMeta meta = item.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("darknessConcentrator")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("DarknessBeam"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        DarknessConcentrator = item;
    }
    public void createInfectedSword()
    {
        ItemStack item = new ItemStack(Material.STONE_SWORD, 1);

        ItemMeta meta = item.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("infectedSword")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("InfectedBlade"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        InfectedSword = item;
    }
    public void createSwordOfGrowth()
    {
        ItemStack item = new ItemStack(Material.STONE_SWORD, 1);

        ItemMeta meta = item.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("swordOfGrowth")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("Growth"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        SwordOfGrowth = item;
    }
    public void createSingularity()
    {
        ItemStack item = new ItemStack(Material.CROSSBOW, 1);

        ItemMeta meta = item.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setDisplayName(ColorUtil.ConvertToCustom(this.config.getString("singularity")));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();

        for(String l : lib.Lore.get("Singularity"))
            lore.add(l);


        meta.setLore(lore);
        item.setItemMeta(meta);

        Singularity = item;
    }

}
