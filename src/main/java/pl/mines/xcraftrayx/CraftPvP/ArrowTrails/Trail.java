package pl.mines.xcraftrayx.CraftPvP.ArrowTrails;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;

public class Trail
{
	private Particle e;
	
	private ArrayList<Arrow> arrows = new ArrayList<Arrow>();
	
	public Trail(Particle e)
	{
		this.e = e;
	}
	
	public void addArrow(Arrow a)
	{
		arrows.add(a);
	}
	
	public void tick(Iterator<?> t)
	{
		for(Arrow a: arrows)
		{
			if(a.isOnGround() || a.isDead() || a == null)
			{
				arrows.remove(a);
				t.remove();
				return;
			}
			else
			{
				particle(a.getLocation());
			}
		}
	}
	
	private void particle(Location loc)
	{
//		for(Player p : Bukkit.getOnlinePlayers())
//		{
			loc.getWorld().spawnParticle(e, loc, 15);
//		}
	}
}
