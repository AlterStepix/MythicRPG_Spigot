package alterstepix.mythicrpg.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GetPlayerHead {

    public static String GrandmasterHelmet = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;1165266987,715278077,-1144161251,-894625357],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVlYjBiZDg1YWFkZGYwZDI5ZWQwODJlYWMwM2ZjYWRlNDNkMGVlODAzYjBlODE2MmFkZDI4YTYzNzlmYjU0ZSJ9fX0=\"}]}}}";
    public static String NetherCatalyst = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;-1571577115,-1110423922,-1407986460,-147466327],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzcxNzkzM2M0MGZiZjkzNmFhOTI4ODUxM2VmZTE5YmRhNDYwMWVmYzBlNGVjYWQyZTAyM2IwYzFkMjg0NDRiIn19fQ==\"}]}}}";
    public static String FrozenWarriorHelmet = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;-968937912,-1382069675,-2122703197,-730591545],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmZiMTg4NDhlM2FlMjhhZDJmODE4YjUxMDdhYmY5MGEyM2E0ZGE3MzM2MDRiZmZjZjZjYWUxMDI3NDg1MGFjNCJ9fX0=\"}]}}}";
    public static String MythicWarriorHelmet = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;1182945815,391332355,-1340799932,119225895],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNmNjllZjI3ZjMxMjBhNTEwNTBlNmJlNjZhMGUzZjhlODlhZjg4OGQ0ODA2NzE2YmY4NmY1OTBkYTYzODMxNyJ9fX0=\"}]}}}";
    public static String CursedEmperorHead = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;-1919258451,1811891575,-1461357782,-1970630537],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDQ0YjY1M2JiYjcyN2I1Yzg2NWE4ZWZjNzUwOTJhZmU5MzllMmMzZTY4NjEzZGI5YWJhZTRmMWI2NGY5OWY4In19fQ==\"}]}}}";
    public static String FrozenSoulHead = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;-373202936,1077166576,-2126954867,774551255],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTEzYzI1NjQwNzA1MTM2Y2Y3NzY3ODQ5MTBjNjNhMGYxNjBmNWFmNmEzOGNjYWNmYmIzMDcxZjI0N2I4MjAwNCJ9fX0=\"}]}}}";
    public static String RevenantArcherHead = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;146631858,-1940634717,-2084048776,314592679],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYzMzVlMTRmNDNmMjFmNjgxMmNkOWNhY2Q1MDJmN2JiYzA2NzUzMjVhZDYwMTM5YTg4MzY3NDU2NzI4ZmRlNSJ9fX0=\"}]}}}";
    public static String ThiefHood = "{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;1739266772,-1638839473,-1286252193,1578750174],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmZkNDUxOGRiMzVhYThhYTBjNTlkOTY4MzRkZWQ3OWI0NmM5OTFlODM0M2Q3MjZjMjAzMWYxYWM1NWZkNzc5ZSJ9fX0=\"}]}}}";
    public static String CyclopsHead="{display:{Name:\"{\\\"text\\\":\\\"MRPG\\\"}\"},SkullOwner:{Id:[I;-1985959630,1862550733,-1349409951,-239765540],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQzZDhmNzc0NDAxOWYyM2I3ZmQ2NGJjYmM4YTJhMTZmOGFjMjFlZWRkMTE4NjVkOTdiNmI2YzE5MGZiMWFmMCJ9fX0=\"}]}}}";
    public static String OverworldInvaderHead ="{display:{Name:\"{\\\"text\\\":\\\"Piglin with Red Headband\\\"}\"},SkullOwner:{Id:[I;656700737,1672432225,-1489592514,944422051],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWFjNmQ5NTk4Y2Y5OGY4M2FmODljNzViMTRhZmY5MGZmYzZiN2UyMmFlOGU2ZmRiODVlMWJjNWRmN2M1ZmQwZiJ9fX0=\"}]}}}";
    public static String BeastHelmet="{display:{Name:\"{\\\"text\\\":\\\"Dragon\\\"}\"},SkullOwner:{Id:[I;1752952309,-409448665,-1933320532,-396836580],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTc5NjA1OWE3NTM1OTNjNWI0MDhiODQ0ZjUxNjBjY2FiMDY5YWQzNGUwOTI3OGY2NGViY2ZmOGQwZTczODEyOSJ9fX0=\"}]}}}";
    public static String FrozenZombieHead="{display:{Name:\"{\\\"text\\\":\\\"Frozen Zombie\\\"}\"},SkullOwner:{Id:[I;1120320009,-1918352004,-1534688616,107302545],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ5MjhjY2I3Y2Y3NTg0MjVlYTM4YTVkNjBiZGE1MTY5ZjdkZmRhZjQyMTQ2Mzc0ZjllOWM5OTMyMDJmYTFiZSJ9fX0=\"}]}}}";
    public static String CorruptedMythicHelmet="{display:{Name:\"{\\\"text\\\":\\\"Ender Warrior\\\"}\"},SkullOwner:{Id:[I;-1856575242,-839498010,-1603498856,-2088027457],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODMxNTJiMzhkYzE0MjU4OGQxNGZkZDM4YWFhMGI1NGU2MTM4NjBmN2QxNTM5NTM1YjMyYzAxZWIyMjBmZTY3YiJ9fX0=\"}]}}}";
    public static String GlacialMythicHelmet="{display:{Name:\"{\\\"text\\\":\\\"Subzero Wisp\\\"}\"},SkullOwner:{Id:[I;1806529956,-728282034,-1143027417,2078042202],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2EwZWIzN2U1OGM5NDJlY2E0ZDMzYWI0NGUyNmViMTkxMGM3ODM3ODg1MTBiMGE1M2I2ZjRkMTg4ODFlMjM3ZSJ9fX0=\"}]}}}";


    public static ItemStack GetCustomHead(String value)
    {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        return Bukkit.getUnsafe().modifyItemStack(skull, value);
    }

}

