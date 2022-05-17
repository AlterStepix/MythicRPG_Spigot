/*
Copyright 2022 AlterStepix and Crutogurch

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0
*/
package alterstepix.mythicrpg.util;

import java.util.ArrayList;
import java.util.List;

public class getPossibleArgs {
    public static List<String> getPossible(List<String> raw, String key)
    {
        List<String> result = new ArrayList<>();
        for(String var : raw)
        {
            if(var.startsWith(key))
            {
                result.add(var);
            }
        }
        return result;
    }
}
