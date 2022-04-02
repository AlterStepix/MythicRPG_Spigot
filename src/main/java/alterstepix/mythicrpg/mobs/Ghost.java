package alterstepix.mythicrpg.mobs;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.configuration.file.FileConfiguration;

public class Ghost {

    Mythicrpg main;
    FileConfiguration config;

    public Ghost(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }
}
