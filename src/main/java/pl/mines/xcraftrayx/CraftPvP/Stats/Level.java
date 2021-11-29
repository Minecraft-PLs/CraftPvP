package pl.mines.xcraftrayx.CraftPvP.Stats;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.LevelRewards.LevelRewards;
import pl.mines.xcraftrayx.CraftPvP.Utils.ParticleEffects;

public class Level
{
	public static int getPlayerLevel(int points)
	{
		int i = 0, pLevel = 0;
		for(int lvl = 1; lvl <= 1000000; lvl++)
		{
			i = i + lvl;
			
			if(points >= i * Config.levelOfCoreValue)
			{
				pLevel = lvl;
			}
			else
			{
				break;
			}
		}
		return pLevel;
	}
	
	public static int getXpFromLevel(int level)
	{
		if(level == 0) return 0;
		
		int xp = level * Config.levelOfCoreValue;
		for(int i = 1; i < level; i++)
		{
			xp = xp + (i * Config.levelOfCoreValue);
		}
		return xp;
	}
	
	public static void playerLevelUp(String userName, int newLevel)
	{
		LevelRewards.giveReward(userName, newLevel);
		
		Player pl = Bukkit.getPlayer(userName);
		
		if(pl.isOnline())
		{
			ParticleEffects.runHelix(pl.getPlayer().getLocation());
			pl.getPlayer().getLocation().getWorld().playSound(pl.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, (float) 0.6);
		}
	}

	public static void checkLevelUp(String userName, int xp, int addedXp)
	{
		int oldPlayerLevel = getPlayerLevel(xp);
		int newPlayerLevel = getPlayerLevel(xp+addedXp);
		
		if(oldPlayerLevel != newPlayerLevel)
		{
			playerLevelUp(userName, newPlayerLevel);
		}
		
	}
}
