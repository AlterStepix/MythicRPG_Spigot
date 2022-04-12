package alterstepix.mythicrpg.misc;

import alterstepix.mythicrpg.Mythicrpg;
import alterstepix.mythicrpg.managers.DropTable;
import alterstepix.mythicrpg.managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
public class CustomRecipes {

    Mythicrpg main;
    FileConfiguration config;
    ItemManager items;
    DropTable drops;

    public CustomRecipes(Mythicrpg main)
    {
        this.main = main;
        this.config = main.getConfig();
        this.items = new ItemManager(main);
        this.items.init();
        this.drops = new DropTable(main);
        this.drops.init();
    }

    // Example
    public void RegisterExampleRecipe()
    {
        ItemStack item = new ItemStack(Material.AIR,1);
        NamespacedKey key = new NamespacedKey(main,"name");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                "AAA",
                "AAA",
                "AAA"
        );

        recipe.setIngredient('A', Material.AIR);
        Bukkit.addRecipe(recipe);
    }

    public void RegisterTerminatorRecipe()
    {
        ItemStack item = items.Terminator;
        NamespacedKey key = new NamespacedKey(main,"terminator");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                "IF ",
                "IBL",
                "IF "
        );

        recipe.setIngredient('F', Material.NETHERITE_INGOT);
        recipe.setIngredient('L', new RecipeChoice.ExactChoice(drops.lightningShard));
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(drops.impulseShard));
        recipe.setIngredient('B',Material.BOW);

        Bukkit.addRecipe(recipe);
    }
    public void RegisterMilkPotionRecipe()
    {
        ItemStack item = items.MilkPotion;
        NamespacedKey key = new NamespacedKey(main,"milk_potion");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " M ",
                " P ",
                " M "
        );

        recipe.setIngredient('M', Material.MILK_BUCKET);
        recipe.setIngredient('P', Material.SPLASH_POTION);

        Bukkit.addRecipe(recipe);
    }
    public void RegisterLightingAxeRecipe()
    {
        ItemStack item = items.LightingAxe;
        NamespacedKey key = new NamespacedKey(main,"lighing_axe");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " II",
                " SI",
                " S "
        );

        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(drops.lightningShard));
        Bukkit.addRecipe(recipe);
    }
    public void RegisterImpulseSwordRecipe()
    {
        ItemStack item = items.ImpulseSword;
        NamespacedKey key = new NamespacedKey(main,"impulse_sword");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " I ",
                " I ",
                " S "
        );

        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('I', new RecipeChoice.ExactChoice(drops.impulseShard));
        Bukkit.addRecipe(recipe);
    }
    public void RegisterIdolsIncarnateRecipe()
    {
        return;
    }
    public void RegisterHealingSwordRecipe()
    {
        ItemStack item = items.HealingSword;
        NamespacedKey key = new NamespacedKey(main,"healing_sword");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " FF",
                " HF",
                " PC"
        );

        recipe.setIngredient('C', Material.CAKE);
        recipe.setIngredient('P', new RecipeChoice.ExactChoice(drops.parasiteHeart));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(drops.infectedHeart));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(drops.infectedFlesh));
        Bukkit.addRecipe(recipe);
    }
    public void RegisterGiantSwordRecipe()
    {
        return;
    }
    public void RegisterFuriousAxeRecipe()
    {
        return;
    }
    public void RegisterFrozenWandRecipe()
    {
        ItemStack item = items.FrozenWand;
        NamespacedKey key = new NamespacedKey(main,"frozen_wand");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " F ",
                " S ",
                " S "
        );

        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(drops.frozenShard));

        Bukkit.addRecipe(recipe);
    }
    public void RegisterAmberScytheRecipe()
    {
        ItemStack item = items.AmberScythe;
        NamespacedKey key = new NamespacedKey(main,"amber_scythe");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " AA",
                " S ",
                " S "
        );

        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(drops.amberShard));

        Bukkit.addRecipe(recipe);
    }
    public void RegisterAirBurnerRecipe()
    {
        ItemStack item = items.AirBurner;
        NamespacedKey key = new NamespacedKey(main,"air_burner");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " A ",
                "AFA",
                " A "
        );

        recipe.setIngredient('F', Material.FLINT_AND_STEEL);
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(drops.amberShard));

        Bukkit.addRecipe(recipe);
    }
    public void RegisterRunicDaggerRecipe()
    {
        ItemStack item = items.RuinicDagger[0];
        NamespacedKey key = new NamespacedKey(main,"runic_dagger");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " F ",
                " A",
                " S "
        );

        recipe.setIngredient('F', new RecipeChoice.ExactChoice(drops.frozenShard));
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(drops.amberShard));

        Bukkit.addRecipe(recipe);
    }
    public void RegisterMythicSwordOfLegendsRecipe()
    {

    }
    public void RegisterFlamingWhipRecipe()
    {
        ItemStack item = items.FlamingWhip;
        NamespacedKey key = new NamespacedKey(main,"flaming_whip");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                "NAN",
                "AFA",
                "NAN"
        );

        recipe.setIngredient('F', Material.FISHING_ROD);
        recipe.setIngredient('A', new RecipeChoice.ExactChoice(drops.amberShard));
        recipe.setIngredient('N', new RecipeChoice.ExactChoice(drops.netherEssence));

        Bukkit.addRecipe(recipe);
    }
    public void RegisterDarknessConcentratorRecipe()
    {

    }
    public void RegisterInfectedSwordRecipe()
    {
        ItemStack item = items.InfectedSword;
        NamespacedKey key = new NamespacedKey(main,"infected_sword");

        ShapedRecipe recipe = new ShapedRecipe(key,item);
        recipe.shape(
                " F ",
                " F ",
                " S "
        );

        recipe.setIngredient('S', new RecipeChoice.ExactChoice(drops.infectedHeart));
        recipe.setIngredient('F', new RecipeChoice.ExactChoice(drops.infectedFlesh));

        Bukkit.addRecipe(recipe);
    }

}
