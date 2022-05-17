/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

public class ColorUtil {

    public static String ConvertToCustom(String text)
    {
        String finalText = "";

            if(text.split("!").length == 2)
            {
                finalText = (ChatColor.of(text.split("!")[0]) + text.split("!")[1]);
            }
            else
            {
                Bukkit.getLogger().info("[mrpg] Cannot read the custom name");
                Bukkit.getLogger().info("Name was "+text+" args length - "+text.split("!").length);
            }

        return finalText;
    }
}
