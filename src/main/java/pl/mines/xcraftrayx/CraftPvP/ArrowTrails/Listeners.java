package pl.mines.xcraftrayx.CraftPvP.ArrowTrails;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class Listeners implements Listener
{
	@EventHandler
	public void bowEvent(ProjectileLaunchEvent e)
	{
	    if (e.getEntity().getShooter() != null)
	    {
		    if (e.getEntity().getShooter() instanceof Player)
		    {
		    	if(e.getEntity() instanceof Arrow)
		    	{
		    		Player p = (Player) e.getEntity().getShooter();
		    		Trail tr = new Trail(ArrowTrails.getTrail(p));
		    		if(!ArrowTrails.trails.contains(tr))
		    		{
		    			ArrowTrails.trails.add(tr);
		    			ArrowTrails.trails.get(ArrowTrails.trails.size()-1).addArrow((Arrow) e.getEntity());
		    		}
		    	}
		    }
	    }
	}
	  
//	@EventHandler
//	public void hitEvent(ProjectileHitEvent e)
//	{
//	    Projectile projectile = e.getEntity();
//	    if (((projectile.getShooter() instanceof Player)) && ((projectile instanceof Arrow)))
//	    {
//	    	Player p = (Player)projectile.getShooter();
//	    	if (!p.hasPermission("group.gracz"))
//	    	{
//	    		ArrowTrail.projectiles.remove(projectile);
//	    		for(Iterator localIterator = Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext();) 
//	    		{
//	    			Player online = (Player)localIterator.next();
//	    			((CraftPlayer)online).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles(EnumParticle.LAVA, false, (float)projectile.getLocation().getX(), (float)projectile.getLocation().getY(), (float)projectile.getLocation().getZ(), 0.0F, 0.0F, 0.0F, 0.0F, 0, new int[0]));
//	    		}
//	    	}
//	    }
//	}
}
