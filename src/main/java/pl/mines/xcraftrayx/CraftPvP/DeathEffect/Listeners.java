package pl.mines.xcraftrayx.CraftPvP.DeathEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;


public class Listeners implements Listener
{
	@EventHandler
    public void PlayerDamageReceive(EntityDamageByEntityEvent e)
	{
        if(e.getEntity() instanceof Player)
        {
            Player p = (Player) e.getEntity();
            LocalPlayer lp = WorldGuardPlugin.inst().wrapPlayer(p);
            
            if((p.getHealth() - e.getFinalDamage()) <= 0)
            {
    			Location loc = p.getLocation();
    			
    		    //boolean invincibleFlag = wg.getRegionManager(loc.getWorld()).getApplicableRegions(loc).allows(DefaultFlag.INVINCIBILITY);
    		    
    		    RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
    		    
    		    if(!e.isCancelled() && loc.getWorld().getPVP() && query.testState(lp.getLocation(), lp, Flags.PVP))
    		    {
				    if (p.hasPermission("CraftPvP.DeathEffect.VIP"))
				    {
					    for (int i = 1; i <= 3; i++)
					    {
					        Firework firework = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
					        FireworkMeta meta = firework.getFireworkMeta();
					        FireworkEffect effect = null;
					        if (i == 1)
					        {
					        	effect = FireworkEffect.builder().flicker(true).trail(true).withColor(Color.AQUA).withColor(Color.WHITE).with(FireworkEffect.Type.BALL).build();
					        	meta.setPower(1);
					        }
					        else if (i == 2)
					        {
					        	effect = FireworkEffect.builder().flicker(true).trail(true).withColor(Color.RED).withColor(Color.WHITE).with(FireworkEffect.Type.BALL_LARGE).build();
					        	meta.setPower(2);
					        }
					        else if (i == 3)
					        {
					        	effect = FireworkEffect.builder().flicker(true).trail(true).withColor(Color.BLUE).withColor(Color.WHITE).with(FireworkEffect.Type.CREEPER).build();
					        	meta.setPower(3);
					        }
					        meta.addEffect(effect);
					        firework.setFireworkMeta(meta);
					    }
					}
					else
					{
					    Firework firework = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
					    FireworkMeta meta = firework.getFireworkMeta();
					    FireworkEffect effect = FireworkEffect.builder().flicker(false).trail(true).withColor(Color.LIME).withColor(Color.WHITE).with(FireworkEffect.Type.BALL).build();
					    meta.setPower(1);
					    meta.addEffect(effect);
					    firework.setFireworkMeta(meta);
		    		}
    			}
            }
        }
    }
}
