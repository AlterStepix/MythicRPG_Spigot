package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DropTable {

    public ItemStack amberShard; //Fire Spirit
    public ItemStack impulseShard; // Air Spirit
    public ItemStack frozenShard; // Ice Spirit
    public ItemStack infectedFlesh; // Infected Zombie
    public ItemStack infectedHeart; // Infected Zombie
    public ItemStack NONE_1; // Master Assassin
    public ItemStack parasiteHeart; // Parasite
    public ItemStack lightningShard; // SemiIdol
    public ItemStack witheredEye; // Wither Spider

    Mythicrpg main;
    FileConfiguration config;

    public DropTable(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    public void init()
    {
        CreateAmberShard();
        CreateImpulseShard();
        CreateFrozenShard();
        CreateInfectedFlesh();
        CreateInfectedHeart();
        CreateParasiteHeart();
        CreateLightingShard();
        CreateWitheredEye();
    }

    private void CreateAmberShard()
    {
        ItemStack item = new ItemStack(Material.HONEYCOMB, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("AmberShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        amberShard = item;
    }
    private void CreateImpulseShard()
    {
        ItemStack item = new ItemStack(Material.FEATHER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ImpulseShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        impulseShard = item;
    }
    private void CreateFrozenShard()
    {
        ItemStack item = new ItemStack(Material.DIAMOND, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("FrozenShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        frozenShard = item;
    }
    private void CreateInfectedFlesh()
    {
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("InfectedFleshName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        infectedFlesh = item;
    }
    private void CreateInfectedHeart()
    {
        ItemStack item = new ItemStack(Material.BEETROOT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("InfectedHeartName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("RareRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        infectedHeart = item;
    }
    private void CreateParasiteHeart()
    {
        ItemStack item = new ItemStack(Material.GREEN_DYE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("ParasiteHeartName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("RareRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        parasiteHeart = item;
    }
    private void CreateLightingShard()
    {
        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("LightningShardName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("EpicRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        lightningShard = item;
    }
    private void CreateWitheredEye()
    {
        ItemStack item = new ItemStack(Material.SPIDER_EYE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.ConvertToCustom(config.getString("WitheredEyeName")));

        List<String> lore = new ArrayList<>();
        lore.add(ColorUtil.ConvertToCustom(config.getString("CommonRarity")));
        meta.setLore(lore);

        item.setItemMeta(meta);
        witheredEye = item;
    }

}
