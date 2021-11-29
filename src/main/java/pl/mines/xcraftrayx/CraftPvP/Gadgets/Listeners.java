package pl.mines.xcraftrayx.CraftPvP.Gadgets;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import pl.mines.xcraftrayx.CraftPvP.Config;

public class Listeners implements Listener
{
	@EventHandler
	public void onStickClick(PlayerInteractEvent e)
	{
	    Player p = e.getPlayer();
	    ItemStack itemInHand = p.getInventory().getItemInMainHand();

	    if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getHand() != EquipmentSlot.OFF_HAND)
	    {
		    if((itemInHand.getType() == Material.getMaterial(Config.thorItem)) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasDisplayName()) && (itemInHand.getItemMeta().getDisplayName().equals(Config.mThorName)))
		    {
		    	if(p.hasPermission("CraftPvP.Gadgets.Thor") || p.hasPermission("CraftPvP.Admin"))
		    	{
		    		if(p.hasPermission("CraftPvP.Admin"))
		    		{
		    			e.getPlayer().getWorld().strikeLightning(e.getPlayer().getTargetBlock(null, 150).getLocation());
		    		}
		    		else if(Gadget.playersThor.contains(p.getName()))
		    		{
		    			p.sendMessage(Config.mThorNotYetToUse);
		    		}
		    		else
		    		{
		    			e.getPlayer().getWorld().strikeLightning(e.getPlayer().getTargetBlock(null, 150).getLocation());
		    			p.sendMessage(Config.mThorWaitToUse);
		    			
		    			if(!Gadget.playersThor.contains(p.getName()))
		    			{
		    				Gadget.playersThor.add(p.getName());
		    				Gadget.bukkitRunnableThor(p);
		    			}
		    		}
		      }
		      else
		      {
		    	  p.sendMessage(Config.mThorNoPerm);
		      }
		    }
		    else if((itemInHand.getType() == Material.getMaterial(Config.fireBallItem)) && (itemInHand.hasItemMeta()) && (itemInHand.getItemMeta().hasDisplayName()) && (itemInHand.getItemMeta().getDisplayName().equals(Config.mFireBallName)))
		    {
		    	if (p.hasPermission("CraftPvP.Gadgets.FireBall"))
		    	{
		    		if (p.hasPermission("CraftPvP.Admin"))
		    		{
		    			Fireball f = (Fireball)e.getPlayer().launchProjectile(Fireball.class);
		    			f.setIsIncendiary(false);
		    			f.setTicksLived(140);
		    			f.setYield(0.0F);
		    		}
	    			else if (Gadget.playersFireBall.contains(p.getName()))
	    			{
	    				p.sendMessage(Config.mFireBallNotYetToUse);
	    			}
	    			else
	    			{
	    				Fireball f = (Fireball)e.getPlayer().launchProjectile(Fireball.class);
	    				f.setIsIncendiary(false);
	    				f.setYield(0.0F);
	    				f.setTicksLived(200);
	          
	    				if (p.hasPermission("CraftPvP.Gadgets.FireBallVIP") || p.hasPermission("CraftPvP.Admin"))
	    				{
	    					p.sendMessage(Config.mFireBallWaitToUseVIP);
	    				}
	    				else
	    				{
	    					p.sendMessage(Config.mFireBallWaitToUse);
	    				}
	          
	    				if (!Gadget.playersFireBall.contains(p.getName()))
	    				{
	    					Gadget.playersFireBall.add(p.getName());
	    					Gadget.bukkitRunnableFireBallVIP(p);
	    				}
	    			}
		    	}
		    	else
		    	{
		    		p.sendMessage(Config.mFireBallNoPerm);
		    	}
		    }
	    }
	}
}