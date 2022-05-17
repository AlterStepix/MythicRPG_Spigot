/*
Copyright 2022 AlterStepix

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.mobsystem.info;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.util.Vector;

public abstract class BlockThrowerInfo {
    public Material material;

    public int cooldown;
    public int starting;

    public abstract void displayParticles(Location location);
    public abstract void displayTrailParticles(Location location);
    public abstract void displayHitParticles(Location location);
    public abstract void playSound(Location location);
    public abstract void playHitSound();




}
