package alterstepix.mythicrpg.util;

import alterstepix.mythicrpg.Mythicrpg;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemLoreLibrary {

    FileConfiguration config;
    Mythicrpg main;

    public HashMap<String, ArrayList<String>> Lore;

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
        ThunderlordDescription.add("§7Strikes all nearby enemies with lightnings");
        ThunderlordDescription.add("§8Cooldown: "+ cooldown + "s");

        Lore.put("Thunderlord",ThunderlordDescription);

        //  LightningPower
        ArrayList<String> LightningPowerDescription = new ArrayList<>();

        LightningPowerDescription.add("");
        LightningPowerDescription.add("§6ITEM ABILITY: §eLightning Power");
        LightningPowerDescription.add("§7Strikes your enemies with lightning on hit");
        LightningPowerDescription.add("§7Deals extra damage to undead mobs");


        Lore.put("LightningPower",LightningPowerDescription);

        //  Termination
        ArrayList<String> TerminationDescription = new ArrayList<>();

        TerminationDescription.add("");
        TerminationDescription.add("§6LEFT CLICK: §eTermination");
        TerminationDescription.add("§7Instantly shoots 3 arrow");


        Lore.put("Termination",TerminationDescription);

        //  Recall
        ArrayList<String> RecallDescription = new ArrayList<>();

        cooldown = this.config.getInt("terminatorCooldown");
        radius = this.config.getInt("terminatorAbilityRange");
        RecallDescription.add("");
        RecallDescription.add("§6SNEAK + LEFT CLICK: §eRecall");
        RecallDescription.add("§7Sends all arrows in a " + radius + " blocks radius to the sky");
        RecallDescription.add("§8Cooldown: "+ cooldown + "s");


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

        // FireFury

        ArrayList<String> FireFuryDescription = new ArrayList<>();

        cooldown = config.getInt("amberScytheCooldown");
        FireFuryDescription.add("");
        FireFuryDescription.add("§6RIGHT CLICK: §eFire Fury");
        FireFuryDescription.add("§7Shoots 3 fireballs");
        FireFuryDescription.add("§8Cooldown: "+ cooldown + "s");


        Lore.put("FireFury",FireFuryDescription);

        // Milk

        ArrayList<String> MilkDescription = new ArrayList<>();

        MilkDescription.add("");
        MilkDescription.add("§6ITEM ABILITY: §eMilk");
        MilkDescription.add("§7Removes all potion effects on use");


        Lore.put("Milk",MilkDescription);

        // Giant's Hit

        ArrayList<String> GiantsHitDescription = new ArrayList<>();


        cooldown = config.getInt("giantSwordCooldown");
        GiantsHitDescription.add("");
        GiantsHitDescription.add("§6ITEM ABILITY: §eGiant's Hit");
        GiantsHitDescription.add("§7Deals a lot of damage and knockback");
        GiantsHitDescription.add("§8Cooldown: "+ cooldown + "s");


        Lore.put("GiantHit", GiantsHitDescription);

        // Throwable

        ArrayList<String> ThrowableDescription = new ArrayList<>();

        cooldown = config.getInt("furiousAxeCooldown");
        ThrowableDescription.add("");
        ThrowableDescription.add("§6RIGHT CLICK: §eThrow");
        ThrowableDescription.add("§7Throws your weapon in the air");
        ThrowableDescription.add("§8Cooldown: "+ cooldown + "s");

        Lore.put("Throw",ThrowableDescription);

        // AirBurner

        ArrayList<String> AirBurnerDescription = new ArrayList<>();

        AirBurnerDescription.add("");
        AirBurnerDescription.add("§6ITEM ABILITY: §eAirBurner");
        AirBurnerDescription.add("§7Burning all living entities around 10x10x10 blocks");

        Lore.put("AirBurner",AirBurnerDescription);

        // RunicSwap

        ArrayList<String> RunicSwap = new ArrayList<>();

        RunicSwap.add("");
        RunicSwap.add("§6RIGHT CLICK: §eSwap");
        RunicSwap.add("§7Changes your weapons mode");

        Lore.put("RunicSwap",RunicSwap);

        // Runic Frozen

        ArrayList<String> RunicFrozen = new ArrayList<>();

        RunicFrozen.add("");
        RunicFrozen.add("§6ITEM ABILITY: §eFrozen Runes");
        RunicFrozen.add("§7Freezes your enemies");
        RunicFrozen.add("§7Deals extra damage to flaming spirits");

        Lore.put("RunicFrozen",RunicFrozen);

        // Runic Molten

        ArrayList<String> RunicMolten = new ArrayList<>();

        RunicMolten.add("");
        RunicMolten.add("§6ITEM ABILITY: §eMolten Runes");
        RunicMolten.add("§7Ignites your enemies");
        RunicMolten.add("§7Deals extra damage to frozen spirits");

        Lore.put("RunicMolten",RunicMolten);

        /* ===========================================
           ==============| Scrolls |==================
           ===========================================
         */

        // Array List

        ArrayList<String> ArrowStormScrollDescription = new ArrayList<>();

        ArrowStormScrollDescription.add("");
        ArrowStormScrollDescription.add("§6SCROLL: §eArrow Storm");
        ArrowStormScrollDescription.add("§7Shoots an arrow storm");
        ArrowStormScrollDescription.add("§8Consumed on use");

        Lore.put("ArrowStorm",ArrowStormScrollDescription);

    }

}
