package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InfectedSword implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;

    public InfectedSword(Mythicrpg main)
    {
        this.main = main;
        this.lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event)
    {
        if(event.getDamager() instanceof Player player)
        {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("InfectedBlade").get(1))) {
                if(event.getEntity() instanceof LivingEntity trg)
                {
                    trg.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,1200,1,false,false,false));
                    trg.addPotionEffect(new PotionEffect(PotionEffectType.POISON,600,0,false,false,false));
                }
            }
        }

    }
}
