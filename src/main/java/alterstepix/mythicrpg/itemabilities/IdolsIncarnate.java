package alterstepix.mythicrpg.itemabilities;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.util.ItemLoreLibrary;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
                PotionEffect BEff = new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, true, true, true);
                PotionEffect WEEff = new PotionEffect(PotionEffectType.WEAKNESS, 100, 1, true, true, true);

                double a = Math.random();
                if (a<0.3)
                {
                    entity.addPotionEffect(WEff); //WITHER
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.DRAGON_BREATH, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.4)
                {
                    entity.addPotionEffect(CEff); //CONFUSION
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.6)
                {
                    entity.addPotionEffect(SEff); //SLOW
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.4)
                {
                    entity.addPotionEffect(SDEff); //SLOW_DIGGING
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.7)
                {
                    entity.addPotionEffect(HEff); //HUNGER
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.3)
                {
                    entity.addPotionEffect(PEff); //POISON
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.3)
                {
                    entity.setFireTicks(entity.getFireTicks()+80); //FIRE
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.LAVA, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.6)
                {
                    entity.addPotionEffect(BEff); //BLINDNESS
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.END_ROD, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.2)
                {
                    entity.getWorld().strikeLightningEffect(entity.getLocation());
                    entity.damage(6);
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }
                a = Math.random();
                if (a<0.3)
                {
                    entity.addPotionEffect(WEEff); //WEAKNESS
                    entity.getWorld().spawnParticle(Particle.SOUL, player.getLocation(), 15);
                    entity.getWorld().spawnParticle(Particle.SLIME, player.getLocation(), 15);
                    entity.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,15,5);
                }

            }


        }

    }

}