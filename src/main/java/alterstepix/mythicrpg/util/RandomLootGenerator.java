package alterstepix.mythicrpg.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class RandomLootGenerator {

    static String[] ColorCodes = new String[] {"#7153d0!","#d17554!","#a4d154!","#bf39b7!","#39bf8e!"};

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

}
