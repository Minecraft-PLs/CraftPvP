package pl.mines.xcraftrayx.CraftPvP.NightPlotPvP;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.CraftPvP;
import pl.mines.xcraftrayx.CraftPvP.Utils.Title;

public class NightPlotPvP
{
	private static BukkitTask bossBarTask;
	static BossBar pvpOnInfo = Bukkit.createBossBar(Config.mPvPOnPlotON, BarColor.RED, BarStyle.SEGMENTED_6, new BarFlag[0]);
	static BarColor[] colors = BarColor.values();
	
	
	public static void runTimeChecker()
	{
		Bukkit.getScheduler().runTaskTimer(CraftPvP.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				long time = Bukkit.getWorld(Config.nightPvPOnPlotWorldName).getTime();
				
				boolean isPvpOn = Bukkit.getWorld(Config.nightPvPOnPlotWorldName).getPVP();
				
				if(time < 13000 && time > 0  && isPvpOn)
				{
					Bukkit.getWorld(Config.nightPvPOnPlotWorldName).setPVP(false);
					
					if(bossBarTask != null)
					{
						Bukkit.getScheduler().cancelTask(bossBarTask.getTaskId());
					}
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						if(p.getWorld().getName().equals(Config.nightPvPOnPlotWorldName))
						{
							Title.sendTitle(p, 15, 80, 25, Config.tag, Config.mPvPOnPlotOFF);
							p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 0.5f, 0.3f);
						}
					}
					pvpOnInfo.removeAll();
				}
				else if(time > 13000 && time < 24000 && !isPvpOn)
				{
					Bukkit.getWorld(Config.nightPvPOnPlotWorldName).setPVP(true);
					
					for(Player p : Bukkit.getOnlinePlayers())
					{
						if(p.getWorld().getName().equals(Config.nightPvPOnPlotWorldName))
						{
							Title.sendTitle(p, 15, 80, 25, Config.tag, Config.mPvPOnPlotON);
							p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 0.8f, 1);
						}
					}
					createBossBar();
				}
			}
		}, 0, 40);
	}
	
	public static void createBossBar()
	{
		bossBarTask = Bukkit.getScheduler().runTaskTimer(CraftPvP.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				if(pvpOnInfo.isVisible())
				{
					pvpOnInfo.setVisible(false);
				}
				else
				{
					pvpOnInfo.setVisible(true);
				}
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					if(p.getWorld().getName().equals(Config.nightPvPOnPlotWorldName))
					{
						if(!pvpOnInfo.getPlayers().contains(p))
						{
							pvpOnInfo.addPlayer(p);
						}
					}
					else
					{
						if(pvpOnInfo.getPlayers().contains(p))
						{
							pvpOnInfo.removePlayer(p);
						}
					}
				}
				pvpOnInfo.setColor(colors[new Random().nextInt(colors.length-1)]);
			}
		}, 0, 5);
	}
}