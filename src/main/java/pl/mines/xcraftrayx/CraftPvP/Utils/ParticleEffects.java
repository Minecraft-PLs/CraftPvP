package pl.mines.xcraftrayx.CraftPvP.Utils;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;

public class ParticleEffects
{
	public static void runHelix(Location loc)
	{
        double radius = 5;
   
        for (double y = 5; y >= 0; y -= 0.007) {
            radius = y / 3;
            double x = radius * Math.cos(3 * y);
            double z = radius * Math.sin(3 * y);
       
            double y2 = 5 - y;
       
            Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
//    		for(Player p : Bukkit.getOnlinePlayers())
//    		{
    			loc2.getWorld().spawnParticle(Particle.END_ROD, loc2, 1);
//    		}
        }
   
        for (double y = 5; y >= 0; y -= 0.007) {
            radius = y / 3;
            double x = -(radius * Math.cos(3 * y));
            double z = -(radius * Math.sin(3 * y));
       
            double y2 = 5 - y;
       
            Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
//    		for(Player p : Bukkit.getOnlinePlayers())
//    		{
//            Particle particle = Particle.REDSTONE;
//
//            Entity entity = loc2.getWorld().spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
//
//            AreaEffectCloud cloud = (AreaEffectCloud)entity;

//            try
//            {
//                cloud.setParticle(particle);
//            }
//            catch(Exception e)
//            {
//            	e.printStackTrace();
//            }
    			loc2.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, new DustOptions(Color.RED, 1));
//    		}
        }
    }
}