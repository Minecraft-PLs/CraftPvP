package pl.mines.xcraftrayx.CraftPvP;

import org.bstats.MetricsLite;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import pl.mines.xcraftrayx.CraftPvP.AntyLogOut.AntyLogOut;
import pl.mines.xcraftrayx.CraftPvP.AnvilRepair.AnvilRepair;
import pl.mines.xcraftrayx.CraftPvP.ArrowTrails.ArrowTrails;
import pl.mines.xcraftrayx.CraftPvP.Database.Database;
import pl.mines.xcraftrayx.CraftPvP.NightPlotPvP.NightPlotPvP;
import pl.mines.xcraftrayx.CraftPvP.Scoreboards.Scoreboards;
import pl.mines.xcraftrayx.CraftPvP.Stats.UserManager;
import pl.mines.xcraftrayx.CraftPvP.TAB.CraftTabs;

public class CraftPvP extends JavaPlugin
{
	/**
	 * Plugins supported:
	 - Duels
	 - CorposeReborn
	 */
	/**
	 * Permissions:
	   - CraftPvP.AnvilRepair.VIP
	   - CraftPvP.ArrowTrails.VIP
	   - CraftPvP.DeathEffect.VIP
	   - CraftPvP.Gadgets.Thor
	   - CraftPvP.Gadgets.FireBall
	   - CraftPvP.Gadgets.FireBallVIP
	   - CraftPvP.Admin
	 */
	
	/**
	 * Variable in chat: {xp}, {Level}
	 */
	//TODO PLAYER DEATH BY FIRE NO KILLS, COMMANDS CHECK IF gadgets remove when off //DATABASE MIGRATE TO NEW // KILLSTREAK, DOUBLE, TRIPLE...
	private static CraftPvP instance;
	
	public void onEnable()
	{
		instance = this;
		
		Config.createDefaultFile();
		
		Database.connect();
		
		UserManager.loadUsers();
		
		ServerLog.pluginTag = Config.tag;
		
		getCommand("CraftPvP").setExecutor((CommandExecutor) new Commands());
		Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.Stats.Listeners(), this);
		initializePlugins();
		
		new MetricsLite(this);
	}
	
//	public void onDisable()
//	{
//		Database.closeConnection();
//	}
	
	public static CraftPvP getInstance()
	{
		return instance;
	}
	
	public void initializePlugins()
	{	
		if(Config.antyLogoutIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.AntyLogOut.Listeners(), getInstance());
			AntyLogOut.runChecker();
		}
		if(Config.anvilRepairIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.AnvilRepair.Listeners(), getInstance());
			AnvilRepair.registerVault();
		}
		if(Config.arrowTrailsIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.ArrowTrails.Listeners(), getInstance());
			ArrowTrails.run();
		}
		if(Config.bloodIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.Blood.Listeners(), getInstance());
		}
		if(Config.deathEffectIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.DeathEffect.Listeners(), getInstance());
		}
		if(Config.gadgetsIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.Gadgets.Listeners(), this);
		}
		if(Config.betterRespawnWithCorposeIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.Respawn.Listeners(), this);
		}
		if(Config.nightPvPOnPlotIsEnabled)
		{
			NightPlotPvP.runTimeChecker();
		}
		if(Config.scoreboardsIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.Scoreboards.Listeners(), getInstance());
			Scoreboards.runTaskScoreboardVisable();
		}
		if(Config.tabStatsIsEnabled)
		{
			CraftTabs.updater();
		}
		if(Config.teleportOnJoinIsEnabled)
		{
			Bukkit.getPluginManager().registerEvents(new pl.mines.xcraftrayx.CraftPvP.TeleportOnJoin.Listeners(), this);
		}
	}
	
//	public static WorldGuardPlugin getWorldGuard() {
//	    Plugin plugin = CraftPvP.getInstance().getServer().getPluginManager().getPlugin("WorldGuard");
//	 
//	    // WorldGuard may not be loaded
//	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
//	        return null; // Maybe you want throw an exception instead
//	    }
//	 
//	    return (WorldGuardPlugin) plugin;
//	}
	
}
