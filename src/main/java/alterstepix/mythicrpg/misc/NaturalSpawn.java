/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.misc;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.mobs.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;


public class NaturalSpawn implements Listener {

    Mythicrpg main;
    FileConfiguration config;

    public NaturalSpawn(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
    }

    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent e)
    {
        if(e.getEntity().getCustomName() == null)
            return;
        if(e.getEntity().getCustomName().equals(""))
            return;
        if(e.getEntityType() == EntityType.SPIDER && config.getInt("SpawnWitherSpiders") == 1 && Math.random() < 0.1)
        {
            WitherSpider mob = new WitherSpider(main);
            mob.createLeapingSpider(e.getLocation());
            e.getEntity().remove();
        }
        if(e.getEntityType() == EntityType.ZOMBIE && config.getInt("SpawnInfectedZombies") == 1 && Math.random() < 0.1)
        {
            InfectedZombie mob = new InfectedZombie(main);
            mob.createInfectedZombie(e.getLocation());
            e.getEntity().remove();
        }
        if(e.getEntityType() == EntityType.ZOMBIE && config.getInt("SpawnParasite") == 1 && Math.random() < 0.01)
        {
            Parasite mob = new Parasite(main);
            mob.createParasite(e.getLocation());
            e.getEntity().remove();
        }
        if(e.getEntityType() == EntityType.SKELETON && config.getInt("SpawnMasterAssassins") == 1 && Math.random() < 0.1)
        {
            MasterAssassin mob = new MasterAssassin(main);
            mob.createMasterAssassin(e.getLocation());
            e.getEntity().remove();
        }
        if(e.getEntityType() == EntityType.ZOMBIE && config.getInt("SpawnAncientZombies") == 1 && Math.random() < 0.1)
        {
            AncientZombie mob = new AncientZombie(main);
            mob.createAncientZombie(e.getLocation());
            e.getEntity().remove();
        }
        if(e.getEntityType() == EntityType.IRON_GOLEM && config.getInt("SpawnSemiIdols") == 1 && Math.random() < 0.01)
        {
            SemiIdol mob = new SemiIdol(main);
            mob.createSemiIdol(e.getLocation());
            e.getEntity().remove();
        }
        if((e.getEntityType() == EntityType.ZOMBIE || e.getEntityType() == EntityType.SKELETON) && config.getInt("SpawnOverworldInvaders") == 1 && Math.random() < 0.01)
        {
            OverworldInvader mob = new OverworldInvader(main);
            mob.CreateOverworldInvader(e.getLocation());
            e.getEntity().remove();
        }
        if(e.getEntityType() == EntityType.CREEPER && config.getInt("SpawnGhosts") == 1 && Math.random() < 0.05)
        {
            Ghost mob = new Ghost(main);
            mob.createGhost(e.getLocation());
            e.getEntity().remove();
        }
        if(e.getEntityType() == EntityType.PHANTOM && config.getInt("SpawnPhantomRiders") == 1 && Math.random() < 0.1)
        {
            PhantomRider mob = new PhantomRider(main);
            mob.createPhantom(e.getLocation());
            e.getEntity().remove();
        }
        if(e.getEntityType() == EntityType.HUSK && config.getInt("SpawnPhantomRiders") == 1 && Math.random() < 0.1)
        {
            DesertGuardian mob = new DesertGuardian(main);
            mob.createDesertGuardian(e.getLocation());
            e.getEntity().remove();
        }
    }

}
