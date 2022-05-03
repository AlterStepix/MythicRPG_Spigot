package alterstepix.mythicrpg.menusystem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuItemWrapper {
    public static ItemStack item(Material material, String name, String... lore)
    {
        ItemStack result = new ItemStack(material,1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(name);

        if(lore != null)
        {
            List<String> itemLore = new ArrayList<>(List.of(lore));
            meta.setLore(itemLore);
        }


        result.setItemMeta(meta);
        return result;
    }
}
