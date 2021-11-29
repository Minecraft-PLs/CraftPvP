package pl.mines.xcraftrayx.CraftPvP.AnvilRepair;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

public class AnvilRepair
{
	public static Economy economy;

	public static void registerVault()
	{
	    if(Bukkit.getPluginManager().getPlugin("Vault") instanceof Vault)
	    {
	        RegisteredServiceProvider<Economy> service = Bukkit.getServicesManager().getRegistration(Economy.class);
	        
	        if(service != null)
	        {
	            economy = service.getProvider();
	        }
	    }
	    else
	    {
	    	System.out.println("You must add Vault plugin to properly work AnvilRepair");
	    	Bukkit.shutdown();
	    }
	}
}