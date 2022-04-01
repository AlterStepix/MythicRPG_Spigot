package alterstepix.mythicrpg.managers;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ArmorSetsManager {

    FileConfiguration config;
    Mythicrpg main;
    ItemLoreLibrary lib;

    public ItemStack[] GrandmasterArmorSet = new ItemStack[4];

    public ArmorSetsManager(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    public void init()
    {
        createGrandmasterArmorSet();
    }

    public void createGrandmasterArmorSet()
    {
        List<String> lore = new ArrayList<String>();
        for(String l : lib.Lore.get("GMA"))
            lore.add(l);



        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta metaB = boots.getItemMeta();

        metaB.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,8,true);

        metaB.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GrandmasterBootsName")));

        metaB.setLore(lore);
        metaB.setUnbreakable(true);

        boots.setItemMeta(metaB);

        //

        ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta metaL = boots.getItemMeta();

        metaL.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,8,true);

        metaL.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GrandmasterLeggingsName")));

        metaL.setLore(lore);
        metaL.setUnbreakable(true);

        leggings.setItemMeta(metaL);

        //

        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta metaC = boots.getItemMeta();

        metaC.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,9,true);

        metaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GrandmasterChestplateName")));

        metaC.setLore(lore);
        metaC.setUnbreakable(true);

        chestplate.setItemMeta(metaC);

        //

        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta metaH = boots.getItemMeta();

        metaH.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,7,true);

        metaH.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GrandmasterHelmetName")));

        metaH.setLore(lore);
        metaH.setUnbreakable(true);

        helmet.setItemMeta(metaL);

        GrandmasterArmorSet[0] = boots;
        GrandmasterArmorSet[1] = leggings;
        GrandmasterArmorSet[2] = chestplate;
        GrandmasterArmorSet[3] = helmet;

    }
}
