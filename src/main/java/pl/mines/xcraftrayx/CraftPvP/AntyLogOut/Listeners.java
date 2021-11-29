package pl.mines.xcraftrayx.CraftPvP.AntyLogOut;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.Respawn.CorpseReborn;

public class Listeners implements Listener
{
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
	{
		if (e.getEntity() instanceof Player && (e.getDamager() instanceof Player || e.getDamager() instanceof Arrow))
		{

			Player pl = (Player)e.getEntity();
			LocalPlayer lp = WorldGuardPlugin.inst().wrapPlayer(pl);
			Location loc = lp.getLocation();
			
		    RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
		    
		    if(!e.isCancelled() && pl.getWorld().getPVP() && !query.testState(loc, lp, Flags.INVINCIBILITY) && query.testState(loc, lp, Flags.PVP))
		    {
				String playerName;
				boolean entityIsInList = false;
				boolean damagerIsInList = false;
	
				Player entityPlayer = (Player) e.getEntity();
				Player damagerPlayer = null;
				
				if(e.getDamager() instanceof Arrow)
				{
					if(((Arrow)e.getDamager()).getShooter() instanceof Player)
					{
						if(!entityPlayer.equals(e.getDamager()))
						{
							damagerPlayer = ((Player)((Arrow)e.getDamager()).getShooter());
						}
					}
					else
					{
						return;
					}
				}
				else
				{
					damagerPlayer = (Player) e.getDamager();
				}
	
				String entityName = entityPlayer.getName();
				String damagerName = damagerPlayer.getName();
			    
				for (int i = 0; i < AntyLogOut.playerInBattle.length; i++)
				{
					playerName = AntyLogOut.playerInBattle[i][0];
	
					if (playerName != null)
					{
						if (damagerName.equals(playerName) && damagerPlayer.getGameMode() != GameMode.CREATIVE
								&& damagerPlayer.getGameMode() != GameMode.SPECTATOR)
						{
							damagerIsInList = true;
							AntyLogOut.playerInBattle[i][1] = String.valueOf(Config.antyLogoutTime + 1);
						}
						if (entityName.equals(playerName) && entityPlayer.getGameMode() != GameMode.CREATIVE
								&& entityPlayer.getGameMode() != GameMode.SPECTATOR)
						{
							entityIsInList = true;
							AntyLogOut.playerInBattle[i][1] = String.valueOf(Config.antyLogoutTime + 1);
						}
					}
				}
	
				if (!entityIsInList && entityPlayer.getGameMode() != GameMode.CREATIVE && entityPlayer.getGameMode() != GameMode.SPECTATOR) // USTAWIAMY CZAS BO NIE MA ICH NA LISCIE
				{
					for (int i = 0; i < AntyLogOut.playerInBattle.length; i++)
					{
						if (AntyLogOut.playerInBattle[i][0] == null)
						{
							AntyLogOut.playerInBattle[i][0] = entityName;
							AntyLogOut.playerInBattle[i][1] = String.valueOf(Config.antyLogoutTime + 1);
							
							entityPlayer.sendMessage(Config.tag + Config.mJoinToFightMode.replace("%PlayerName%", damagerName));
							break;
						}
					}
				}
	
				if (!damagerIsInList && damagerPlayer.getGameMode() != GameMode.CREATIVE && damagerPlayer.getGameMode() != GameMode.SPECTATOR) // USTAWIAMY CZAS BO NIE MA ICH NA LISCIE
				{
					for (int i = 0; i < AntyLogOut.playerInBattle.length; i++)
					{
						if (AntyLogOut.playerInBattle[i][0] == null)
						{
							AntyLogOut.playerInBattle[i][0] = damagerName;
							AntyLogOut.playerInBattle[i][1] = String.valueOf(Config.antyLogoutTime + 1);
							
							damagerPlayer.sendMessage(Config.tag + Config.mJoinToFightMode.replace("%PlayerName%", entityName));
							break;
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e)
	{
		Player p = e.getPlayer();
		for(int i = 0; i < AntyLogOut.playerInBattle.length; i++)
		{
			if(AntyLogOut.playerInBattle[i][0] != null && AntyLogOut.playerInBattle[i][0].equals(p.getName()))
			{
				for(String command : AntyLogOut.blockedCommands)
				{
					if(e.getMessage().startsWith("/" + command))
					{
						p.sendMessage(Config.tag + Config.mWarnCanNotTeleportInFightMode);
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		for (int i = 0; i < AntyLogOut.playerInBattle.length; i++)
		{
			if (AntyLogOut.playerInBattle[i][0] != null && AntyLogOut.playerInBattle[i][0].equals(e.getPlayer().getName()))
			{
				CorpseReborn.playerDeath(e.getPlayer());
				
				Bukkit.broadcastMessage(Config.tag + Config.mPlayerLogOutDuringFight.replace("%PlayerName%", e.getPlayer().getName()));
				return;
			}
		}
	}
}