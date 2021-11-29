package pl.mines.xcraftrayx.CraftPvP.Scoreboards;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.CraftPvP;
import pl.mines.xcraftrayx.CraftPvP.Stats.Level;
import pl.mines.xcraftrayx.CraftPvP.Stats.User;
import pl.mines.xcraftrayx.CraftPvP.Stats.UserManager;

public class Scoreboards
{
	
	public static HashMap<String, Scoreboard> scoreboard = new HashMap<String, Scoreboard>();
	
	public static void createScoreBoard(Player p)
	{	
		String playerName = p.getName();
		User u = UserManager.getUser(playerName);
		
		int level = 0;
		int kills = 0;
		int deaths = 0;
		int assists = 0;
		double kdr = 0;
		
		if(u != null)
		{
			level = Level.getPlayerLevel(u.getXp());
			kills =  u.getKills();
			deaths =  u.getDeaths();
			assists =  u.getAssists();
			kdr =  u.getKDR();
		}
		
		if(!scoreboard.containsKey(playerName))
		{
			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
			
			Objective obj = board.registerNewObjective(playerName, "dummy");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			obj.setDisplayName(Config.tag);
			
			//Score pause6 = obj.getScore(ChatColor.BOLD + "" + ChatColor.BLACK);
			//pause6.setScore(14);
	
			String name;
			if(playerName.length() > 14)
			{
				name = playerName.substring(0, 15);
			}
			else if(playerName.length() == 14)
			{
				name = ChatColor.YELLOW.toString() + playerName;
			}
			else
			{
				name = ChatColor.YELLOW.toString() + ChatColor.BOLD.toString() + playerName;
			}
			
			Score onlineName = obj.getScore(name);
			onlineName.setScore(15);
			
			Score levell = obj.getScore(ChatColor.GREEN + Config.mScoreboardLevel);
			levell.setScore(14);
			
			Team levelStats = board.registerNewTeam("level");
			levelStats.addEntry(ChatColor.DARK_RED + "" + ChatColor.BLACK + "");
			levelStats.setPrefix(String.valueOf(ChatColor.GREEN + "> "+ ChatColor.BLUE + level));
			
			obj.getScore(ChatColor.DARK_RED + "" + ChatColor.BLACK + "").setScore(13);
			
			Score pause0 = obj.getScore("");
			pause0.setScore(12);
	
//			Score pause5 = obj.getScore(ChatColor.YELLOW + "");
//			pause5.setScore(15);
			
			Score killss = obj.getScore(ChatColor.GREEN + Config.mScoreboardKills);
			killss.setScore(11);
			
			Team killsStat = board.registerNewTeam("kills");
			killsStat.addEntry(ChatColor.YELLOW + "" + ChatColor.WHITE + "");
			killsStat.setPrefix(String.valueOf(ChatColor.GREEN + "> " + ChatColor.GOLD + kills));
			obj.getScore(ChatColor.YELLOW + "" + ChatColor.WHITE + "").setScore(10);
			
			Score pause4 = obj.getScore(ChatColor.WHITE + "");
			pause4.setScore(9);
			
			Score pause3 = obj.getScore(ChatColor.GREEN + Config.mScoreboardDeaths);
			pause3.setScore(8);
			
			Team deathss = board.registerNewTeam("deaths");
			deathss.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE + "");
			deathss.setPrefix(String.valueOf(ChatColor.GREEN + "> " + ChatColor.RED + deaths));
			obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE + "").setScore(7);
			
			Score pause2 = obj.getScore(ChatColor.BLACK.toString() + ChatColor.YELLOW.toString());
			pause2.setScore(6);
			
			Score assistss = obj.getScore(ChatColor.GREEN + Config.mScoreboardAssists);
			assistss.setScore(5);
			
			Team assistsStats = board.registerNewTeam("assists");
			assistsStats.addEntry(ChatColor.YELLOW + "" + ChatColor.BLACK + "");
			assistsStats.setPrefix(String.valueOf(ChatColor.GREEN + "> " + ChatColor.AQUA + assists));
			obj.getScore(ChatColor.YELLOW + "" + ChatColor.BLACK + "").setScore(4);
			
			Score pause1 = obj.getScore(ChatColor.BLACK + "");
			pause1.setScore(3);
			
			Score kdrr = obj.getScore(ChatColor.GREEN + Config.mScoreboardKDR);
			kdrr.setScore(2);
			
			Team kdrStats = board.registerNewTeam("kdr");
			kdrStats.addEntry(ChatColor.LIGHT_PURPLE + "" + ChatColor.BLACK + "");
			kdrStats.setPrefix(String.valueOf(ChatColor.GREEN + "> "+ ChatColor.YELLOW + kdr));
			obj.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.BLACK + "").setScore(1);
			
			scoreboard.put(playerName, board);
			p.setScoreboard(board);
			
			//HEALTH
			Objective objective = p.getScoreboard().registerNewObjective("HealthBar", "health");
			objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
			objective.setDisplayName(Config.mScoreboardHealth);
			p.setHealth(p.getHealth());
		}
	}
	
	public static void refreshScoreboard(final Player p)
	{
		if(p == null) return;
		
		String playerName = p.getName();
		Scoreboard board = p.getScoreboard();
		
		if(!board.getTeams().isEmpty())
		{
			if(p.getScoreboard().getObjective(p.getName()) != null)
			{
				User u = UserManager.getUser(playerName);
				
				int level = 0;
				int kills = 0;
				int deaths = 0;
				int assists = 0;
				double kdr = 0;
				
				if(u != null)
				{
					level = Level.getPlayerLevel(u.getXp());
					kills =  u.getKills();
					deaths =  u.getDeaths();
					assists =  u.getAssists();
					kdr =  u.getKDR();
				}

				board.getTeam("level").setPrefix(String.valueOf(ChatColor.GREEN + "> "+ ChatColor.BLUE + level));
				board.getTeam("kills").setPrefix(String.valueOf(ChatColor.GREEN + "> " + ChatColor.GOLD + kills));
				board.getTeam("deaths").setPrefix(String.valueOf(ChatColor.GREEN + "> " + ChatColor.RED + deaths));
				board.getTeam("assists").setPrefix(String.valueOf(ChatColor.GREEN + "> " + ChatColor.AQUA + assists));
				board.getTeam("kdr").setPrefix(String.valueOf(ChatColor.GREEN + "> "+ ChatColor.YELLOW + kdr));
			}
		}
	}
	
	public static void setScoreboard(Player p)
	{
		if(Bukkit.getPlayer(p.getUniqueId()).isOnline() && scoreboard.containsKey(p.getName()))
		{
			p.setScoreboard(scoreboard.get(p.getName()));
		}
	}
	
	static boolean refresherTask = false;
	public static void runTaskScoreboardVisable()
	{
		if(refresherTask == false)
		{
			Bukkit.getScheduler().runTaskTimer(CraftPvP.getInstance(), new Runnable()
			{	
				public void run()
				{
					refresherTask = true;
					for(Player p : Bukkit.getOnlinePlayers())
					{
						if((p.getScoreboard().getObjectives().isEmpty()))
						{
							setScoreboard(p);
							refreshScoreboard(p);
						}
					}
				}
			}, 0, 20);
		}
	}
}