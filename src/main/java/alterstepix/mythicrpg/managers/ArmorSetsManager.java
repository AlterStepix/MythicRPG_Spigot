package alterstepix.mythicrpg.managers;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.GetPlayerHead;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArmorSetsManager {

    FileConfiguration config;
    Mythicrpg main;
    ItemLoreLibrary lib;

    public ItemStack[] GrandmasterArmorSet = new ItemStack[4];
    public ItemStack[] MasterAssasinArmorSet = new ItemStack[3];
    public ItemStack[] FrozenWarriorArmorSet = new ItemStack[4];
    public ItemStack[] MythicWarriorArmorset = new ItemStack[4];
    public ItemStack[] ThiefArmorset = new ItemStack[4];
    public ItemStack[] BeastArmorset = new ItemStack[2];
    public ItemStack[] CorruptedMythicArmor = new ItemStack[4];
    public ItemStack[] GlacialMythicArmor = new ItemStack[4];

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
        createMasterAssasinArmorSet();
        createFrozenWarriorArmorSet();
        createMythicWarriorArmorset();
        createThiefArmorset();
        createBeastArmorset();
        createCorruptedMythicWarriorArmorset();
        createGlacialMythicWarriorArmorset();
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
        ItemMeta metaL = leggings.getItemMeta();

        metaL.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,8,true);

        metaL.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GrandmasterLeggingsName")));

        metaL.setLore(lore);
        metaL.setUnbreakable(true);

        leggings.setItemMeta(metaL);

        //

        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta metaC = chestplate.getItemMeta();

        metaC.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,9,true);

        metaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GrandmasterChestplateName")));

        metaC.setLore(lore);
        metaC.setUnbreakable(true);

        chestplate.setItemMeta(metaC);

        //

        ItemStack helmet = GetPlayerHead.GetCustomHead(GetPlayerHead.GrandmasterHelmet);
        ItemMeta metaH = helmet.getItemMeta();

        metaH.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,7,true);

        metaH.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GrandmasterHelmetName")));

        metaH.setLore(lore);
        metaH.setUnbreakable(true);

        helmet.setItemMeta(metaH);

        GrandmasterArmorSet[0] = boots;
        GrandmasterArmorSet[1] = leggings;
        GrandmasterArmorSet[2] = chestplate;
        GrandmasterArmorSet[3] = helmet;

    }

    public void createMasterAssasinArmorSet()
    {
        List<String> lore = new ArrayList<String>();
        for(String l : lib.Lore.get("MAA"))
            lore.add(l);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta colmetaB = (LeatherArmorMeta) boots.getItemMeta();

        colmetaB.setColor(Color.BLACK);

        colmetaB.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,6,true);

        colmetaB.setDisplayName(ColorUtil.ConvertToCustom(config.getString("MasterAssassinBootsName")));

        colmetaB.setLore(lore);
        colmetaB.setUnbreakable(true);

        boots.setItemMeta(colmetaB);

        //

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta colmetaL = (LeatherArmorMeta) leggings.getItemMeta();

        colmetaL.setColor(Color.BLACK);

        colmetaL.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,6,true);

        colmetaL.setDisplayName(ColorUtil.ConvertToCustom(config.getString("MasterAssassinLeggingsName")));

        colmetaL.setLore(lore);
        colmetaL.setUnbreakable(true);

        leggings.setItemMeta(colmetaL);

        //

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta metaC = chestplate.getItemMeta();

        metaC.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,5,true);
        metaC.addEnchant(Enchantment.PROTECTION_PROJECTILE,5,true);


        metaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("MasterAssassinChestplateName")));

        metaC.setLore(lore);
        metaC.setUnbreakable(true);

        chestplate.setItemMeta(metaC);



        MasterAssasinArmorSet[0] = boots;
        MasterAssasinArmorSet[1] = leggings;
        MasterAssasinArmorSet[2] = chestplate;

    }

    public void createFrozenWarriorArmorSet()
    {
        List<String> lore = new ArrayList<String>();

        for(String l : lib.Lore.get("FWA"))
            lore.add(l);

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta colmetaB = (LeatherArmorMeta) boots.getItemMeta();

        colmetaB.setColor(Color.fromRGB(173,204 ,255));

        colmetaB.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        colmetaB.setDisplayName(ColorUtil.ConvertToCustom(config.getString("FrozenWarriorBootsName")));


        colmetaB.setLore(lore);
        colmetaB.setUnbreakable(true);

        boots.setItemMeta(colmetaB);

        //

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta colmetaL = (LeatherArmorMeta) leggings.getItemMeta();

        colmetaL.setColor(Color.fromRGB(173,204 ,255));

        colmetaL.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        colmetaL.setDisplayName(ColorUtil.ConvertToCustom(config.getString("FrozenWarriorLeggingsName")));

        colmetaL.setLore(lore);
        colmetaL.setUnbreakable(true);

        leggings.setItemMeta(colmetaL);

        //

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta colmetaC = (LeatherArmorMeta) chestplate.getItemMeta();

        colmetaC.setColor(Color.fromRGB(173,204 ,255));

        colmetaC.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        colmetaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("FrozenWarriorChestplateName")));

        colmetaC.setLore(lore);
        colmetaC.setUnbreakable(true);

        chestplate.setItemMeta(colmetaC);

        //

        ItemStack helmet = GetPlayerHead.GetCustomHead(GetPlayerHead.FrozenWarriorHelmet);
        ItemMeta metaH = helmet.getItemMeta();


        metaH.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        metaH.setDisplayName(ColorUtil.ConvertToCustom(config.getString("FrozenWarriorHelmetName")));

        metaH.setLore(lore);
        metaH.setUnbreakable(true);

        helmet.setItemMeta(metaH);

        FrozenWarriorArmorSet[0] = boots;
        FrozenWarriorArmorSet[1] = leggings;
        FrozenWarriorArmorSet[2] = chestplate;
        FrozenWarriorArmorSet[3] = helmet;

    }

    public void createMythicWarriorArmorset()
    {
        List<String> lore = new ArrayList<String>();
        for(String l : lib.Lore.get("MWA"))
            lore.add(l);



        ItemStack boots = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta metaB = boots.getItemMeta();

        metaB.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        metaB.setDisplayName(ColorUtil.ConvertToCustom(config.getString("MythicWarriorBootsName")));

        metaB.setLore(lore);
        metaB.setUnbreakable(true);

        boots.setItemMeta(metaB);

        //

        ItemStack leggings = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta metaL = leggings.getItemMeta();

        metaL.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        metaL.setDisplayName(ColorUtil.ConvertToCustom(config.getString("MythicWarriorLeggingsName")));

        metaL.setLore(lore);
        metaL.setUnbreakable(true);

        leggings.setItemMeta(metaL);

        //

        ItemStack chestplate = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta metaC = chestplate.getItemMeta();

        metaC.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        metaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("MythicWarriorChestplateName")));

        metaC.setLore(lore);
        metaC.setUnbreakable(true);

        chestplate.setItemMeta(metaC);

        //

        ItemStack helmet = GetPlayerHead.GetCustomHead(GetPlayerHead.MythicWarriorHelmet);
        ItemMeta metaH = helmet.getItemMeta();

        metaH.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        metaH.setDisplayName(ColorUtil.ConvertToCustom(config.getString("MythicWarriorHelmetName")));

        metaH.setLore(lore);
        metaH.setUnbreakable(true);

        helmet.setItemMeta(metaH);

        MythicWarriorArmorset[0] = boots;
        MythicWarriorArmorset[1] = leggings;
        MythicWarriorArmorset[2] = chestplate;
        MythicWarriorArmorset[3] = helmet;

    }


    public void createThiefArmorset()
    {
        List<String> lore = new ArrayList<String>();
        for(String l : lib.Lore.get("TA"))
            lore.add(l);


        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta metaB = boots.getItemMeta();

        AttributeModifier modifierB = new AttributeModifier(UUID.randomUUID(),"mrpg",0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);

        metaB.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,modifierB);

        metaB.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ThiefBootsName")));

        metaB.setLore(lore);
        metaB.setUnbreakable(true);

        boots.setItemMeta(metaB);

        //

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta metaL = leggings.getItemMeta();

        AttributeModifier modifierL = new AttributeModifier(UUID.randomUUID(),"mrpg",0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);

        metaL.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,modifierL);

        metaL.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ThiefLeggingsName")));

        metaL.setLore(lore);
        metaL.setUnbreakable(true);

        leggings.setItemMeta(metaL);

        //

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta metaC = chestplate.getItemMeta();

        AttributeModifier modifierC = new AttributeModifier(UUID.randomUUID(),"mrpg",0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);

        metaC.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,modifierC);

        metaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ThiefChestplateName")));

        metaC.setLore(lore);
        metaC.setUnbreakable(true);

        chestplate.setItemMeta(metaC);

        //

        ItemStack helmet = GetPlayerHead.GetCustomHead(GetPlayerHead.ThiefHood);
        ItemMeta metaH = helmet.getItemMeta();

        metaH.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        metaH.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ThiefHelmetName")));

        AttributeModifier modifierH = new AttributeModifier(UUID.randomUUID(),"mrpg",0.04, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);

        metaH.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,modifierH);

        metaH.setLore(lore);
        metaH.setUnbreakable(true);

        helmet.setItemMeta(metaH);

        ThiefArmorset[0] = boots;
        ThiefArmorset[1] = leggings;
        ThiefArmorset[2] = chestplate;
        ThiefArmorset[3] = helmet;

    }

    public void createBeastArmorset()
    {
        List<String> lore = new ArrayList<String>();
        for(String l : lib.Lore.get("BA"))
            lore.add(l);



        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta metaC = (LeatherArmorMeta) chestplate.getItemMeta();

        AttributeModifier modifierC = new AttributeModifier(UUID.randomUUID(),"mrpg",0.03, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        AttributeModifier modifierC_A = new AttributeModifier(UUID.randomUUID(),"mrpg",0.07, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);

        metaC.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,modifierC);
        metaC.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,modifierC_A);

        metaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("BeastChestplateName")));

        metaC.setLore(lore);
        metaC.setUnbreakable(true);

        metaC.setColor(Color.fromRGB(214,214,214));

        chestplate.setItemMeta(metaC);

        //

        ItemStack helmet = GetPlayerHead.GetCustomHead(GetPlayerHead.BeastHelmet);
        ItemMeta metaH = helmet.getItemMeta();

        metaH.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,10,true);

        metaH.setDisplayName(ColorUtil.ConvertToCustom(config.getString("BeastHelmetName")));

        AttributeModifier modifierH = new AttributeModifier(UUID.randomUUID(),"mrpg",0.03, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        AttributeModifier modifierH_A = new AttributeModifier(UUID.randomUUID(),"mrpg",0.07, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);

        metaH.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,modifierH);
        metaH.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,modifierH_A);

        metaH.setLore(lore);
        metaH.setUnbreakable(true);

        helmet.setItemMeta(metaH);

        BeastArmorset[0] = chestplate;
        BeastArmorset[1] = helmet;

    }

    public void createCorruptedMythicWarriorArmorset()
    {
        List<String> lore = new ArrayList<String>();
        for(String l : lib.Lore.get("CMWA"))
            lore.add(l);



        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta metaB = (LeatherArmorMeta) boots.getItemMeta();
        metaB.setColor(Color.fromRGB(98,42,163));

        AttributeModifier modifierB = new AttributeModifier(UUID.randomUUID(),"mrpg",30, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        metaB.addAttributeModifier(Attribute.GENERIC_ARMOR,modifierB);


        metaB.setDisplayName(ColorUtil.ConvertToCustom(config.getString("CorruptedMythicWarriorBootsName")));

        metaB.setLore(lore);
        metaB.setUnbreakable(true);

        boots.setItemMeta(metaB);

        //

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta metaL = (LeatherArmorMeta) leggings.getItemMeta();
        metaL.setColor(Color.fromRGB(98,42,163));

        AttributeModifier modifierL = new AttributeModifier(UUID.randomUUID(),"mrpg",30, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        metaL.addAttributeModifier(Attribute.GENERIC_ARMOR,modifierL);

        metaL.setDisplayName(ColorUtil.ConvertToCustom(config.getString("CorruptedMythicWarriorLeggingsName")));

        metaL.setLore(lore);
        metaL.setUnbreakable(true);

        leggings.setItemMeta(metaL);

        //

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta metaC = (LeatherArmorMeta) chestplate.getItemMeta();
        metaC.setColor(Color.fromRGB(98,42,163));

        AttributeModifier modifierC = new AttributeModifier(UUID.randomUUID(),"mrpg",30, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        metaC.addAttributeModifier(Attribute.GENERIC_ARMOR,modifierC);

        metaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("CorruptedMythicWarriorChestplateName")));

        metaC.setLore(lore);
        metaC.setUnbreakable(true);

        chestplate.setItemMeta(metaC);

        //

        ItemStack helmet = GetPlayerHead.GetCustomHead(GetPlayerHead.CorruptedMythicHelmet);
        ItemMeta metaH = helmet.getItemMeta();

        AttributeModifier modifierH = new AttributeModifier(UUID.randomUUID(),"mrpg",30, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        metaH.addAttributeModifier(Attribute.GENERIC_ARMOR,modifierH);

        metaH.setDisplayName(ColorUtil.ConvertToCustom(config.getString("CorruptedMythicWarriorHelmetName")));

        metaH.setLore(lore);
        metaH.setUnbreakable(true);

        helmet.setItemMeta(metaH);

        CorruptedMythicArmor[0] = boots;
        CorruptedMythicArmor[1] = leggings;
        CorruptedMythicArmor[2] = chestplate;
        CorruptedMythicArmor[3] = helmet;

    }
    public void createGlacialMythicWarriorArmorset()
    {
        List<String> lore = new ArrayList<String>();
        for(String l : lib.Lore.get("GMWA"))
            lore.add(l);



        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta metaB = (LeatherArmorMeta) boots.getItemMeta();
        metaB.setColor(Color.fromRGB(174,240,255));

        AttributeModifier modifierB = new AttributeModifier(UUID.randomUUID(),"mrpg",50, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        metaB.addAttributeModifier(Attribute.GENERIC_ARMOR,modifierB);


        metaB.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GlacialMythicWarriorBootsName")));

        metaB.setLore(lore);
        metaB.setUnbreakable(true);

        boots.setItemMeta(metaB);

        //

        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta metaL = (LeatherArmorMeta) leggings.getItemMeta();
        metaL.setColor(Color.fromRGB(174,240,255));

        AttributeModifier modifierL = new AttributeModifier(UUID.randomUUID(),"mrpg",70, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        metaL.addAttributeModifier(Attribute.GENERIC_ARMOR,modifierL);

        metaL.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GlacialMythicWarriorLeggingsName")));

        metaL.setLore(lore);
        metaL.setUnbreakable(true);

        leggings.setItemMeta(metaL);

        //

        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta metaC = (LeatherArmorMeta) chestplate.getItemMeta();
        metaC.setColor(Color.fromRGB(136,233,255));

        AttributeModifier modifierC = new AttributeModifier(UUID.randomUUID(),"mrpg",90, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        metaC.addAttributeModifier(Attribute.GENERIC_ARMOR,modifierC);

        metaC.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GlacialMythicWarriorChestplateName")));

        metaC.setLore(lore);
        metaC.setUnbreakable(true);

        chestplate.setItemMeta(metaC);

        //

        ItemStack helmet = GetPlayerHead.GetCustomHead(GetPlayerHead.GlacialMythicHelmet);
        ItemMeta metaH = helmet.getItemMeta();

        AttributeModifier modifierH = new AttributeModifier(UUID.randomUUID(),"mrpg",50, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        metaH.addAttributeModifier(Attribute.GENERIC_ARMOR,modifierH);

        metaH.setDisplayName(ColorUtil.ConvertToCustom(config.getString("GlacialMythicWarriorHelmetName")));

        metaH.setLore(lore);
        metaH.setUnbreakable(true);

        helmet.setItemMeta(metaH);

        GlacialMythicArmor[0] = boots;
        GlacialMythicArmor[1] = leggings;
        GlacialMythicArmor[2] = chestplate;
        GlacialMythicArmor[3] = helmet;

    }
}
