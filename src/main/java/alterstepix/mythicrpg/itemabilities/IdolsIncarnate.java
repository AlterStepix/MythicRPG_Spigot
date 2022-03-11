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



public class IdolsIncarnate implements Listener {

    Mythicrpg main;
    ItemLoreLibrary lib;

    public IdolsIncarnate(Mythicrpg main)
    {
        this.main = main;
        lib = new ItemLoreLibrary(main);
        lib.Init();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof LivingEntity && event.getDamager() instanceof Player){
            Player player = (Player) event.getDamager();
            LivingEntity entity = (LivingEntity) event.getEntity();

            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(lib.Lore.get("Curse").get(1)))
            {
                PotionEffect WEff = new PotionEffect(PotionEffectType.WITHER, 100, 5, true, true, true);
                PotionEffect CEff = new PotionEffect(PotionEffectType.CONFUSION, 400, 1, true, true, true);
                PotionEffect SEff = new PotionEffect(PotionEffectType.SLOW, 60, 2, true, true, true);
                PotionEffect SDEff = new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 2, true, true, true);
                PotionEffect HEff = new PotionEffect(PotionEffectType.HUNGER, 200, 4, true, true, true);
                PotionEffect PEff = new PotionEffect(PotionEffectType.POISON, 100, 3, true, true, true);

                double a = Math.random();
                if (a<0.3)
                {
                    entity.addPotionEffect(WEff); //WITHER
                }
                a = Math.random();
                if (a<0.4)
                {
                    entity.addPotionEffect(CEff); //CONFUSION
                }
                a = Math.random();
                if (a<0.6)
                {
                    entity.addPotionEffect(SEff); //SLOW
                }
                a = Math.random();
                if (a<0.4)
                {
                    entity.addPotionEffect(SDEff); //SLOW_DIGGING
                }
                a = Math.random();
                if (a<0.7)
                {
                    entity.addPotionEffect(HEff); //HUNGER
                }
                a = Math.random();
                if (a<0.3)
                {
                    entity.addPotionEffect(PEff); //POISON
                }

            }


        }

    }

}