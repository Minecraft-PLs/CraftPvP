package pl.mines.xcraftrayx.CraftPvP.ArrowTrails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import pl.mines.xcraftrayx.CraftPvP.CraftPvP;
import pl.mines.xcraftrayx.CraftPvP.Stats.Level;
import pl.mines.xcraftrayx.CraftPvP.Stats.UserManager;

public class ArrowTrails
{
    private static HashMap<String, Particle> particleForLevels = new HashMap<String, Particle>();
	public static ArrayList<Trail> trails = new ArrayList<Trail>();
  
    public static void run()
    {
	    new BukkitRunnable()
	    {
	    	public void run()
		    {
			    for(Iterator<Trail> t = trails.iterator(); t.hasNext();)
			    {
				    t.next().tick(t);
			    }
		    }
	    }.runTaskTimerAsynchronously(CraftPvP.getInstance(), 0, 2);
    }
  
	public static Particle getTrail(Player p)
	{
		if(p.hasPermission("CraftPvP.ArrowTrails.VIP"))
		{
			Particle ep = null;
			
			do {
				ep = Particle.values()[new Random().nextInt(Particle.values().length - 1)];
			}
			while(ep.toString().equalsIgnoreCase("CURRENT_DOWN") || ep.toString().equalsIgnoreCase("CRIT") || ep.toString().equalsIgnoreCase("LEGACY_BLOCK_CRACK") || ep.toString().equalsIgnoreCase("LEGACY_BLOCK_DUST") || ep.toString().equalsIgnoreCase("REDSTONE") || ep.toString().equalsIgnoreCase("BARRIER") || ep.toString().equalsIgnoreCase("FOOTSTEP") || ep.toString().equalsIgnoreCase("SUSPENDED_DEPTH") || ep.toString().equalsIgnoreCase("SUSPENDED") || ep.toString().equalsIgnoreCase("EXPLOSION_LARGE") || ep.toString().equalsIgnoreCase("SWEEP_ATTACK") || ep.toString().equalsIgnoreCase("EXPLOSION_HUGE") || ep.toString().equalsIgnoreCase("MOB_APPEARANCE") || ep.toString().equalsIgnoreCase("BLOCK_DUST") || ep.toString().equalsIgnoreCase("ITEM_CRACK") || ep.toString().equalsIgnoreCase("ITEM_TAKE") || ep.toString().equalsIgnoreCase("FALLING_DUST") || ep.toString().equalsIgnoreCase("BLOCK_CRACK"));
			return ep;
		}
		else
		{
			int playerLevel = Level.getPlayerLevel(UserManager.getUser(p.getName()).getXp());
			
			for(String levelString : particleForLevels.keySet())
			{
				if(levelString.contains("-"))
				{
					int levelMin = Integer.parseInt(levelString.substring(0, levelString.indexOf("-")));
					int levelMax = Integer.parseInt(levelString.substring(levelString.indexOf("-") + 1, levelString.length()));
					
					if(playerLevel >= levelMin && playerLevel <= levelMax)
					{
						return particleForLevels.get(levelString);
					}
				}
				else
				{
					if(Integer.parseInt(levelString) == playerLevel)
					{
						return particleForLevels.get(levelString);
					}
				}
			}
			return null;
		}
	}

	public static void setParticleForLevels(HashMap<String, Particle> data)
	{
		particleForLevels = data;
	}
}
