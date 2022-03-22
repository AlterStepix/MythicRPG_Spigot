package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DropTabel {

    public ItemStack amberShard;

    Mythicrpg main;
    FileConfiguration config;

    public DropTabel(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void Init()
    {
        CreateAmberShard();
    }

    private void CreateAmberShard()
    {
        ItemStack item = new ItemStack(Material.HONEYCOMB, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("AmberShardName")));
        item.setItemMeta(meta);
        amberShard = item;
    }

}
