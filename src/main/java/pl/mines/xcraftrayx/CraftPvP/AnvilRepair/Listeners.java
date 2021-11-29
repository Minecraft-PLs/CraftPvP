package pl.mines.xcraftrayx.CraftPvP.AnvilRepair;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pl.mines.xcraftrayx.CraftPvP.Config;

public class Listeners implements Listener
{
	
	@EventHandler
	public void listener(InventoryClickEvent e)
	{
        Inventory inv = e.getInventory();
	    Player p = (Player)e.getWhoClicked();
	    
	    ItemStack[] item = {inv.getItem(0), inv.getItem(1), inv.getItem(2)};
	    
	    if(inv instanceof AnvilInventory)
	    {
	        inv = (AnvilInventory)inv;
		    if(e.getSlot() == 2)
		    {
		        if((item[0] != null) && (item[1] != null) && (item[2] != null) && (p.hasPermission("CraftPvP.AnvilRepair.VIP")))
			    {
			    	if(AnvilRepair.economy.getBalance(p) >= Config.specialCost)
			        {
				        e.setCancelled(true);
				        ItemStack result = item[2];
				        inv.clear();
				        inv.setItem(0, result);
				        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
				        
				        if(Config.blockLostXP)
				        {
				            p.setExp(p.getExp());
				        }
			        }
			    }
			    else if ((item[0] != null) && (item[1] != null) && (item[2] != null) && (!p.hasPermission("CraftPvP.AnvilRepair.VIP")))
			    {
			    	if(AnvilRepair.economy.getBalance(p) >= Config.defaultCost)
			        {
			            AnvilRepair.economy.withdrawPlayer(p, Config.defaultCost);
			            e.setCancelled(true);
			            ItemStack result = item[2];
			            inv.clear();
			            inv.setItem(0, result);
			            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
			            
				        if(Config.blockLostXP)
				        {
				            p.setExp(p.getExp());
				        }
			        }
			        else
			        {
			            p.sendMessage(Config.mWarnPlayerCanNotRepair.replace("%DefaultCost%", String.valueOf(Config.defaultCost)).replace("%Balance%", String.valueOf(AnvilRepair.economy.getBalance(p))));
			            p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1.0F, 1.0F);
			            e.setCancelled(true);
			        }
			    }
			    else
			    {
			        p.playSound(p.getLocation(), Sound.ENTITY_BAT_HURT, 1.0F, 1.0F);
			    }
		    }
	    }
    }
}
