package pl.mines.xcraftrayx.CraftPvP.Stats;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cod;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Llama;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Mule;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Salmon;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Stray;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.CraftPvP;
import pl.mines.xcraftrayx.CraftPvP.Utils.Title;

public class Listeners implements Listener
{
	
	public int getRandomXP(String xpMob)
	{
		int xp = 0;
		
		xpMob = xpMob.trim();
		
		int xpMin;
		int xpMax;
		
		if(xpMob.contains("-"))
		{
			int indexOfMinus = xpMob.indexOf("-");
			
			xpMin = Integer.valueOf(xpMob.substring(0, indexOfMinus));
			xpMax = Integer.valueOf(xpMob.substring(indexOfMinus + 1, xpMob.length()));
			
			xp = new Random().nextInt(xpMax + 1 - xpMin) + xpMin;
		}
		else
		{
			xp = Integer.valueOf(xpMob);
		}
		
		return xp;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		String userName = e.getPlayer().getName();
		
		if(UserManager.getUser(userName) == null)
		{
			UserManager.addUser(userName, 0, 0, 0, 0, 0); //To jest update not instert
		}
	}
	@EventHandler
	public void mobDeath(EntityDeathEvent e)
	{
		if(Config.xpFromMobsIsEnabled)
		{
			if(e.getEntity().getKiller() instanceof Player)
			{
				int xp = 0;
				Entity en = e.getEntity();
				
				String userName = e.getEntity().getKiller().getName();
				
				if(en instanceof Bat)
				{
					xp = getRandomXP(Config.xpBat);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Chicken)
				{
					xp = getRandomXP(Config.xpChicken);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Cod)
				{
					xp = getRandomXP(Config.xpCod);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Cow)
				{
					xp = getRandomXP(Config.xpCow);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Donkey)
				{
					xp = getRandomXP(Config.xpDonkey);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Horse)
				{
					xp = getRandomXP(Config.xpHorse);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof MushroomCow)
				{
					xp = getRandomXP(Config.xpMushroomCow);
					UserManager.addKillsMob(xp, userName);	
				}
				else if(en instanceof Mule)
				{
					xp = getRandomXP(Config.xpMule);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Ocelot)
				{
					xp = getRandomXP(Config.xpOcelot);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Parrot)
				{
					xp = getRandomXP(Config.xpParrot);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Pig)
				{
					xp = getRandomXP(Config.xpPig);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Rabbit)
				{
					xp = getRandomXP(Config.xpRabbit);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Sheep)
				{
					xp = getRandomXP(Config.xpSheep);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof SkeletonHorse)
				{
					xp = getRandomXP(Config.xpSkeletonHorse);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Salmon)
				{
					xp = getRandomXP(Config.xpSalmon);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Squid)
				{
					xp = getRandomXP(Config.xpSquid);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Turtle)
				{
					xp = getRandomXP(Config.xpTurtle);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof TropicalFish)
				{
					xp = getRandomXP(Config.xpTropicalFish);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Villager)
				{
					xp = getRandomXP(Config.xpVillager);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Llama)
				{
					xp = getRandomXP(Config.xpLlama);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof PufferFish)
				{
					xp = getRandomXP(Config.xpPufferfish);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Dolphin)
				{
					xp = getRandomXP(Config.xpDolphin);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof PolarBear)
				{
					xp = getRandomXP(Config.xpPolarBear);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Wolf)
				{
					xp = getRandomXP(Config.xpWolf);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof CaveSpider)
				{
					xp = getRandomXP(Config.xpCaveSpider);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Enderman)
				{
					xp = getRandomXP(Config.xpEnderman);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Spider)
				{
					xp = getRandomXP(Config.xpSpider);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof PigZombie)
				{
					xp = getRandomXP(Config.xpPigZombie);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Blaze)
				{
					xp = getRandomXP(Config.xpBlaze);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Creeper)
				{
					xp = getRandomXP(Config.xpCreeper);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Drowned)
				{
					xp = getRandomXP(Config.xpDrowned);
					UserManager.addKillsMob(xp, userName);	
				}
				else if(en instanceof ElderGuardian)
				{
					xp = getRandomXP(Config.xpElderGuardian);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Endermite)
				{
					xp = getRandomXP(Config.xpEndermite);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Evoker)
				{
					xp = getRandomXP(Config.xpEvoker);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Ghast)
				{
					xp = getRandomXP(Config.xpGhast);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Guardian)
				{
					xp = getRandomXP(Config.xpGuardian);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Husk)
				{
					xp = getRandomXP(Config.xpHusk);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof MagmaCube)
				{
					xp = getRandomXP(Config.xpMagmaCube);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Phantom)
				{
					xp = getRandomXP(Config.xpPhantom);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Shulker)
				{
					xp = getRandomXP(Config.xpShulker);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Silverfish)
				{
					xp = getRandomXP(Config.xpSilverfish);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Skeleton)
				{
					xp = getRandomXP(Config.xpSkeleton);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Slime)
				{
					xp = getRandomXP(Config.xpSlime);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Stray)
				{
					xp = getRandomXP(Config.xpStray);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Vex)
				{
					xp = getRandomXP(Config.xpVex);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Vindicator)
				{
					xp = getRandomXP(Config.xpVindicator);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Witch)
				{
					xp = getRandomXP(Config.xpWitch);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof WitherSkeleton)
				{
					xp = getRandomXP(Config.xpWitherSkeleton);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Zombie)
				{
					xp = getRandomXP(Config.xpZombie);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof ZombieVillager)
				{
					xp = getRandomXP(Config.xpZombieVillager);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof IronGolem)
				{
					xp = getRandomXP(Config.xpIronGolem);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Snowman)
				{
					xp = getRandomXP(Config.xpSnowman);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof EnderDragon)
				{
					xp = getRandomXP(Config.xpEnderDragon);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Wither)
				{
					xp = getRandomXP(Config.xpWither);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Giant)
				{
					xp = getRandomXP(Config.xpGiant);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof Illusioner)
				{
					xp = getRandomXP(Config.xpIllusioner);
					UserManager.addKillsMob(xp, userName);
				}
				else if(en instanceof ZombieHorse)
				{
					xp = getRandomXP(Config.xpZombieHorse);
					UserManager.addKillsMob(xp, userName);
				}
				else
				{
					return;
				}
				
				Title.sendTitle(e.getEntity().getKiller(), 10, 20, 10, " §a+§b" + xp + "xp", Config.tag);
			}
		}
	}
	
