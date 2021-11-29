package pl.mines.xcraftrayx.CraftPvP.Respawn;

import java.util.logging.Level;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerQuitEvent;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import me.realized.duels.DuelsPlugin;
import pl.mines.xcraftrayx.CraftPvP.CraftPvP;

public class Listeners implements Listener
{
	@EventHandler
    public void PlayerDamageReceive(EntityDamageByEntityEvent e)
	{
        if(e.getEntity() instanceof Player)
        {
    		Player p = (Player)e.getEntity();
    		LocalPlayer lp = WorldGuardPlugin.inst().wrapPlayer(p);

    	    RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();

    	    if(query.testState(lp.getLocation(), lp, Flags.INVINCIBILITY) || !query.testState(lp.getLocation(), lp, Flags.PVP))
    	    {
    			return;
    		}
    		
            final Player damaged = (Player) e.getEntity();
            
			if(CraftPvP.getInstance().getServer().getPluginManager().getPlugin("Duels") == null || CraftPvP.getInstance().getServer().getPluginManager().getPlugin("Duels").isEnabled() == false || !DuelsPlugin.getInstance().getArenaManager().isInMatch((damaged)))
        	{
	            if((damaged.getHealth() - e.getFinalDamage()) <= 0)
	            {
	            	if(!CorpseReborn.respawnPlayerList.containsKey(damaged.getName()))
	            	{
	            		e.setCancelled(true);
	                	CorpseReborn.playerDeath(damaged);
	            	}
	            }
        	}
        }
    }
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
            final Player damaged = (Player) e.getEntity();
            
            //CHECK DUELS IS ON SERVER / IS ENABLED / PLAYERS IS IN ARENA
			if(CraftPvP.getInstance().getServer().getPluginManager().getPlugin("Duels") == null || CraftPvP.getInstance().getServer().getPluginManager().getPlugin("Duels").isEnabled() == false || !DuelsPlugin.getInstance().getArenaManager().isInMatch(damaged))
        	{
	            if((damaged.getHealth() - e.getFinalDamage()) <= 0 && e.getCause() != DamageCause.PROJECTILE && e.getCause() != DamageCause.ENTITY_SWEEP_ATTACK && e.getCause() != DamageCause.ENTITY_EXPLOSION && e.getCause() != DamageCause.ENTITY_ATTACK)
	            {
	            	if(!CorpseReborn.respawnPlayerList.containsKey(damaged.getName()))
	            	{
	            		e.setCancelled(true);
	                	CorpseReborn.playerDeath(damaged);
	            	}
	            }
        	}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		if(e.getPlayer().getGameMode() != GameMode.SURVIVAL)
		{
			e.getPlayer().setGameMode(GameMode.SURVIVAL);
		}
	}
}