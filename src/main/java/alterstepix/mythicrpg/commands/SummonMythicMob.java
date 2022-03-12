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
                    switch (args[0])
                    {
                        case "WitherSpider":
                            WitherSpider mob1 = new WitherSpider(main);
                            mob1.createLeapingSpider(player.getLocation());
                            break;
                        case "Parasite":
                            Parasite mob2 = new Parasite(main);
                            mob2.createParasite(player.getLocation());
                            break;
                        case "InfectedZombie":
                            InfectedZombie mob3 = new InfectedZombie(main);
                            mob3.createInfectedZombie(player.getLocation());
                            break;
                        case "MasterAssassin":
                            MasterAssassin mob4 = new MasterAssassin(main);
                            mob4.createMasterAssassin(player.getLocation());
                            break;
                        case "AirSpirit":
                            AirSpirit mob5 = new AirSpirit(main);
                            mob5.createAirSpirit(player.getLocation());
                            break;
                        case "FireSpirit":
                            FireSpirit mob6 = new FireSpirit(main);
                            mob6.createFireSpirit(player.getLocation());
                            break;
                        case "IceSpirit":
                            IceSpirit mob7 = new IceSpirit(main);
                            mob7.createIceSpirit(player.getLocation());
                            break;
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
            switch (args[0])
            {
                case "WitherSpider":
                    WitherSpider mob1 = new WitherSpider(main);

                    Location loc1 = new Location(wr,Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
                    mob1.createLeapingSpider(loc1);
                    break;
                case "Parasite":
                    Parasite mob2 = new Parasite(main);

                    Location loc2 = new Location(wr,Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
                    mob2.createParasite(loc2);
                    break;
                case "InfectedZombie":
                    InfectedZombie mob3 = new InfectedZombie(main);

                    Location loc3 = new Location(wr,Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
                    mob3.createInfectedZombie(loc3);
                    break;
                case "MasterAssassin":
                    MasterAssassin mob4 = new MasterAssassin(main);

                    Location loc4 = new Location(wr,Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
                    mob4.createMasterAssassin(loc4);
                    break;
                case "AirSpirit":
                    AirSpirit mob5 = new AirSpirit(main);

                    Location loc5 = new Location(wr,Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
                    mob5.createAirSpirit(loc5);
                    break;
                case "FireSpirit":
                    FireSpirit mob6 = new FireSpirit(main);

                    Location loc6 = new Location(wr,Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
                    mob6.createFireSpirit(loc6);
                    break;
                case "IceSpirit":
                    IceSpirit mob7 = new IceSpirit(main);

                    Location loc7 = new Location(wr,Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
                    mob7.createIceSpirit(loc7);
                    break;
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
        List<String> tab = new ArrayList<String>();
        if(args.length == 1)
        {
            tab.add("WitherSpider");
            tab.add("Parasite");
            tab.add("InfectedZombie");
            tab.add("MasterAssassin");
            tab.add("AirSpirit");
            tab.add("FireSpirit");
            tab.add("IceSpirit");
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
