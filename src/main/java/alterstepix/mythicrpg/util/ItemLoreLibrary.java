package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemLoreLibrary {

    FileConfiguration config;
    Mythicrpg main;

    HashMap<String, ArrayList<String>> Lore;

    public ItemLoreLibrary(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        Lore = new HashMap<>();
    }
    public void Init()
    {
        int cooldown = 0;
        int radius = 0;

        //  Thunderlord
        ArrayList<String> ThunderlordDescription = new ArrayList<>();

        cooldown = this.config.getInt("lightningAxeCooldown");
        ThunderlordDescription.add("");
        ThunderlordDescription.add("§6RIGHT CLICK: §eThunderlord");
        ThunderlordDescription.add("§7Strikes all nearby enemies with lightning");
        ThunderlordDescription.add("§8Cooldown: "+ cooldown + "s");

        Lore.put("Thunderlord",ThunderlordDescription);
    }

}
