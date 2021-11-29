package pl.mines.xcraftrayx.CraftPvP.AntyLogOut;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_13_R2.ChatMessageType;
import net.minecraft.server.v1_13_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_13_R2.PacketPlayOutChat;
import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.CraftPvP;

public class AntyLogOut
{
	public static String[][] playerInBattle = new String[150][2];
	public static List<String> blockedCommands = Config.antyLogOutBlockedCMDS;

	public static void runChecker()
	{
		Bukkit.getScheduler().runTaskTimer(CraftPvP.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < playerInBattle.length; i++)
				{
					if (playerInBattle[i][0] != null)
					{
						int timeToEnd = Integer.valueOf(playerInBattle[i][1]);

						Player pl = Bukkit.getPlayer(playerInBattle[i][0]);
						
						if (timeToEnd == 1)
						{
							playerInBattle[i][0] = null;
							playerInBattle[i][1] = null;
							
							if(pl != null)
							{
								pl.sendMessage(Config.tag + Config.mOutFight);
							}
						}
						else
						{
							if(pl != null)
							{
								if(pl.getGameMode() == GameMode.CREATIVE || pl.getGameMode() == GameMode.SPECTATOR)
								{
									playerInBattle[i][0] = null;
									playerInBattle[i][1] = null;
									
									pl.sendMessage(Config.tag + Config.mWarnInFight);
								}
								else
								{
									timeToEnd -= 1;
									
									playerInBattle[i][1] = String.valueOf(timeToEnd);
									
									PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + Config.mFightMode.replace("%TimeToEnd%", String.valueOf(timeToEnd)) + "\"}"), ChatMessageType.GAME_INFO);
									((CraftPlayer) pl).getHandle().playerConnection.sendPacket(packet);
								}
							}
						}
					}
				}
			}
		}, 0, 20);
	}
}