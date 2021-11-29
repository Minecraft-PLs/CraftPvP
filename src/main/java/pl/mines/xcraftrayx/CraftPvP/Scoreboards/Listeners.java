package pl.mines.xcraftrayx.CraftPvP.Scoreboards;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import pl.mines.xcraftrayx.CraftPvP.Stats.Level;
import pl.mines.xcraftrayx.CraftPvP.Stats.User;
import pl.mines.xcraftrayx.CraftPvP.Stats.UserManager;

public class Listeners implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		Scoreboards.createScoreBoard(p);
		
		User u = UserManager.getUser(p.getName());
		int lvl = 0;
		if(u != null)
		{
			lvl = Level.getPlayerLevel(u.getXp());
		}
		//WHAT IS DOING? :P
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		Scoreboards.scoreboard.remove(e.getPlayer().getName());
	}
}
