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
