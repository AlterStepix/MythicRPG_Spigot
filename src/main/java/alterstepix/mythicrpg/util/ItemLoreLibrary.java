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

        //  LightningPower
        ArrayList<String> LightningPowerDescription = new ArrayList<>();

        LightningPowerDescription.add("");
        LightningPowerDescription.add("§6ITEM ABILITY: §eLightning Power");
        LightningPowerDescription.add("§7Strikes your enemies with lightning on hit");


        Lore.put("LightningPower",LightningPowerDescription);

        //  Termination
        ArrayList<String> TerminationDescription = new ArrayList<>();

        TerminationDescription.add("");
        TerminationDescription.add("§6LEFT CLICK: §eTermination");
        TerminationDescription.add("§7Instantly shoots 3 arrow");


        Lore.put("Termination",TerminationDescription);

        //  Recall
        ArrayList<String> RecallDescription = new ArrayList<>();

        radius = this.config.getInt("terminatorAbilityRange");
        RecallDescription.add("");
        RecallDescription.add("§6SNEAK + LEFT CLICK: §eRecall");
        RecallDescription.add("§7Sends all arrows in a " + radius + " blocks radius to the sky");


        Lore.put("Recall",RecallDescription);

        //  Annihilation
        ArrayList<String> AnnihilationDescription = new ArrayList<>();

        radius = this.config.getInt("terminatorAbilityRange");
        AnnihilationDescription.add("");
        AnnihilationDescription.add("§6KEYBOARD F: §eAnnihilation");
        AnnihilationDescription.add("§7Explodes all arrows in a " + radius + " blocks radius");


        Lore.put("Annihilation",AnnihilationDescription);

        //  Healing
        ArrayList<String> HealingDescription = new ArrayList<>();

        HealingDescription.add("");
        HealingDescription.add("§6RIGHT CLICK: §eHealing");
        HealingDescription.add("§7Heals you in exchange for hunger");


        Lore.put("Healing",HealingDescription);

        //  Curse
        ArrayList<String> CurseDescription = new ArrayList<>();

        CurseDescription.add("");
        CurseDescription.add("§6ITEM ABILITY: §eCurse");
        CurseDescription.add("§7Applies strong debuffs to your enemies on hit");


        Lore.put("Curse",CurseDescription);

        //  FrozenBreathe
        ArrayList<String> FrozenBreatheDescription = new ArrayList<>();

        radius = config.getInt("frozenWandRadius");
        cooldown = config.getInt("frozenWandCooldown");
        FrozenBreatheDescription.add("");
        FrozenBreatheDescription.add("§6RIGHT CLICK: §eFrozen Breathe");
        FrozenBreatheDescription.add("§7Debuffs your enemies in a "+radius+" block radius");
        FrozenBreatheDescription.add("§8Cooldown: "+ cooldown + "s");


        Lore.put("FrozenBreathe",FrozenBreatheDescription);

        //  Pull
        ArrayList<String> PullDescription = new ArrayList<>();

        radius = config.getInt("impulseSwordRadius");
        cooldown = config.getInt("impulseSwordCooldown");
        PullDescription.add("");
        PullDescription.add("§6RIGHT CLICK: §ePull");
        PullDescription.add("§7Pulls your enemies towards you in a "+radius+" block radius");
        PullDescription.add("§8Cooldown: "+ cooldown + "s");


        Lore.put("Pull",PullDescription);

        //  Push
        ArrayList<String> PushDescription = new ArrayList<>();

        radius = config.getInt("impulseSwordRadius");
        cooldown = config.getInt("impulseSwordCooldown");
        PushDescription.add("");
        PushDescription.add("§6SNEAK + RIGHT CLICK: §ePush");
        PushDescription.add("§7Pushes your enemies from you in a "+radius+" block radius");
        PushDescription.add("§8Cooldown: "+ cooldown + "s");


        Lore.put("Push",PushDescription);
    }

}
