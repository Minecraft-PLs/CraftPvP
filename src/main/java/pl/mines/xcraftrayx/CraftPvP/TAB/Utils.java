package pl.mines.xcraftrayx.CraftPvP.TAB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import pl.kacperduras.protocoltab.ProtocolTabAPI;
import pl.kacperduras.protocoltab.manager.ProtocolTab;
import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.Stats.Level;
import pl.mines.xcraftrayx.CraftPvP.Stats.User;
import pl.mines.xcraftrayx.CraftPvP.Stats.UserManager;

public class Utils
{
	
	static Economy econ = null;
//	public static String[][] topPointsFactions = new String[15][2];
    static String[][] topPlayers = new String[15][3];
    static Map<String, Integer> topKillers = new HashMap<String, Integer>();
    static Map<String, Integer> topPoziomow = new HashMap<String, Integer>();
	
    public String formatDate(long time)
    {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy' 'HH:mm");
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTimeInMillis(time);
    	calendar.setTimeZone(TimeZone.getTimeZone(Config.timeZone));
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        
        return dateFormatted;
    }
    
    public static String formatTime(long time)
    {
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm.ss");
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTimeInMillis(time);
    	calendar.setTimeZone(TimeZone.getTimeZone(Config.timeZone));
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        
        return dateFormatted;
    }
    
