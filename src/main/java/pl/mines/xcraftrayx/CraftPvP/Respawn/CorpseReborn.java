package pl.mines.xcraftrayx.CraftPvP.Respawn;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.golde.bukkit.corpsereborn.CorpseAPI.CorpseAPI;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.CraftPvP;
import pl.mines.xcraftrayx.CraftPvP.Utils.Title;

public class CorpseReborn
{
	public static HashMap<String, Integer> respawnPlayerList = new HashMap<String, Integer>();
	
	public static void spawnCorpose(Player p)
	{
		new CorpseAPI().spawnCorpse(p, p.getName(), p.getLocation(), null, p.getInventory().getHelmet(), p.getInventory().getChestplate(), p.getInventory().getLeggings(), p.getInventory().getBoots(), p.getInventory().getItemInMainHand());
	}
	
	
	public static void playerDeath(Player p)
	{
		final String playerName = p.getName();
		
        if(!respawnPlayerList.containsKey(playerName))
        {
        	respawnPlayerList.put(playerName, 5);
    		
            spawnCorpose(p);
            
    		for(ItemStack i : p.getInventory().getContents())
    		{
    			if(i != null)
    			{
    				p.getWorld().dropItem(p.getLocation(), i);
    			}
    		}
            p.getInventory().clear();
            
			String message = Config.mRespawnOnDeath.replace("%Time%", String.valueOf(respawnPlayerList.get(playerName)));
			String subMessage = Config.mRespawnOnDeathSub;
			
    		p.setGameMode(GameMode.SPECTATOR);
    		
	        Title.sendTitle(p, 0, 50, 30, message, subMessage);
        }
        
    	Bukkit.getScheduler().runTaskLater(CraftPvP.getInstance(), new Runnable()
    	{
			public void run()
			{	
				if(p.isOnline())
				{
					if(respawnPlayerList.get(playerName) == 1)
					{
						respawnPlayerList.remove(playerName);
						
						String message = Config.mRespawnBackToSpawn;
						String subMessage = Config.mRespawnBackToSpawnSub;
						
						p.setSneaking(true);
						p.setSneaking(false);
								
						p.setGameMode(GameMode.SURVIVAL);
						Location loc = new Location(Bukkit.getWorld(Config.respawnWorld), Config.respawnX, Config.respawnY, Config.respawnZ);
						loc.setPitch((float) Config.respawnPitch);
						loc.setYaw((float) Config.respawnYaw);
				        p.teleport(loc);
				        p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				        
				        Title.sendTitle(p, 0, 50, 30, message, subMessage);
					}
					else
					{
						respawnPlayerList.put(playerName, respawnPlayerList.get(playerName)-1);
						String message = Config.mRespawnOnDeath.replace("%Time%", String.valueOf(respawnPlayerList.get(playerName)));
						String subMessage = Config.mRespawnOnDeathSub;
	
						Title.sendTitle(p, 0, 50, 30, message, subMessage);
						
						playerDeath(p);
					}
				}
				else
				{
					respawnPlayerList.remove(playerName);
				}
			}
		},20);
	}
}