	public HashMap<String, String> lastKill = new HashMap<String, String>();
	public String[][] lastKillers = new String[500][2];
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e)
	{
		if(!(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) return;
		
		LocalPlayer lp = WorldGuardPlugin.inst().wrapPlayer((Player)e.getEntity());

	    RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();

	    if(query.testState(lp.getLocation(), lp, Flags.INVINCIBILITY))
	    {
			return;
		}

	    Player entity = (Player)e.getEntity();
		Player damager = (Player)e.getDamager();
			
		UserManager.addAssisterToList(entity.getName(), damager.getName());
		
        Player damaged = (Player) e.getEntity();
        
        if((damaged.getHealth() - e.getFinalDamage()) <= 0)
        {
        	Player death = damaged;
    		
    	    Entity entityKiller = death.getKiller();
    	    
    	    Player killer = null;
    	    
    	    if(entityKiller instanceof Player && !entityKiller.getName().equals(death.getName()))
    	    {
    	    	killer = (Player) entityKiller;
    	    	
    	    	String killerFT;
    	    	String deathFT;

    	    	Title.sendTitle(killer, 10, 50, 20, Config.mPlayerKill.replace("%XpForKill%", String.valueOf(Config.xpForKill)), Config.tag);
    	    	
    	    	boolean last = false;
    	    	
    	    	for(int i = 0; i < lastKillers.length; i++)
    	    	{
    	    		killerFT= lastKillers[i][0];
    	    		deathFT = lastKillers[i][1];
    	    		
    	    		if(killerFT != null && killerFT.equals(killer.getName()))
    	    		{
    	    			if(deathFT.equals(death.getName()))
    	    			{
    	    				//KILLER ZABIŁ JUZ TA OSOBE POPRZEDNIO
    	    				killer.sendMessage(Config.tag + Config.mAntyLastKillInRow.replace("%Time%", String.valueOf(Config.antyLastKillInRowTimeDelay)));
    	    				last = true;
        	    			break;
    	    			}
    	    		}
    	    	}
    	    	
    	    	if(last == false && killer != null && death != null) //nie bylo trupa w ostatnich zabojstwach, wiec dodaje killa / killer, death null dlaczego? 
    	    	{
    	    		addAntyLastKill(killer.getName(), death.getName());
    	    		UserManager.addKills(killer.getName(), 1);
    	    	}
    	    }
    	    
    	    if(killer == null || killer.getName().equals(death.getName())) //spadł albo coś i się zabił sam
    	    {
    	    	UserManager.addDeaths(death.getName(), 1, null);
    	    }
    	    else
    	    {
    	    	UserManager.addDeaths(death.getName(), 1, killer.getName());
    	    	String weaponName = killer.getInventory().getItemInMainHand().getType().name();
    	    	
    	    	if(killer.getInventory().getItemInMainHand().hasItemMeta() && killer.getInventory().getItemInMainHand().getItemMeta().hasDisplayName())
    	    	{
    	    		weaponName = killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
    	    	}

    	    	 // Prepare a BaseComponent array with the itemJson as a text component
    //	        BaseComponent[] hoverEventComponents = new BaseComponent[]
  //  	        {
    	 //PROTOCOL ERROR         new TextComponent(ItemStackToJson.convertItemStackToJsonRegular(killer.getItemInHand())) // The only element of the hover events basecomponents is the item json
   // 	        };

    	        // Create the hover event
    	   //     HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents);

    	        /* And now we create the text component (this is the actual text that the player sees)
    	         * and set it's hover event to the item event */
    	       // TextComponent component = new TextComponent(weaponName);
    	        
    	  //      component.setHoverEvent(event);
    	        //TextComponent compText = new TextComponent(Config.tag + " §6§o§l" + killer.getName() + "§7[§9" + Level.getPlayerLevel(UserManager.getUser(killer.getName()).getXp()) + "§7]" + " §4" + "§4ť¤§c" + (int)killer.getHealth() + " §8zabiĹ‚ §e§o" + death.getName() + "§7[§9" + Level.getPlayerLevel(UserManager.getUser(death.getName()).getXp()) +"§7] §8uĹĽywajÄ…c §b§n");
    	    	
    	       // compText.addExtra(component);
    	        
    	       // Bukkit.spigot().broadcast(compText);
    	    	Bukkit.broadcastMessage(Config.tag + Config.mPlayerKillPlayerUsed.replace("%KillerName%", killer.getName()).replace("%KillerLevel%", String.valueOf(Level.getPlayerLevel(UserManager.getUser(killer.getName()).getXp()))).replace("%KillerHP%", String.valueOf(killer.getHealth())).replace("%DeathName%", death.getName()).replace("%DeathLevel%", String.valueOf(Level.getPlayerLevel(UserManager.getUser(death.getName()).getXp()))).replace("%WeaponName%", weaponName));
    	    }
        }
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
            final Player damaged = (Player) e.getEntity();
            
            if((damaged.getHealth() - e.getFinalDamage()) <= 0 && (e.getCause() == DamageCause.FALL || e.getCause() == DamageCause.FALLING_BLOCK))
            {
            	UserManager.addDeaths(damaged.getName(), 1, null);
            }
		}
	}
	
	public void addAntyLastKill(final String killerName, final String deathName)
	{
		for(int i = 0; i < lastKillers.length; i++)
		{
			if(lastKillers[i][0] == null)
			{
				lastKillers[i][0] = killerName;
				lastKillers[i][1] = deathName;
				break;
			}
		}
		
		Bukkit.getScheduler().runTaskLater(CraftPvP.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				for(int i = 0; i < lastKillers.length; i++)
				{	
					if(lastKillers[i][0].equals(killerName) && lastKillers[i][1].equals(deathName)) // ERROR
					{
						lastKillers[i][0] = null;
						lastKillers[i][1] = null;
						break;
					}
				}
			}
		},20 * Config.antyLastKillInRowTimeDelay);
	}

	@EventHandler
	public void chat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		
		if(UserManager.getUser(e.getPlayer().getName()) == null)
		{
			if(e.getFormat().contains("{xp}"))
			{
				e.setFormat(e.getFormat().replace("{xp}", "0"));
			}
			if(e.getFormat().contains("{level}"))
			{
				e.setFormat(e.getFormat().replace("{level}", "0"));
			}
		}
		else
		{
			if(e.getFormat().contains("{xp}"))
			{
				e.setFormat(e.getFormat().replace("{xp}", String.valueOf(UserManager.getUser(p.getName()).getXp())));
			}
			if(e.getFormat().contains("{level}"))
			{
				e.setFormat(e.getFormat().replace("{level}", String.valueOf(Level.getPlayerLevel(UserManager.getUser(p.getName()).getXp()))));
			}
		}
	}
}