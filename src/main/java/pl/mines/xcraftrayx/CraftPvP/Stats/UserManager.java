package pl.mines.xcraftrayx.CraftPvP.Stats;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.CraftPvP;
import pl.mines.xcraftrayx.CraftPvP.Database.Database;
import pl.mines.xcraftrayx.CraftPvP.Scoreboards.Scoreboards;
import pl.mines.xcraftrayx.CraftPvP.Utils.Title;

public class UserManager
{
	public static ArrayList<User> usersList = new ArrayList<User>();
	
	public static void addUser(String userName, int kills, int deaths, int assists, double kdr, int xp)
	{
		usersList.add(new User(userName, kills, deaths, assists, kdr, xp));
		Database.insertUser(userName, kills, deaths, assists, kdr, xp);
	}
	
	public static User getUser(String userName)
	{
		for(User u : usersList)
		{
			if(u.getUserName().equals(userName))
			{
				return u;
			}
		}
		return null;
	}
	
	public static void removeUser(String userName)
	{
		usersList.remove(getUser(userName));
	}
	
	public static void addKills(String userName, int kills)
	{
		User u = getUser(userName);
		
		if(u != null)
		{
			Level.checkLevelUp(userName, u.getXp(), Config.xpForKill);
			u.addKills(kills);
			u.addXp(Config.xpForKill);
			setKDR(userName);
			pushUserToMySQL(u);
		}
		else
		{
			addUser(userName, 1, 0, 0, 1, Config.xpForKill);
			Level.checkLevelUp(userName, 0, Config.xpForKill);
		}

		Scoreboards.refreshScoreboard(Bukkit.getPlayer(userName));
	}
	
	public static void addKillsMob(int xp, String userName)
	{
		User u = getUser(userName);
		
		
		if(u != null)
		{
			Level.checkLevelUp(userName, u.getXp(), xp);
			u.addXp(xp);
			pushUserToMySQL(u);
		}
		else
		{
			addUser(userName, 0, 0, 0, 0, xp);
			Level.checkLevelUp(userName, 0, xp);
		}

		Scoreboards.refreshScoreboard(Bukkit.getPlayer(userName));
	}
	
	public static void addDeaths(String userName, int deaths, String killer)
	{
		User u = getUser(userName);
		if(u != null)
		{
			u.addDeaths(deaths);
			
			for(Player player : Bukkit.getOnlinePlayers())
			{
				for(String assister : u.assistersOfDeath)
				{
					if(player.getName().equals(assister) && !player.getName().equals(killer))
					{
						addAssists(player.getName(), 1);
					    Title.sendTitle(player, 10, 50, 20, Config.mPlayerAssisted.replace("%XP%", String.valueOf(Config.xpForAssists)), Config.tag + Config.mPlayerAssistedSub.replace("%UserName%", userName).replace("%Level%", String.valueOf(Level.getPlayerLevel(UserManager.getUser(userName).getXp()))));
					}
				}
			}
			u.assistersOfDeath.clear();
			setKDR(userName);
			pushUserToMySQL(u);
		}
		else
		{
			addUser(userName, 0, 1, 0, 0, 0);
		}
		Scoreboards.refreshScoreboard(Bukkit.getPlayer(userName));
	}
	
	public static void addAssists(String userName, int assists)
	{
		User u = getUser(userName);
		
		if(u != null)
		{
			Level.checkLevelUp(userName, u.getXp(), Config.xpForAssists);
			usersList.get(usersList.indexOf(u)).addAssists(assists);
			u.addXp(Config.xpForAssists);
			pushUserToMySQL(u);
		}
		else
		{
			addUser(userName, 0, 0, 1, 0, Config.xpForAssists);
			Level.checkLevelUp(userName, 0, Config.xpForAssists);
		}
		Scoreboards.refreshScoreboard(Bukkit.getPlayer(userName));
	}
	
	public static void addAssisterToList(String userName, String assister)
	{
		User u = getUser(userName);
		
		if(u != null)
		{
			usersList.get(usersList.indexOf(u)).assistersOfDeath.add(assister);
			removeAssisterTask(u.getUserName(), assister);
		}
		else
		{
			addUser(userName, 0, 0, 0, 0, 0);
		}
	}
	
	public static void removeAssisterTask(final String userName, final String assister)
	{
		Bukkit.getScheduler().runTaskLater(CraftPvP.getInstance(), new Runnable()
		{
			public void run()
			{
				
				User user = getUser(userName);
				
				if(user != null && !user.assistersOfDeath.isEmpty())
				{
					for(String assisters : user.assistersOfDeath)
					{
						if(assisters.equals(assister))
						{
							user.assistersOfDeath.remove(assister);
							break;
						}
				}
				}
			}
		}, 20 * 15);
	}
	
	public static void setKDR(String userName)
	{
		User u = getUser(userName);
		int kills = u.getKills();
		int deaths = u.getDeaths();
		
		double kdr;
		
		if(deaths == 0)
		{
			kdr = kills;
		}
		else
		{
			kdr = (double)kills/(double)deaths;
		}
		
		BigDecimal killsDecimal = BigDecimal.valueOf(kills);
		BigDecimal deathsDecimal = BigDecimal.valueOf(deaths);
		if(deaths == 0)
		{
			kdr = kills;
		}
		else
		{
			kdr = killsDecimal.divide(deathsDecimal, 3, 4).doubleValue();
		}
		
		u.setKDR(kdr);
		pushUserToMySQL(u);
	}
	
	public static void pushUserToMySQL(User u)
	{
		Database.updateUser(u.getUserName(), u.getKills(), u.getDeaths(), u.getAssists(), u.getKDR(), u.getXp());
	}
	
	public static void loadUsers()
	{
		ArrayList<User> users = Database.getUsers();
		usersList = users;
	}
}