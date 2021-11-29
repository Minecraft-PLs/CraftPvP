package pl.mines.xcraftrayx.CraftPvP.Gadgets;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.CraftPvP;


public class Gadget
{
	public static ArrayList<String> playersThor = new ArrayList<String>();
	public static ArrayList<String> playersFireBall = new ArrayList<String>();
	  
	public static void bukkitRunnableThor(final Player p)
	{
		Bukkit.getServer().getScheduler().runTaskLater(CraftPvP.getInstance(), new Runnable()
	    {
	        public void run()
	        {
	            if (playersThor.contains(p.getName()))
	            {
	            	p.sendMessage(Config.mThorUnlocked);
	            	playersThor.remove(p.getName());
	            }
	        }
	    }, 20 * Config.thorTimeToReUseInSec);
	}
	
	public static void bukkitRunnableFireBall(final Player p)
	{
		Bukkit.getServer().getScheduler().runTaskLater(CraftPvP.getInstance(), new Runnable()
	    {
	        public void run()
	        {
	            if (playersFireBall.contains(p.getName()))
	            {
	            	playersFireBall.remove(p.getName());
	            	p.sendMessage(Config.mFireBallUnlocked);
	            }
	        }
	    }, 20 * Config.fireBallTimeToReUseInSec);
	}
	
	public static void bukkitRunnableFireBallVIP(final Player p)
	{
		Bukkit.getServer().getScheduler().runTaskLater(CraftPvP.getInstance(), new Runnable()
	    {
	        public void run()
	        {
	            if (playersFireBall.contains(p.getName()))
	            {
	            	playersFireBall.remove(p.getName());
	            	p.sendMessage(Config.mFireBallUnlocked);
	            }
	        }
	    }, 20 * Config.fireBallTimeToReUseInSecByVIP);
	}
}