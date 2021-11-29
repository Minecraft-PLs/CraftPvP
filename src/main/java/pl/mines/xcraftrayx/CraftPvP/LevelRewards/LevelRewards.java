package pl.mines.xcraftrayx.CraftPvP.LevelRewards;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import pl.mines.xcraftrayx.CraftPvP.Stats.UserManager;

public class LevelRewards
{
	//  LEVEL     ITEM
	public static HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();

    public static void giveReward(String userName, int level)
    {
    	UserManager.getUser(userName);
    	
    	if(items.containsKey(level))
    	{
    		Bukkit.getPlayer(userName).getInventory().addItem(items.get(level));
    	}
    }
}
