package pl.mines.xcraftrayx.CraftPvP.TeleportOnJoin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.mines.xcraftrayx.CraftPvP.Config;

public class Listeners implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Location loc = new Location(Bukkit.getWorld(Config.teleportOnJoinWorld), Config.teleportOnJoinX, Config.teleportOnJoinY, Config.teleportOnJoinZ);
		loc.setPitch((float) Config.teleportOnJoinPitch);
		loc.setYaw((float) Config.teleportOnJoinYaw);
		e.getPlayer().teleport(loc);
	}
}
