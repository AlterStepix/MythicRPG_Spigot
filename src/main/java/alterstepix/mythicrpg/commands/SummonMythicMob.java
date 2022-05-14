package alterstepix.mythicrpg.commands;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.mobs.*;
import alterstepix.mythicrpg.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SummonMythicMob implements CommandExecutor, TabCompleter {

    Mythicrpg main;

    public SummonMythicMob(Mythicrpg main)
    {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1)
        {
            if(sender instanceof Player){
                Player player = (Player)sender;

                if(player.isOp() || player.hasPermission("mythicrpg.summonmythicmobs"))
                {
                    switch (args[0]) {
                        case "WitherSpider" -> {
                            WitherSpider mob1 = new WitherSpider(main);
                            mob1.createLeapingSpider(player.getLocation());
                        }
                        case "Parasite" -> {
                            Parasite mob2 = new Parasite(main);
                            mob2.createParasite(player.getLocation());
                        }
                        case "InfectedZombie" -> {
                            InfectedZombie mob3 = new InfectedZombie(main);
                            mob3.createInfectedZombie(player.getLocation());
                        }
                        case "MasterAssassin" -> {
                            MasterAssassin mob4 = new MasterAssassin(main);
                            mob4.createMasterAssassin(player.getLocation());
                        }
                        case "AirSpirit" -> {
                            AirSpirit mob5 = new AirSpirit(main);
                            mob5.createAirSpirit(player.getLocation());
                        }
                        case "FireSpirit" -> {
                            FireSpirit mob6 = new FireSpirit(main);
                            mob6.createFireSpirit(player.getLocation());
                        }
                        case "IceSpirit" -> {
                            IceSpirit mob7 = new IceSpirit(main);
                            mob7.createIceSpirit(player.getLocation());
                        }
                        case "SemiIdol" -> {
                            SemiIdol mob8 = new SemiIdol(main);
                            mob8.createSemiIdol(player.getLocation());
                        }
                        case "AncientZombie" -> {
                            AncientZombie mob9 = new AncientZombie(main);
                            mob9.createAncientZombie(player.getLocation());
                        }
                        case "WitherusNetherlord" -> {
                            NetherLord mob10 = new NetherLord(main);
                            mob10.createNetherLord(player.getLocation());
                        }
                        case "OverworldInvader" -> {
                            OverworldInvader mob11 = new OverworldInvader(main);
                            mob11.CreateOverworldInvader(player.getLocation());
                        }
                        case "Ghost" -> {
                            Ghost mob12 = new Ghost(main);
                            mob12.createGhost(player.getLocation());
                        }
                        case "CursedEmperor" -> {
                            CursedEmperor mob13 = new CursedEmperor(main);
                            mob13.createCursedEmperor(player.getLocation());
                        }
                        case "PhantomRider" -> {
                            PhantomRider mob14 = new PhantomRider(main);
                            mob14.createPhantom(player.getLocation());
                        }
                        case "FrozenSoul" -> {
                            FrozenSoul mob15 = new FrozenSoul(main);
                            mob15.createFrozenSoul(player.getLocation());
                        }
                        case "DesertGuardian" -> {
                            DesertGuardian mob16 = new DesertGuardian(main);
                            mob16.createDesertGuardian(player.getLocation());
                        }
                        case "RevenantArcher" -> {
                            RevenantArcher mob17 = new RevenantArcher(main);
                            mob17.createRevenantArcher(player.getLocation());
                        }
                        case "MushroomMonster" -> {
                            MushroomMonster mob18 = new MushroomMonster(main);
                            mob18.createMushroomMonster(player.getLocation());
                        }
                        case "Cyclops" -> {
                            Cyclops mob19 = new Cyclops(main);
                            mob19.summon(player.getLocation());
                        }
                        case "WatchingEye" -> {
                            WatchingEye mob19 = new WatchingEye(main);
                            mob19.summon(player.getLocation());
                        }
                        case "Giant" -> {
                            mGiant mob19 = new mGiant(main);
                            mob19.summon(player.getLocation());
                        }
                        case "RedstoneMonster" -> {
                            RedstoneMonster mob20 = new RedstoneMonster();
                            mob20.summon(player.getLocation());
                        }
                        case "DarknessIncarnate" -> (new DarknessIncarnate()).summon(player.getLocation());
                    }
                }
                else
                {
                    player.sendMessage(Messages.NotOperator);
                }

            }
            else
            {
                Bukkit.getLogger().info("[mrpg] To use this command from console / command block you need to specify the location and the world");
            }

        }
        else if(args.length == 5)
        {
            if(!(sender.isOp() || sender.hasPermission("mythicrpg.summonmythicmobs")))
            {
                sender.sendMessage(Messages.NotOperator);
                return true;
            }
            World wr = Bukkit.getWorld(args[4]);
            if(wr == null)
            {
                if(sender instanceof Player)
                {
                    Player p = (Player) sender;
                    p.sendMessage(Messages.WrongWorld);
                }
                else
                {
                    Bukkit.getLogger().info(Messages.WrongWorld.replace("§c",""));
                }
            }
            Location loc = new Location(wr, Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            switch (args[0]) {
                case "WitherSpider" -> {
                    WitherSpider mob1 = new WitherSpider(main);

                    mob1.createLeapingSpider(loc);
                }
                case "Parasite" -> {
                    Parasite mob2 = new Parasite(main);
                    mob2.createParasite(loc);
                }
                case "InfectedZombie" -> {
                    InfectedZombie mob3 = new InfectedZombie(main);
                    mob3.createInfectedZombie(loc);
                }
                case "MasterAssassin" -> {
                    MasterAssassin mob4 = new MasterAssassin(main);
                    mob4.createMasterAssassin(loc);
                }
                case "AirSpirit" -> {
                    AirSpirit mob5 = new AirSpirit(main);
                    mob5.createAirSpirit(loc);
                }
                case "FireSpirit" -> {
                    FireSpirit mob6 = new FireSpirit(main);
                    mob6.createFireSpirit(loc);
                }
                case "IceSpirit" -> {
                    IceSpirit mob7 = new IceSpirit(main);
                    mob7.createIceSpirit(loc);
                }
                case "SemiIdol" -> {
                    SemiIdol mob8 = new SemiIdol(main);
                    mob8.createSemiIdol(loc);
                }
                case "AncientZombie" -> {
                    AncientZombie mob9 = new AncientZombie(main);
                    mob9.createAncientZombie(loc);
                }
                case "WitherusNetherlord" -> {
                    NetherLord mob10 = new NetherLord(main);
                    mob10.createNetherLord(loc);
                }
                case "OverworldInvader" -> {
                    OverworldInvader mob11 = new OverworldInvader(main);
                    mob11.CreateOverworldInvader(loc);
                }
                case "Ghost" -> {
                    Ghost mob12 = new Ghost(main);
                    mob12.createGhost(loc);
                }
                case "CursedEmperor" -> {
                    CursedEmperor mob13 = new CursedEmperor(main);
                    mob13.createCursedEmperor(loc);
                }
                case "PhantomRider" -> {
                    PhantomRider mob14 = new PhantomRider(main);
                    mob14.createPhantom(loc);
                }
                case "FrozenSoul" -> {
                    FrozenSoul mob15 = new FrozenSoul(main);
                    mob15.createFrozenSoul(loc);
                }
                case "DesertGuardian" -> {
                    DesertGuardian mob16 = new DesertGuardian(main);
                    mob16.createDesertGuardian(loc);
                }
                case "RevenantArcher" -> {
                    RevenantArcher mob17 = new RevenantArcher(main);
                    mob17.createRevenantArcher(loc);
                }
                case "MushroomMonster" -> {
                    MushroomMonster mob18 = new MushroomMonster(main);
                    mob18.createMushroomMonster(loc);
                }
                case "Cyclops" -> {
                    Cyclops mob19 = new Cyclops(main);
                    mob19.summon(loc);
                }
                case "WatchingEye" -> {
                    WatchingEye mob19 = new WatchingEye(main);
                    mob19.summon(loc);
                }
                case "Giant" -> {
                    mGiant mob19 = new mGiant(main);
                    mob19.summon(loc);
                }
                case "RedstoneMonster" -> (new RedstoneMonster()).summon(loc);
                case "DarknessIncarnate" -> (new DarknessIncarnate()).summon(loc);
            }
        }
        else
        {
            if(sender instanceof Player)
            {
                Player p = (Player)sender;
                p.sendMessage(Messages.WrongArgAmount);
            }
            else
            {
                Bukkit.getLogger().info(Messages.WrongArgAmount.replace("§c",""));
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("WitherSpider");
        list.add("Parasite");
        list.add("InfectedZombie");
        list.add("MasterAssassin");
        list.add("AirSpirit");
        list.add("FireSpirit");
        list.add("IceSpirit");
        list.add("SemiIdol");
        list.add("AncientZombie");
        list.add("WitherusNetherlord");
        list.add("OverworldInvader");
        list.add("Ghost");
        list.add("CursedEmperor");
        list.add("PhantomRider");
        list.add("FrozenSoul");
        list.add("DesertGuardian");
        list.add("RevenantArcher");
        list.add("MushroomMonster");
        list.add("Cyclops");
        list.add("WatchingEye");
        list.add("Giant");
        list.add("RedstoneMonster");
        list.add("DarknessIncarnate");
        List<String> tab = new ArrayList<String>();
        if(args.length == 1)
        {
            for (String var : list)
            {
                if(var.toLowerCase(Locale.ROOT).startsWith(args[0].toLowerCase(Locale.ROOT)))
                    tab.add(var);
            }
        }
        else if(args.length == 5)
        {
            tab.add("world");
            tab.add("world_nether");
            tab.add("world_end");
        }

        return tab;
    }
}
