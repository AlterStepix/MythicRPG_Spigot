package alterstepix.mythicrpg.managers;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.mobs.NetherLord;
import alterstepix.mythicrpg.util.ColorUtil;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScrollManager {

    Mythicrpg main;
    FileConfiguration config;
    ItemLoreLibrary ILL;

    public ItemStack ArrowStormScroll;
    public ItemStack NetherStormScroll;
    public ItemStack HealingTotemScroll;
    public ItemStack InfernalAuraScroll;

    public ScrollManager(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        ILL = new ItemLoreLibrary(main);
        ILL.Init();
    }

    public void init()
    {
        createArrowStormScroll();
        createNetherStormScroll();
        createHealingTotemScroll();
        createInfernalAuraScroll();
    }

    public void createArrowStormScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ArrowStormName")));
        meta.setLore(ILL.Lore.get("ArrowStorm"));
        scroll.setItemMeta(meta);

        ArrowStormScroll = scroll;
    }
    public void createNetherStormScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("NetherStormScroll")));
        meta.setLore(ILL.Lore.get("NetherStorm"));
        scroll.setItemMeta(meta);

        NetherStormScroll = scroll;
    }
    public void createHealingTotemScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("HealingTotemScroll")));
        meta.setLore(ILL.Lore.get("HealingTotem"));
        scroll.setItemMeta(meta);

        HealingTotemScroll = scroll;
    }

    public void createInfernalAuraScroll()
    {
        ItemStack scroll = new ItemStack(Material.MOJANG_BANNER_PATTERN,1);
        ItemMeta meta = scroll.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("InfernalAuraScroll")));
        meta.setLore(ILL.Lore.get("InfernalAura"));
        scroll.setItemMeta(meta);

        InfernalAuraScroll = scroll;
    }



}
