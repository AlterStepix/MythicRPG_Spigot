package alterstepix.mythicrpg.util;

import org.bukkit.entity.Player;

public class PMU {

    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public PMU(Player owner) {
        this.owner = owner;
    }
}
