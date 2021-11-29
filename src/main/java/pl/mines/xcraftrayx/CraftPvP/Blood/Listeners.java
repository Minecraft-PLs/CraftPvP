package pl.mines.xcraftrayx.CraftPvP.Blood;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Listeners implements Listener
{
    @EventHandler
    public void listener(EntityDamageEvent e)
    {
    	Location loc = e.getEntity().getLocation();
    	com.sk89q.worldedit.util.Location locWG = BukkitAdapter.adapt(loc);
    	RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();

    	if(!e.isCancelled() && !query.testState(locWG, null, Flags.INVINCIBILITY))
    	{
    		loc.getWorld().playEffect(loc, Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
    	}
    }
}