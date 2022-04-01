package alterstepix.mythicrpg.managers;

import alterstepix.mythicrpg.Mythicrpg;
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

}
