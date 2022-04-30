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
        AirBurnerDescription.add("§6RIGHT CLICK: §eAir Burner");
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

        // Mythic Weapon

        ArrayList<String> MythicWeapon = new ArrayList<>();

        cooldown = config.getInt("mythicSwordOfLegendsCooldown");
        MythicWeapon.add("");
        MythicWeapon.add("§6ITEM ABILITY: §eMythic Weapon");
        MythicWeapon.add("§7You can only use this weapon");
        MythicWeapon.add("§7if you are wearing mythic warrior armor");
        MythicWeapon.add("");
        MythicWeapon.add("§6RIGHT CLICK: §eSlam");
        MythicWeapon.add("§7Right click to slam your sword");
        MythicWeapon.add("§7into the ground creating a mythic aura");
        MythicWeapon.add("§8Cooldown: "+ cooldown + "s");

        Lore.put("MythicWeapon",MythicWeapon);

        // Flaming Arc

        ArrayList<String> FlamingArc = new ArrayList<>();

        cooldown = config.getInt("flamingWhipCooldown");
        FlamingArc.add("");
        FlamingArc.add("§6RIGHT CLICK: §eFlaming Arc");
        FlamingArc.add("§7Creates a flaming arc");
        FlamingArc.add("§7that burns your enemies");
        FlamingArc.add("§8Cooldown: "+ cooldown + "s");

        Lore.put("FlamingArc",FlamingArc);

        ArrayList<String> DarknessBeam = new ArrayList<>();

        cooldown = config.getInt("darknessConcentratorCooldown");
        DarknessBeam.add("");
        DarknessBeam.add("§6RIGHT CLICK: §eDarkness Beam");
        DarknessBeam.add("§7Creates a darkness arc");
        DarknessBeam.add("§7that hurt your enemies");
        DarknessBeam.add("§8Cooldown: "+ cooldown + "s");

        Lore.put("DarknessBeam",DarknessBeam);


        ArrayList<String> InfectedBlade = new ArrayList<>();

        InfectedBlade.add("");
        InfectedBlade.add("§6RIGHT CLICK: §eInfected blade");
        InfectedBlade.add("§7Infect your enemies on hit");

        Lore.put("InfectedBlade",InfectedBlade);


        ArrayList<String> Growth = new ArrayList<>();

        Growth.add("");
        Growth.add("§6RIGHT CLICK: §eGrowth");
        Growth.add("§7Regenerates you on hit");


        Lore.put("Growth",Growth);


        ArrayList<String> Singularity = new ArrayList<>();

        Singularity.add("");
        Singularity.add("§6ITEM ABILITY: §eGravity Trap");
        Singularity.add("§7Shoots a special gravity trap");
        Singularity.add("§7that pulls you opponents into it");
        Singularity.add("§7and explodes");

        Lore.put("Singularity",Singularity);

        /* ===========================================
           ==============| Scrolls |==================
           ===========================================
         */

        // Arrow Storm

        ArrayList<String> ArrowStormScrollDescription = new ArrayList<>();

        ArrowStormScrollDescription.add("");
        ArrowStormScrollDescription.add("§6SCROLL: §eArrow Storm");
        ArrowStormScrollDescription.add("§7Shoots an arrow storm");
        ArrowStormScrollDescription.add("§8Consumed on use");

        Lore.put("ArrowStorm",ArrowStormScrollDescription);

        // Nether Storm

        ArrayList<String> NetherStormScrollDescription = new ArrayList<>();

        NetherStormScrollDescription.add("");
        NetherStormScrollDescription.add("§6SCROLL: §eNether Storm");
        NetherStormScrollDescription.add("§7Creates a nether storm");
        NetherStormScrollDescription.add("§8Consumed on use");

        Lore.put("NetherStorm",NetherStormScrollDescription);

        // Healing Totem


        ArrayList<String> HealingTotemScrollDescription = new ArrayList<>();

        HealingTotemScrollDescription.add("");
        HealingTotemScrollDescription.add("§6SCROLL: §eHealing Totem");
        HealingTotemScrollDescription.add("§7Creates a healing totem");
        HealingTotemScrollDescription.add("§8Consumed on use");

        Lore.put("HealingTotem",HealingTotemScrollDescription);

        // Healing Totem


        ArrayList<String> FrozenStormScrollDescription = new ArrayList<>();

        FrozenStormScrollDescription.add("");
        FrozenStormScrollDescription.add("§6SCROLL: §eFrozen Storm");
        FrozenStormScrollDescription.add("§7Creates a frozen storm");
        FrozenStormScrollDescription.add("§8Consumed on use");

        Lore.put("FrozenStorm",FrozenStormScrollDescription);

        // Infernal Aura

        ArrayList<String> InferalAuraScrollDescription = new ArrayList<>();

        InferalAuraScrollDescription.add("");
        InferalAuraScrollDescription.add("§6SCROLL: §eInfernal Aura");
        InferalAuraScrollDescription.add("§7Creates an infernal aura around you");
        InferalAuraScrollDescription.add("§8Consumed on use");

        Lore.put("InfernalAura",InferalAuraScrollDescription);

        /* ===========================================
           ==============| Armor |==================
           ===========================================
         */

        // Grandmaster Armor

        ArrayList<String> GMADescription = new ArrayList<>();

        GMADescription.add("");
        GMADescription.add("§6Full Set Bonus: §eThe Grandmaster");
        GMADescription.add("§7You can fully ignore the damage");
        GMADescription.add("§7you get with a 30% chance");

        Lore.put("GMA",GMADescription);

        // Master Assassin Armor

        ArrayList<String> MADescription = new ArrayList<>();

        cooldown = config.getInt("MasterAssassinArmorCooldown");
        MADescription.add("");
        MADescription.add("§6Full Set Bonus: §eThe Master Assassin");
        MADescription.add("§7Sneak to teleport behind closest");
        MADescription.add("§7entity within 7 block radius");
        MADescription.add("§7and get Strength 6 for 3s");
        MADescription.add("§8Cooldown: "+ cooldown + "s");

        Lore.put("MAA",MADescription);

        // Frozen Warrior Armor

        ArrayList<String> FADescription = new ArrayList<>();

        FADescription.add("");
        FADescription.add("§6Full Set Bonus: §eThe Frozen Warrior");
        FADescription.add("§7You will deal +6 extra damage");
        FADescription.add("§7on melee hit and get");
        FADescription.add("§7Strength 2 for 3s");
        FADescription.add("§7if you will get damaged");

        Lore.put("FWA",FADescription);

        // Mythic Warrior Armor

        ArrayList<String> MWADescription = new ArrayList<>();

        MWADescription.add("");
        MWADescription.add("§6Full Set Bonus: §eThe Mythic Warrior");
        MWADescription.add("§7I: You will deal +10 extra damage");
        MWADescription.add("§7on each melee hit");
        MWADescription.add("§7II: You will get Strength 2");
        MWADescription.add("§7and Speed 2 for 3s if you will get damaged");
        MWADescription.add("§7III: You can ignore any damage you get");
        MWADescription.add("§7with 40% chance");

        Lore.put("MWA",MWADescription);

        // Thief Armor

        ArrayList<String> TADescription = new ArrayList<>();

        TADescription.add("");
        TADescription.add("§6Full Set Bonus: §eThe Thief");
        TADescription.add("§7You can ignore any fall damage you take");
        TADescription.add("§7You can dodge projectiles with 20% chance");

        Lore.put("TA",TADescription);

        /* ===========================================
           ==============| Misc |==================
           ===========================================
         */

        ArrayList<String> BestiaryBookDesc = new ArrayList<>();

        BestiaryBookDesc.add("");
        BestiaryBookDesc.add("§eBestiary: ");
        BestiaryBookDesc.add("§7This old book contains");
        BestiaryBookDesc.add("§7a lot of information about ");
        BestiaryBookDesc.add("§7different mobs, minibosses, bosses.");
        BestiaryBookDesc.add("§8Right Click to open.");

        Lore.put("Bestiary",BestiaryBookDesc);

    }

}
