package alterstepix.mythicrpg.util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class MobItemCreator {

    public static ItemStack leather(Material material, int protection, int r, int g, int b)
    {
        ItemStack item = new ItemStack(material,1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.fromRGB(r,g,b));
        if(protection > 0)
        {
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,protection,true);
        }
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack normal(Material material, int protection)
    {
        ItemStack item = new ItemStack(material,1);
        ItemMeta meta = item.getItemMeta();
        if(protection > 0)
        {
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,protection,true);
        }
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack weapon(Material material, int sharpness)
    {
        ItemStack item = new ItemStack(material,1);
        ItemMeta meta = item.getItemMeta();
        if(sharpness > 0)
        {
            meta.addEnchant(Enchantment.DAMAGE_ALL,sharpness,true);
        }
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }

}
