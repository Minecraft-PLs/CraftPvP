package pl.mines.xcraftrayx.CraftPvP;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.mines.xcraftrayx.CraftPvP.Scoreboards.Scoreboards;
import pl.mines.xcraftrayx.CraftPvP.Stats.Level;
import pl.mines.xcraftrayx.CraftPvP.Stats.User;
import pl.mines.xcraftrayx.CraftPvP.Stats.UserManager;

public class Commands implements CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) //SIGN SET IF ARENA EXIST
	{
		if(!cmd.getName().equalsIgnoreCase("CraftPvP")) return true;
				
		if(!sender.hasPermission("CraftPvP.admin")) return true;
		
		if(args.length == 0)
		{
			badArguments(sender);
		}
		else if(args.length == 1)
		{
			badArguments(sender);
		}
		else if(args.length == 2)
		{
			if(sender instanceof Player)
			{
				Player pl = (Player)sender;
	
				if(args[0].equalsIgnoreCase("get"))
				{
					if(Config.gadgetsIsEnabled)
					{
						if(args[1].equalsIgnoreCase("fireball"))
						{
							ItemStack fireball = new ItemStack(Material.getMaterial(Config.fireBallItem));
							ItemMeta im = fireball.getItemMeta();
							List<String> lore = new ArrayList<String>();
							lore.add(Config.mFireBallLore);
							im.setLore(lore);
							im.setDisplayName(Config.mFireBallName);
							fireball.setItemMeta(im);
							
							pl.playSound(pl.getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);
							pl.getInventory().addItem(fireball);
						}
						else if(args[1].equalsIgnoreCase("thor"))
						{
							ItemStack thor = new ItemStack(Material.getMaterial(Config.thorItem));
							ItemMeta im = thor.getItemMeta();
							List<String> lore = new ArrayList<String>();
							lore.add(Config.mThorLore);
							im.setLore(lore);
							im.setDisplayName(Config.mThorName);
							thor.setItemMeta(im);
							
							pl.playSound(pl.getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);
							pl.getInventory().addItem(thor);
						}
						else
						{
							badArguments(sender);
						}
					}
					else
					{
						pl.sendMessage(Config.mWarnGadgetsIsDisabled);
						pl.playSound(pl.getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
					}
				}
				else
				{
					badArguments(sender);
				}
			}
			else
			{
				sender.sendMessage(Config.tag + Config.mWarnYouMustBePlayer);
				((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
			}
		}
		else if(args.length == 3)
		{
			String userName = args[1];
			int value;
			try
			{
				value = Integer.parseInt(args[2]);
			}
			catch(NumberFormatException e)
			{
				if(sender instanceof Player)
				{
					((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
				}
				sender.sendMessage(Config.tag + Config.mWarnYouMustEnterNumber);
				return true;
			}
			
			if(args[0].equalsIgnoreCase("addxp"))
			{
				User u = UserManager.getUser(userName);
				if(u != null)
				{
					Level.checkLevelUp(userName, Level.getPlayerLevel(u.getXp()), Level.getPlayerLevel(u.getXp() + value));
					u.setXp(u.getXp() + value);
					UserManager.pushUserToMySQL(u);
					sender.sendMessage(Config.tag + Config.mSuccessfullyAddedXP.replace("%PlayerName%", userName));
					((Player)sender).playSound(((Player)sender).getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);
					
					if(Config.scoreboardsIsEnabled)
					{
						Scoreboards.refreshScoreboard(Bukkit.getPlayer(userName));
					}
				}
				else
				{
					sender.sendMessage(Config.tag + Config.mWarnCanNotFindUser);
					((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
				}
			}
			else if(args[0].equalsIgnoreCase("setxp"))
			{
				User u = UserManager.getUser(userName);
				if(u != null)
				{
					Level.checkLevelUp(userName, Level.getPlayerLevel(u.getXp()), Level.getPlayerLevel(value));
					u.setXp(value);
					UserManager.pushUserToMySQL(u);
					sender.sendMessage(Config.tag + Config.mSuccessfullySetXP.replace("%PlayerName%", userName));
					
					if(Config.scoreboardsIsEnabled)
					{
						Scoreboards.refreshScoreboard(Bukkit.getPlayer(userName));
					}
				}
				else
				{
					sender.sendMessage(Config.tag + Config.mWarnCanNotFindUser);
					((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
				}
			}
			else if(args[0].equalsIgnoreCase("setlevel"))
			{
				User u = UserManager.getUser(userName);
				if(u != null)
				{
					u.setXp(Level.getXpFromLevel(value));
					UserManager.pushUserToMySQL(u);
					Level.playerLevelUp(userName, value);
					sender.sendMessage(Config.tag + Config.mSuccessfullySetLevel.replace("%PlayerName%", userName));
					
					if(Config.scoreboardsIsEnabled)
					{
						Scoreboards.refreshScoreboard(Bukkit.getPlayer(userName));
					}
				}
				else
				{
					sender.sendMessage(Config.tag + Config.mWarnCanNotFindUser);
					((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
				}
			}
			else
			{
				badArguments(sender);
			}
		}
		else
		{
			badArguments(sender);
		}
		return true;
	}
	
	public void badArguments(CommandSender sender)
	{
		if(sender instanceof Player)
		{
			((Player)sender).playSound(((Player)sender).getLocation(), Sound.ENTITY_BAT_HURT, 1, 1);
		}
		sender.sendMessage(Config.tag + Config.mWarnBadArgument);
	}
}