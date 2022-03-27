package alterstepix.mythicrpg.mobclasses;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;


public class OverworldInvaderMobclass extends PiglinBrute {

    public OverworldInvaderMobclass(Location loc)
    {
        super(EntityType.PIGLIN_BRUTE,((CraftWorld)loc.getWorld()).getHandle());
        this.setPos(loc.getX(),loc.getY(),loc.getZ());
    }
    public void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(2,new NearestAttackableTargetGoal<Player>(this,Player.class,false));
        this.goalSelector.addGoal(3,new NearestAttackableTargetGoal<Villager>(this,Villager.class,false));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

}