    public static String createdTime(long time)
    {
        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTimeInMillis(time);
    	calendar.setTimeZone(TimeZone.getTimeZone(Config.timeZone));
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        
        return dateFormatted;
    }
    
    
  	public static void updatePlayerTab(Player player)
  	{
  		String userName = player.getName();
    	String time = formatTime(System.currentTimeMillis());
    	
		User u = UserManager.getUser(userName);
		
		int level = 0;
		int kills = 0;
		int deaths = 0;
		int assists = 0;
		int xp = 0;
		double kdr = 0;
		
		if(u != null)
		{
			level = Level.getPlayerLevel(u.getXp());
			kills =  u.getKills();
			deaths =  u.getDeaths();
			assists =  u.getAssists();
			kdr =  u.getKDR();
			xp = u.getXp();
		}
		
        for (int i = 0; i < 80; i++)
        {
            ProtocolTabAPI.getTablist(player).setSlot(i, ProtocolTab.BLANK_TEXT);
        }

        ProtocolTabAPI.getTablist(player).setHeader(Config.mTabHeader);
        ProtocolTabAPI.getTablist(player).setFooter(Config.mTabFooter);

        //ProtocolTabAPI.getTablist(player).setSlot(0, "0.");
        ProtocolTabAPI.getTablist(player).setSlot(0, Config.mTabInformation);
        ProtocolTabAPI.getTablist(player).setSlot(1, Config.mTabProfil + player.getName());
        ProtocolTabAPI.getTablist(player).setSlot(2, Config.mTabNowOnline + Bukkit.getOnlinePlayers().size());
        ProtocolTabAPI.getTablist(player).setSlot(3, Config.mTabTime + time);
        ProtocolTabAPI.getTablist(player).setSlot(4, Config.mTabPing + ((EntityPlayer)((CraftPlayer) player).getHandle()).ping);
        //ProtocolTabAPI.getTablist(player).setSlot(5, "");
        ProtocolTabAPI.getTablist(player).setSlot(6, Config.mTabStats);
        ProtocolTabAPI.getTablist(player).setSlot(7, Config.mTabKills + kills);
        ProtocolTabAPI.getTablist(player).setSlot(8, Config.mTabAssists + assists);
        ProtocolTabAPI.getTablist(player).setSlot(9, Config.mTabDeaths + deaths);
        ProtocolTabAPI.getTablist(player).setSlot(10, Config.mTabKilledMobs + player.getStatistic(Statistic.MOB_KILLS));
        ProtocolTabAPI.getTablist(player).setSlot(11, Config.mTabInflictedDamage + player.getStatistic(Statistic.DAMAGE_DEALT));
        ProtocolTabAPI.getTablist(player).setSlot(12, Config.mTabReceivedDamage + player.getStatistic(Statistic.DAMAGE_TAKEN));
        ProtocolTabAPI.getTablist(player).setSlot(13, Config.mTabLevel + level);
        ProtocolTabAPI.getTablist(player).setSlot(14, Config.mTabXP + xp);
        ProtocolTabAPI.getTablist(player).setSlot(15, Config.mTabKDR + kdr);
        
        //ProtocolTabAPI.getTablist(player).setSlot(12, "");
        

        
        ProtocolTabAPI.getTablist(player).setSlot(20, Config.mTabTOPKillers);
        ProtocolTabAPI.getTablist(player).setSlot(21, "&7----------------------------");

        ArrayList<String> topka = new ArrayList<String>();
        
        for(User us : UserManager.usersList)
        {
        	topKillers.put(us.getUserName(),us.getKills());
        }
        
        topKillers = sortByValue(topKillers);  //SORTOWANIE PO WARTOSCI 1,2,3,4...
        
        	
        	
    	int count = 0;
    	//Wyciagamy ostatnie 15 wartosci
    	for (Entry<String, Integer> entry : topKillers.entrySet()) //ZBIERAMY TAK WSZYSTKICH A POTEM DO LISTY I SORTOWAC TRZEBA!
    	{
    		count++;
    	    String nickName = entry.getKey();
    	    if(topKillers.size() - 15 <= count)
    	    {
    	    	topka.add(nickName);
    	    }
    	}
    	
    	Collections.reverse(topka);

    	for(int i = 23, a = 0; i < 38; i++, a++)
    	{
    		if(a < topka.size())
    		{
    			String userNick = Bukkit.getOfflinePlayer(topka.get(a)).getName();
    			Integer levell = topKillers.get(topka.get(a));
    			
        		String textA;
        		if(a < 9)
        		{
        			textA = "0" + (a+1);
        		}
        		else
        		{
        			textA = String.valueOf(a+1);
        		}
        		
        	    ProtocolTabAPI.getTablist(player).setSlot(i,"&7" + textA + ". &e&l" + userNick + " &7- &a[&b" + levell + "&a]");
    		}
        }
        ProtocolTabAPI.getTablist(player).setSlot(39, "&7----------------------------");
        ProtocolTabAPI.getTablist(player).setSlot(40, Config.mTabTOPLevels);
        ProtocolTabAPI.getTablist(player).setSlot(41, "&7----------------------------");
        
        int count2 = 0;
    	//Wyciagamy ostatnie 15 wartosci
        
        for(User us : UserManager.usersList)
        {
        	topPoziomow.put(us.getUserName(),us.getXp());
        }
        
        topPoziomow = sortByValue(topPoziomow);
        
        
    	ArrayList<String> topkaPoziomow = new ArrayList<String>();
    	for (Entry<String, Integer> entry : topPoziomow.entrySet()) //ZBIERAMY TAK WSZYSTKICH A POTEM DO LISTY I SORTOWAC TRZEBA!
    	{
    		count2++;
    	    String nick = entry.getKey();
    	    if(topPoziomow.size() - 15 <= count2)
    	    {
    	    	topkaPoziomow.add(nick);
    	    }
    	}
    	
    	Collections.reverse(topkaPoziomow);
    	
    	for(int i = 43, a = 0; i < 58; i++, a++)
    	{
    		if(a < topkaPoziomow.size())
    		{
    			String nick = Bukkit.getOfflinePlayer(topkaPoziomow.get(a)).getName();
    			int levels = Level.getPlayerLevel(topPoziomow.get(topkaPoziomow.get(a)));
    			
        		String textA;
        		if(a < 9)
        		{
        			textA = "0" + (a+1);
        		}
        		else
        		{
        			textA = String.valueOf(a+1);
        		}
        		
        	    ProtocolTabAPI.getTablist(player).setSlot(i,"&7" + textA + ". &e&l" + nick + " &7- &a[&b" + levels + "&a]");
    		}
    	}
    	
//        ProtocolTabAPI.getTablist(player).setSlot(59, "&7----------------------------");
//        ProtocolTabAPI.getTablist(player).setSlot(60, "    &7[&6&lTop Asyst&7]");
//        ProtocolTabAPI.getTablist(player).setSlot(61, "&7----------------------------");
//        
//        int count3 = 0;
//    	//Wyciagamy ostatnie 15 wartosci
//    	ArrayList<UUID> topkaChallenger = new ArrayList<UUID>();
//    	for (Entry<UUID, Integer> entry : topChallenger.entrySet()) //ZBIERAMY TAK WSZYSTKICH A POTEM DO LISTY I SORTOWAC TRZEBA!
//    	{
//    		count3++;
//    	    UUID uuid = entry.getKey();
//    	    Integer chCount = entry.getValue();
//    	    if(topChallenger.size()-15 <= count3)
//    	    {
//    	    	topkaChallenger.add(uuid);
//    	    }
//    	}
//    	
//    	Collections.reverse(topkaChallenger);
//    	
//    	for(int i = 63, a = 0; i < 78; i++, a++)
//    	{
//    		if(a < topkaChallenger.size())
//    		{
//    			String userName = Bukkit.getOfflinePlayer(topkaChallenger.get(a)).getName();
//    			Integer chCount = topChallenger.get(topkaChallenger.get(a));
//    			
//        		String textA;
//        		if(a < 9)
//        		{
//        			textA = "0" + (a+1);
//        		}
//        		else
//        		{
//        			textA = String.valueOf(a+1);
//        		}
//        		
//        	    ProtocolTabAPI.getTablist(player).setSlot(i,"&7" + textA + ". &e&l" + userName + " &7- &a[&b" + chCount + "&a]");
//    		}
//    	}
        ProtocolTabAPI.getTablist(player).setSlot(59, "&7----------------------------");
    		
        //CHECK ALL PL AND SET TOP 15 IN CORE TASK ASYNC Bukkit.getOfflinePlayers()[0].getPlayer().getStatistic(Statistic.PLAYER_KILLS);
        
//        for(int i = 43, a = 0; i < 58; i++, a++)
//        {
//        	int a2 = a+1;
//        	String textA = String.valueOf(a2);
//        	
//        	if(textA.length() == 1)
//        	{
//        		textA = 0 + textA;
//        	}
//        	String nick = topPlayers[a][0];
//        	int kills = 0;
//        	if(topPlayers[a][1] == null)
//        	{
//        		kills = 0;
//        	}
//        	else
//        	{
//        		kills = Integer.parseInt(topPlayers[a][1]);
//        	}
//        	
//        	int deaths = 0;
//        	
//        	if(topPlayers[a][2] == null)
//        	{
//        		deaths = 0;
//        	}
//        	else
//        	{
//        		deaths = Integer.parseInt(topPlayers[a][2]);
//        	}
//        	Double kdr;
//        	
//        	if(deaths == 0)
//        	{
//        		kdr = (double)kills;
//        	}
//        	else
//        	{
//        		kdr = (double)(kills / deaths);
//        	}
//        	if(kdr < 0)
//        	{
//        		kdr = 0.0;
//        	}
//        	
//        	ProtocolTabAPI.getTablist(player).setSlot(i,"&7" + textA + ". &e&l" + nick + " &a[&4"+ kills + "&a] &7- &cKDR: &a[&b" + kdr + "&a]");
//        }
        ProtocolTabAPI.getTablist(player).update();
  	}

  	
  	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map)
  	{
  	    return map.entrySet()
  	              .stream()
  	              .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
  	              .collect(Collectors.toMap(
  	                Map.Entry::getKey, 
  	                Map.Entry::getValue, 
  	                (e1, e2) -> e1, 
  	                LinkedHashMap::new
  	              ));
  	}
  	
  	public double getMoney(Player p)
  	{
        double m = econ.getBalance(p);
        return m;
    }
}