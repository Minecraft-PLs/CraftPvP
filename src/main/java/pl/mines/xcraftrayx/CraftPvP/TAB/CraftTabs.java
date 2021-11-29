package pl.mines.xcraftrayx.CraftPvP.TAB;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import pl.mines.xcraftrayx.CraftPvP.CraftPvP;

public class CraftTabs extends JavaPlugin
{
	public static CraftTabs instance;
	final static File essentialsEcoFolder = new File("plugins/Essentials/userdata/");
    
    public static void updater()
    {
    	Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(CraftPvP.getInstance(), new Runnable()
    	{
			public void run()
			{
				for(Player p: Bukkit.getOnlinePlayers())
				{
					Utils.updatePlayerTab(p);
				}
			}
		}, 0, 20);
    }
}