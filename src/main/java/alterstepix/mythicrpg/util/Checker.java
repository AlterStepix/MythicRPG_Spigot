package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Checker {

    public static boolean isHoldingItem(Player player, String idlore) {

        ItemLoreLibrary lib = new ItemLoreLibrary(Mythicrpg.INSTANCE);
        lib.Init();

        return player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get(idlore).get(1));
    }

    public static boolean isWearingArmor_4(Player player, String idlore) {

        ItemLoreLibrary lib = new ItemLoreLibrary(Mythicrpg.INSTANCE);
        lib.Init();

        if(player.getInventory().getHelmet() != null && player.getInventory().getChestplate() != null && player.getInventory().getLeggings() != null && player.getInventory().getBoots() != null) {
            if (player.getInventory().getHelmet().getItemMeta() != null && player.getInventory().getChestplate().getItemMeta() != null && player.getInventory().getLeggings().getItemMeta() != null && player.getInventory().getBoots().getItemMeta() != null) {
                if (player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getChestplate().getItemMeta().getLore() != null && player.getInventory().getLeggings().getItemMeta().getLore() != null && player.getInventory().getBoots().getItemMeta().getLore() != null) {
                    return player.getInventory().getHelmet().getItemMeta().getLore().contains(lib.Lore.get(idlore).get(1)) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lib.Lore.get(idlore).get(1)) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lib.Lore.get(idlore).get(1)) && player.getInventory().getBoots().getItemMeta().getLore().contains(lib.Lore.get(idlore).get(1));
                }
            }
        }

        return false;
    }

}
