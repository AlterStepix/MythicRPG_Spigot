package alterstepix.mythicrpg.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class RandomLootGenerator {

    static Material[] leatherArmor = new Material[] {Material.LEATHER_BOOTS, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET};
    static Material[] chainArmor = new Material[] {Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET};
    static Material[] goldenArmor = new Material[] {Material.GOLDEN_BOOTS, Material.GOLDEN_LEGGINGS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET};
    static Material[] ironArmor = new Material[] {Material.IRON_BOOTS, Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.IRON_HELMET};
    static Material[] diamondArmor = new Material[] {Material.DIAMOND_BOOTS, Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET};
    static Material[] netheriteArmor = new Material[] {Material.NETHERITE_BOOTS, Material.NETHERITE_LEGGINGS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET};

    static String[] ColorCodes = new String[] {"#7153d0!","#d17554!","#a4d154!","#bf39b7!","#39bf8e!","#7b5cae!","#ad9a5c!","#5ca9ad!","#ff0000!","#361414!","#467048!","#69ef6f!"};

    static String[] ArmorPrefixes = new String[] {"Clean","Reinforced","Shiny","Warrior's","Rare","Uncommon"};
    static String[] SwordPrefixes = new String[] {"Relentless","Shiny","Warrior's","Assassin's","Nether","Epic","Legendary","Furious","Blind","Strong","Lost","Ancient","Huge","Tiny","Astral","Emerald",
            "Emperor's","Ethereal","True","Special"};

    static String[] ArmorHelmetNames = new String[] {"Helmet"};
    static String[] ArmorChestplateNames = new String[] {"Battle Chestplate","Chestplate","Shell"};
    static String[] ArmorLeggingsNames = new String[] {"Leggings","Pants"};
    static String[] ArmorBootsNames = new String[] {"Boots","Shoes"};
    static String[] SwordNames = new String[] {"Cleaver","Sword","Longsword","Knife","Dagger","Katana","Blade","Saber"};

    static String[] SwordPostfixes = new String[] {"of Darkness", "of Light", "of Flame", "of Nothing", "of Power", "of Dictatorship", "of Fate", "of Peace", "of Water", "of Exasperation", "of Freedom", "of Demons","of Void", "of Undead",
            "of Fear", "of Illusion", "of Death", "of Runes", "of Eternity", "of Winter", "of Air", "of Hell", "of Heaven", "of Courage"};

    static String[] ArmorPostfixes = new String[] {"of Darkness", "of Light", "of Flame", "of Nothing", "of Power", "of Dictatorship", "of Fate", "of Peace", "of Water", "of Exasperation", "of Freedom", "of Demons","of Void", "of Undead",
            "of Fear", "of Illusion", "of Death", "of Runes", "of Eternity", "of Winter", "of Air", "of Hell", "of Heaven", "of Courage"};


    public static ItemStack getLootSword(int level) {
        ItemStack item;

        String name = "";

        Random rand = new Random();

        int Sw_Pr = (int)Math.floor(Math.random() * SwordPrefixes.length);
        int Sw_Na = (int)Math.floor(Math.random() * SwordNames.length);
        int Sw_Po = (int)Math.floor(Math.random() * SwordPostfixes.length);
        int ColorCode = (int)Math.floor(Math.random() * ColorCodes.length);

        name = name + SwordPrefixes[Sw_Pr] +" "+ SwordNames[Sw_Na] +" "+ SwordPostfixes[Sw_Po];
        name = ColorUtil.ConvertToCustom(ColorCodes[ColorCode]+name);

        if (level < 17)
        {
            item = new ItemStack(Material.WOODEN_SWORD, 1);
        }
        else if(level < 34)
        {
            item = new ItemStack(Material.STONE_SWORD, 1);
        }
        else if(level < 51)
        {
            item = new ItemStack(Material.GOLDEN_SWORD, 1);
        }
        else if(level < 68)
        {
            item = new ItemStack(Material.IRON_SWORD, 1);
        }
        else if(level < 85)
        {
            item = new ItemStack(Material.DIAMOND_SWORD, 1);
        }
        else
        {
            item = new ItemStack(Material.NETHERITE_SWORD, 1);
        }

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§7Level: "+level);

        if(Math.random() < 0.01*level)
        {
            meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
            lore.add("§6Fire-Infused");
        }

        meta.addEnchant(Enchantment.DAMAGE_ALL, (level%255)/6,true);
        lore.add("§3Sharpness: "+ ((level%255)/6));

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getLootArmor(int level) {
        ItemStack item;

        String name = "";

        Random rand = new Random();

        int Prefix = (int)Math.floor(Math.random() * ArmorPrefixes.length);
        int Name = 0;
        int Postfix = (int)Math.floor(Math.random() * ArmorPostfixes.length);
        int ColorCode = (int)Math.floor(Math.random() * ColorCodes.length);


        if (level < 17)
        {
            int component = (int)Math.floor(Math.random() * leatherArmor.length);
            item = new ItemStack(leatherArmor[component], 1);

        }
        else if(level < 34)
        {
            int component = (int)Math.floor(Math.random() * chainArmor.length);
            item = new ItemStack(chainArmor[component], 1);
        }
        else if(level < 51)
        {
            int component = (int)Math.floor(Math.random() * goldenArmor.length);
            item = new ItemStack(goldenArmor[component], 1);
        }
        else if(level < 68)
        {
            int component = (int)Math.floor(Math.random() * ironArmor.length);
            item = new ItemStack(ironArmor[component], 1);
        }
        else if(level < 85)
        {
            int component = (int)Math.floor(Math.random() * diamondArmor.length);
            item = new ItemStack(diamondArmor[component], 1);
        }
        else
        {
            int component = (int)Math.floor(Math.random() * netheriteArmor.length);
            item = new ItemStack(netheriteArmor[component], 1);
        }

        String FinalName = "";
        if(item.getType() == Material.LEATHER_BOOTS || item.getType() == Material.CHAINMAIL_BOOTS || item.getType() == Material.GOLDEN_BOOTS || item.getType() == Material.IRON_BOOTS || item.getType() == Material.DIAMOND_BOOTS || item.getType() == Material.NETHERITE_BOOTS)
        {
            Name = (int)Math.floor(Math.random() * ArmorBootsNames.length);
            FinalName = ArmorBootsNames[Name];
        }
        if(item.getType() == Material.LEATHER_LEGGINGS || item.getType() == Material.CHAINMAIL_LEGGINGS || item.getType() == Material.GOLDEN_LEGGINGS || item.getType() == Material.IRON_LEGGINGS || item.getType() == Material.DIAMOND_LEGGINGS || item.getType() == Material.NETHERITE_LEGGINGS)
        {
            Name = (int)Math.floor(Math.random() * ArmorLeggingsNames.length);
            FinalName = ArmorLeggingsNames[Name];
        }
        if(item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.CHAINMAIL_CHESTPLATE || item.getType() == Material.GOLDEN_CHESTPLATE || item.getType() == Material.IRON_CHESTPLATE || item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.NETHERITE_CHESTPLATE)
        {
            Name = (int)Math.floor(Math.random() * ArmorChestplateNames.length);
            FinalName = ArmorChestplateNames[Name];
        }
        if(item.getType() == Material.LEATHER_HELMET || item.getType() == Material.CHAINMAIL_HELMET || item.getType() == Material.GOLDEN_HELMET || item.getType() == Material.IRON_HELMET || item.getType() == Material.DIAMOND_HELMET || item.getType() == Material.NETHERITE_HELMET)
        {
            Name = (int)Math.floor(Math.random() * ArmorHelmetNames.length);
            FinalName = ArmorHelmetNames[Name];
        }

        name = name + ArmorPrefixes[Prefix] +" "+ FinalName +" "+ ArmorPostfixes[Postfix];
        name = ColorUtil.ConvertToCustom(ColorCodes[ColorCode]+name);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("§7Level: "+level);

        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, (level%255)/8,true);
        lore.add("§3Protection: "+ ((level%255)/8));

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

}
