package alterstepix.mythicrpg.misc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ResourcePackLoader implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event)
    {
        //event.getPlayer().setResourcePack("");
    }
}
