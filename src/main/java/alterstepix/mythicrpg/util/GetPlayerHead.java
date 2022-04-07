package alterstepix.mythicrpg.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GetPlayerHead {

    public static String GrandmasterHelmet = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;1165266987,715278077,-1144161251,-894625357],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVlYjBiZDg1YWFkZGYwZDI5ZWQwODJlYWMwM2ZjYWRlNDNkMGVlODAzYjBlODE2MmFkZDI4YTYzNzlmYjU0ZSJ9fX0=\"}]}}}";
    public static String NetherCatalyst = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;-1571577115,-1110423922,-1407986460,-147466327],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzcxNzkzM2M0MGZiZjkzNmFhOTI4ODUxM2VmZTE5YmRhNDYwMWVmYzBlNGVjYWQyZTAyM2IwYzFkMjg0NDRiIn19fQ==\"}]}}}";
    public static String FrozenWarriorHelmet = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;-968937912,-1382069675,-2122703197,-730591545],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZiMTg4NDhlM2FlMjhhZDJmODE4YjUxMDdhYmY5MGEyM2E0ZGE3MzM2MDRiZmZjZjZjYWUxMDI3NDg1MGFjNCJ9fX0=\"}]}}}";
    public static String MythicWarriorHelmet = "{display:{Name:\"{\\\"text\\\":\\\"Golden Knight Helmet\\\"}\"},SkullOwner:{Id:[I;1182945815,391332355,-1340799932,119225895],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNmNjllZjI3ZjMxMjBhNTEwNTBlNmJlNjZhMGUzZjhlODlhZjg4OGQ0ODA2NzE2YmY4NmY1OTBkYTYzODMxNyJ9fX0=\"}]}}}";

    public static ItemStack GetCustomHead(String value)
    {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        return Bukkit.getUnsafe().modifyItemStack(skull, value);
    }

}

