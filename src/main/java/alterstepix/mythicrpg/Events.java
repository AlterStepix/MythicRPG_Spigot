package alterstepix.mythicrpg;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.util.Vector;

public class Events implements Listener {

    @EventHandler
    public void OnEntityBreedEvent(EntityBreedEvent e)
    {
        Player player = (Player)e.getBreeder();
        Vector vector = new Vector(0,1,0);
        player.setVelocity(vector);
    }

}
