package pl.mines.xcraftrayx.CraftPvP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Particle;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.mines.xcraftrayx.CraftPvP.ArrowTrails.ArrowTrails;
import pl.mines.xcraftrayx.CraftPvP.LevelRewards.Items;

public class Config
{
	final public static File DIRECTORY = new File("plugins/CraftPvP/");
	final public static File CONFIG = new File("plugins/CraftPvP/Config.yml");
	final public static File LANGDIRECTORY = new File("plugins/CraftPvP/Langs/");
	final public static File EN = new File("plugins/CraftPvP/Langs/en.yml");
	final public static File PL = new File("plugins/CraftPvP/Langs/pl.yml");

	public static String databaseType;
	public static String host;
	public static String base;
	public static String user;
	public static String pass;
	public static int port;
	
	public static boolean scoreboardsIsEnabled;
	public static boolean arrowTrailsIsEnabled;
	public static boolean antyLogoutIsEnabled;
	public static boolean bloodIsEnabled;
	public static boolean deathEffectIsEnabled;
	public static boolean gadgetsIsEnabled;
	public static boolean nightPvPOnPlotIsEnabled;
	public static boolean tabStatsIsEnabled;
	public static boolean anvilRepairIsEnabled;
	public static boolean blockLostXP;
	public static boolean betterRespawnWithCorposeIsEnabled;
	
	public static String tag;
	public static String lang;
	public static String thorItem;
	public static int thorTimeToReUseInSec;
	public static String fireBallItem;
	public static int fireBallTimeToReUseInSec;
	public static int fireBallTimeToReUseInSecByVIP;
	public static int xpForKill;
	public static int xpForAssists;
	public static int levelOfCoreValue;
	public static double defaultCost;
	public static double specialCost;
	public static String teleportOnJoinWorld;
	public static double teleportOnJoinX;
	public static double teleportOnJoinY;
	public static double teleportOnJoinZ;
	public static double teleportOnJoinPitch;
	public static double teleportOnJoinYaw;
	public static boolean teleportOnJoinIsEnabled;
	public static String respawnWorld;
	public static double respawnX;
	public static double respawnY;
	public static double respawnZ;
	public static double respawnPitch;
	public static double respawnYaw;
	public static List<String> antyLogOutBlockedCMDS;
	public static int antyLogoutTime;
	public static String nightPvPOnPlotWorldName;
	public static int antyLastKillInRowTimeDelay;
	public static String timeZone;
	
	public static boolean xpFromMobsIsEnabled;
	public static String xpBat;
	public static String xpChicken;
	public static String xpCod;
	public static String xpCow;
	public static String xpDonkey;
	public static String xpHorse;
	public static String xpMushroomCow;
	public static String xpMule;
	public static String xpOcelot;
	public static String xpParrot;
	public static String xpPig;
	public static String xpRabbit;
	public static String xpSheep;
	public static String xpSkeletonHorse;
	public static String xpSalmon;
	public static String xpSquid;
	public static String xpTurtle;
	public static String xpTropicalFish;
	public static String xpVillager;
	public static String xpLlama;
	public static String xpPufferfish;
	public static String xpDolphin;
	public static String xpPolarBear;
	public static String xpWolf;
	public static String xpCaveSpider;
	public static String xpEnderman;
	public static String xpSpider;
	public static String xpPigZombie;
	public static String xpBlaze;
	public static String xpCreeper;
	public static String xpDrowned;
	public static String xpElderGuardian;
	public static String xpEndermite;
	public static String xpEvoker;
	public static String xpGhast;
	public static String xpGuardian;
	public static String xpHusk;
	public static String xpMagmaCube;
	public static String xpPhantom;
	public static String xpShulker;
	public static String xpSilverfish;
	public static String xpSkeleton;
	public static String xpSlime;
	public static String xpStray;
	public static String xpVex;
	public static String xpVindicator;
	public static String xpWitch;
	public static String xpWitherSkeleton;
	public static String xpZombie;
	public static String xpZombieVillager;
	public static String xpIronGolem;
	public static String xpSnowman;
	public static String xpEnderDragon;
	public static String xpWither;
	public static String xpGiant;
	public static String xpIllusioner;
	public static String xpZombieHorse;
	
	public static String mThorName;
	public static String mThorLore;
	public static String mFireBallName;
	public static String mFireBallLore;
	public static String mThorUnlocked;
	public static String mFireBallUnlocked;
	public static String mThorNotYetToUse;
	public static String mFireBallNotYetToUse;
	public static String mThorNoPerm;
	public static String mFireBallNoPerm;
	public static String mFireBallWaitToUse;
	public static String mFireBallWaitToUseVIP;
	public static String mThorWaitToUse;

	public static String mWarnYouMustBePlayer;
	public static String mWarnBadArgument;
	public static String mWarnInFight;
	public static String mOutFight;
	public static String mFightMode;
	public static String mJoinToFightMode;
	public static String mWarnCanNotTeleportInFightMode;
	public static String mPlayerLogOutDuringFight;
	public static String mWarnPlayerCanNotRepair;
	public static String mPvPOnPlotON;
	public static String mPvPOnPlotOFF;
	public static String mRespawnOnDeath;
	public static String mRespawnOnDeathSub;
	public static String mRespawnBackToSpawn;
	public static String mRespawnBackToSpawnSub;
	public static String mScoreboardLevel;
	public static String mScoreboardKills;
	public static String mScoreboardDeaths;
	public static String mScoreboardAssists;
	public static String mScoreboardKDR;
	public static String mScoreboardHealth;
	public static String mPlayerKill;
	public static String mPlayerKillSub;
	public static String mAntyLastKillInRow;
	public static String mPlayerKillPlayerUsed;
	public static String mPlayerAssisted;
	public static String mPlayerAssistedSub;
	public static String mWarnYouMustEnterNumber;
	public static String mSuccessfullyAddedXP;
	public static String mSuccessfullySetXP;
	public static String mSuccessfullySetLevel;
	public static String mWarnCanNotFindUser;
	public static String mWarnGadgetsIsDisabled;
	
	public static String mTabHeader;
	public static String mTabFooter;
	public static String mTabInformation;
	public static String mTabProfil;
	public static String mTabNowOnline;
	public static String mTabTime;
	public static String mTabPing;
	public static String mTabStats;
	public static String mTabKills;
	public static String mTabAssists;
	public static String mTabDeaths;
	public static String mTabKilledMobs;
	public static String mTabInflictedDamage;
	public static String mTabReceivedDamage;
	public static String mTabLevel;
	public static String mTabXP;
	public static String mTabKDR;
	public static String mTabTOPKillers;
	public static String mTabTOPLevels;
	
	
	public static void createDefaultFile()
    {		
        if (!DIRECTORY.exists())
        {
            DIRECTORY.mkdirs();
        }
        
        if (!LANGDIRECTORY.exists())
        {
            DIRECTORY.mkdirs();
        }
        
		if(!EN.exists())
		{
			CraftPvP.getInstance().saveResource("Langs/en.yml", false);
		}
		
		if(!PL.exists())
		{
			CraftPvP.getInstance().saveResource("Langs/pl.yml", false);
		}
        
        if (!CONFIG.exists())
        {
            try
            {
                CONFIG.createNewFile();
               
                Writer writer = new BufferedWriter(new FileWriter(CONFIG));
               
                writer.write("Config:");                                                                                ((BufferedWriter) writer).newLine();
                writer.write("  TAG: '&7[&6&lCraft&4&lPvP&7]'");                                                        ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #List of all TimeZones: http://combostruct.com/4APa");                                  ((BufferedWriter) writer).newLine();
                writer.write("  timeZone: 'Europe/Warsaw'");                                           		    	    ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();

                writer.write("  #Availble languages: EN, PL"); 											                ((BufferedWriter) writer).newLine();
                writer.write("  #You can change lang file in plugin directory/langs/"); 							    ((BufferedWriter) writer).newLine();
                writer.write("  lang: EN"); 					                                                        ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  Database:");                                                                            ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                                                      ((BufferedWriter) writer).newLine();
                writer.write("    #Avaible type: MySQL / SQL");                                                         ((BufferedWriter) writer).newLine();
                writer.write("    type: SQL");                                                                          ((BufferedWriter) writer).newLine();
                writer.write("    host: localhost");                                                                    ((BufferedWriter) writer).newLine();
                writer.write("    base: PvP");                                                          		        ((BufferedWriter) writer).newLine();
                writer.write("    user: root");                                                                         ((BufferedWriter) writer).newLine();
                writer.write("    pass: password");                                                                     ((BufferedWriter) writer).newLine();
                writer.write("    port: 3306");                                                                         ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #If enabled, player will be must pay for using anvil");                                 ((BufferedWriter) writer).newLine();
                writer.write("  #Permission for special cost: CraftPvP.AnvilRepair.VIP");                               ((BufferedWriter) writer).newLine();
                writer.write("  #If blockLostXP is enabled, using anvil will be don't lose xp");                        ((BufferedWriter) writer).newLine();
                writer.write("  AnvilRepair:");                                                                         ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                                                      ((BufferedWriter) writer).newLine();
                writer.write("    blockLostXP: true");                                                                  ((BufferedWriter) writer).newLine();
                writer.write("    defaultCost: 5000");                                                                  ((BufferedWriter) writer).newLine();
                writer.write("    specialCost: 1000");                                                                  ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();

                writer.write("  #Set the time in sec after which the system will add to the statistics a murder for killing the same person in a row.");((BufferedWriter) writer).newLine();
                writer.write("  AntyLastKillInRow:");                                                                   ((BufferedWriter) writer).newLine();
                writer.write("    timeDelay: 60");                                                                      ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #When player logout during AntyLogout mode player will die");                           ((BufferedWriter) writer).newLine();
                writer.write("  #blocked-cmds is list of blocked player commands used during AntyLogout mode");         ((BufferedWriter) writer).newLine();
                writer.write("  AntyLogout:");                                                                          ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                                                      ((BufferedWriter) writer).newLine();
                writer.write("    time: 15");                                                                           ((BufferedWriter) writer).newLine();
                writer.write("    blocked-cmds:");                                                                      ((BufferedWriter) writer).newLine();
                writer.write("      - spawn");                                                                          ((BufferedWriter) writer).newLine();
                writer.write("      - tpa");                                                                            ((BufferedWriter) writer).newLine();
                writer.write("      - tpahere");                                                                        ((BufferedWriter) writer).newLine();
                writer.write("      - home");                                                                           ((BufferedWriter) writer).newLine();
                writer.write("      - p");                                                                              ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #When player will be using bow the trail of arrows will be have special effect");       ((BufferedWriter) writer).newLine();
                writer.write("  #Random effect of shooting arrows with permission: CraftPvP.ArrowTrails.VIP");          ((BufferedWriter) writer).newLine();
                writer.write("  #List of Particles: http://combostruct.com/4AQF");                                      ((BufferedWriter) writer).newLine();
                writer.write("  ArrowTrails:");                                     				             	    ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                     				            	    ((BufferedWriter) writer).newLine();
                writer.write("    EffectForLevel:");                                     				                ((BufferedWriter) writer).newLine();
                writer.write("      0-2: WATER_BUBBLE");                                     	                 	    ((BufferedWriter) writer).newLine();
                writer.write("      3-4: SPELL_MOB_AMBIENT");                                 	                 	    ((BufferedWriter) writer).newLine();
                writer.write("      5: SPELL");                                 		    	                 	    ((BufferedWriter) writer).newLine();
                writer.write("      6-10: END_ROD");                                         	                 	    ((BufferedWriter) writer).newLine();
                writer.write("      11-12: SPELL_MOB_AMBIENT");                               	                 	    ((BufferedWriter) writer).newLine();
                writer.write("      13-14: SPELL_INSTANT");                                         	                ((BufferedWriter) writer).newLine();
                writer.write("      15-20: ENCHANTMENT_TABLE");                                         	            ((BufferedWriter) writer).newLine();
                writer.write("      21-22: EXPLOSION_NORMAL");                                         	                ((BufferedWriter) writer).newLine();
                writer.write("      23-25: FLAME");                                         	                 	    ((BufferedWriter) writer).newLine();
                writer.write("      26-30: LAVA");                                           	                 	    ((BufferedWriter) writer).newLine();
                
                writer.write("  #When player/mob will be got damage will be spawning particles which will be like bloods");((BufferedWriter) writer).newLine();
                writer.write("  Blood:");                                                                               ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                                                      ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #Spawn firework when player died. Spawn 3x fireworks for players with permission: CraftPvP.DeathEffect.VIP");((BufferedWriter) writer).newLine();
                writer.write("  DeathEffect:");                                                                         ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                                                      ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #This option needed CorposeReborn plugin: http://kudoflow.com/lre.");                   ((BufferedWriter) writer).newLine();
                writer.write("  #When player wish be died, he will be set spectator mode for 5 sec and in him of death location will be spawning corpse of him.");((BufferedWriter) writer).newLine();
                writer.write("  #Set respawn location");                                                                ((BufferedWriter) writer).newLine();
                writer.write("  BetterRespawnWithCorpose:");                                                            ((BufferedWriter) writer).newLine();
                writer.write("    enabled: false");                                                                     ((BufferedWriter) writer).newLine();
                writer.write("    world: world");                                                                       ((BufferedWriter) writer).newLine();
                writer.write("    x: 0"); 	                                                                            ((BufferedWriter) writer).newLine();
                writer.write("    y: 100");	                                                                            ((BufferedWriter) writer).newLine();
                writer.write("    z: 0");                                                                               ((BufferedWriter) writer).newLine();
                writer.write("    pitch: 0");                                                                           ((BufferedWriter) writer).newLine();
                writer.write("    yaw: 0");                                                                             ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();

                writer.write("  #It is compatibile only with PlotSquared");                                             ((BufferedWriter) writer).newLine();
                writer.write("  #Enable PvP on the plot world between 6:00 - 19:00 of minecraft time");                 ((BufferedWriter) writer).newLine();
                writer.write("  NightPvPOnPlot:");                                                                      ((BufferedWriter) writer).newLine();
                writer.write("    enabled: false");                                                                     ((BufferedWriter) writer).newLine();
                writer.write("    world: plot");                                                                        ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #Enable scoreboards with player stats");    	                                        ((BufferedWriter) writer).newLine();
                writer.write("  Scoreboards:");                                                                         ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                                                      ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #Enable tabStats with stats and TOPs");                                                 ((BufferedWriter) writer).newLine();
                writer.write("  TabStats:");                                                                   	        ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                                                      ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #Teleport player to custom location on join to the server");                            ((BufferedWriter) writer).newLine();
                writer.write("  TeleportOnJoin:");                                                                      ((BufferedWriter) writer).newLine();
                writer.write("    enabled: false");                                                                      ((BufferedWriter) writer).newLine();
                writer.write("    world: world");                                                                       ((BufferedWriter) writer).newLine();
                writer.write("    x: 0"); 	                                                                            ((BufferedWriter) writer).newLine();
                writer.write("    y: 100");	                                                                            ((BufferedWriter) writer).newLine();
                writer.write("    z: 0");                                                                               ((BufferedWriter) writer).newLine();
                writer.write("    pitch: 0");                                                                           ((BufferedWriter) writer).newLine();
                writer.write("    yaw: 0");                                                                             ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();

                writer.write("  #Enable gadgets (it is demo or temporary function)");      	                            ((BufferedWriter) writer).newLine();
                writer.write("  #Permissions: CraftPvP.Gadgets.Thor, CraftPvP.Gadgets.FireBall");                       ((BufferedWriter) writer).newLine();
                writer.write("  #Permission for VIP: CraftPvP.Gadgets.FireBallVIP");      	                            ((BufferedWriter) writer).newLine();
                writer.write("  Gadgets:");                                                     		                ((BufferedWriter) writer).newLine();
                writer.write("    enabled: true");                                                     		            ((BufferedWriter) writer).newLine();
                writer.write("    Thor:");                                     				                        	((BufferedWriter) writer).newLine();
                writer.write("      item: GOLDEN_HOE");                                                                 ((BufferedWriter) writer).newLine();
                writer.write("      timeToReUseInSec: 60"); 			                                                ((BufferedWriter) writer).newLine();
                writer.write("    FireBall:");                                     				           		        ((BufferedWriter) writer).newLine();
                writer.write("      item: FIRE_CHARGE");                                                                ((BufferedWriter) writer).newLine();
                writer.write("      timeToReUseInSec: 300"); 			                                                ((BufferedWriter) writer).newLine();
                writer.write("      timeToReUseInSecByVIP: 60"); 			                             	            ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #Set xp for killing players, all mobs, xp for assists");                                ((BufferedWriter) writer).newLine();
                writer.write("  #The coreOfLevel is needed points to level up using this algorithm:");                  ((BufferedWriter) writer).newLine();
                writer.write("  #(For coreofLevel: 100) 1lvl -> 100xp, 2lvl -> 100 + (xp from previous level (for 1lvl was needed 100xp)) etc.");((BufferedWriter) writer).newLine();
                writer.write("  Levels:");                                                     		                	((BufferedWriter) writer).newLine();
                writer.write("    xpForKill: 50");                                     				                    ((BufferedWriter) writer).newLine();
                writer.write("    xpForAssist: 20");                                     				                ((BufferedWriter) writer).newLine();
                writer.write("    coreOfLevel: 100");                                     				                ((BufferedWriter) writer).newLine();
                writer.write("    XpFromMobs:");                                     					                ((BufferedWriter) writer).newLine();
                writer.write("      enabled: true");                                     				                ((BufferedWriter) writer).newLine();
                writer.write("      bat: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      chicken: 1-2");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      cod: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      cow: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      donkey: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      horse: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      mushroomCow: 1-2");                                             			        ((BufferedWriter) writer).newLine();
                writer.write("      mule: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      ocelot: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      parrot: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      pig: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      rabbit: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      sheep: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      skeletonHorse: 1-2");                                             			        ((BufferedWriter) writer).newLine();
                writer.write("      salmon: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      squid: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      turtle: 1-2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      tropicalFish: 1-2");                                             			        ((BufferedWriter) writer).newLine();
                writer.write("      villager: 1-2");                                             			            ((BufferedWriter) writer).newLine();
                
                writer.write("      llama: 2");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      pufferfish: 2");                                             			            ((BufferedWriter) writer).newLine();
                
                writer.write("      dolphin: 2-3");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      polarBear: 2-3");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      wolf: 2-3");                                             			                ((BufferedWriter) writer).newLine();
                
                writer.write("      caveSpidr: 3-4");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      enderman: 3-4");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      spider: 3-4");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      pigZombie: 3-4");                                             			            ((BufferedWriter) writer).newLine();
                
                writer.write("      blaze: 4-10");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      creeper: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      drowned: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      elderGuardian: 4-10");                                             			        ((BufferedWriter) writer).newLine();
                writer.write("      endermite: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      evoker: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      ghast: 4-10");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      guardian: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      husk: 4-10");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      magmaCube: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      phantom: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      shulker: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      silverfish: 4-10");                                             			        ((BufferedWriter) writer).newLine();
                writer.write("      skeleton: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      slime: 4-10");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      stray: 4-10");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      vex: 4-10");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      vindicator: 4-10");                                             			        ((BufferedWriter) writer).newLine();
                writer.write("      witch: 4-10");                                             			                ((BufferedWriter) writer).newLine();
                writer.write("      witherSkeleton: 4-10");                                             			    ((BufferedWriter) writer).newLine();
                writer.write("      zombie: 4-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      zombieVillager: 4-10");                                             			    ((BufferedWriter) writer).newLine();
                
                writer.write("      ironGolem: 5-10");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      snowman: 5-10");                                             			            ((BufferedWriter) writer).newLine();
                
                writer.write("      enderDragon: 50-200");                                             			        ((BufferedWriter) writer).newLine();
                writer.write("      wither: 50-150");                                             			            ((BufferedWriter) writer).newLine();
                
                writer.write("      giant: 30-50");                                             			            ((BufferedWriter) writer).newLine();
                writer.write("      illusioner: 6-10");                                             			        ((BufferedWriter) writer).newLine();
                writer.write("      zombieHorse: 6-10");                                             			        ((BufferedWriter) writer).newLine();((BufferedWriter) writer).newLine();
                
                writer.write("  #Set rewards for reaching level.");                                                     ((BufferedWriter) writer).newLine();
                writer.write("  #Example:");                                                                            ((BufferedWriter) writer).newLine();
                writer.write("  #1: -> Set level for what player will be getting reward");                              ((BufferedWriter) writer).newLine();
                writer.write("  #  item: IRON_SWORD -> Set the item which will be avarded for the player, item name list: http://combostruct.com/4A72");                                                     ((BufferedWriter) writer).newLine();
                writer.write("  #  durability: 1 -> Set the durability value");                                         ((BufferedWriter) writer).newLine();
                writer.write("  #  count: 1 -> Set the count of item");                                                 ((BufferedWriter) writer).newLine();
                writer.write("  #  name: '&aNice sword' -> Set the name of avarded item");                              ((BufferedWriter) writer).newLine();
                writer.write("  #  lore: -> Set the lores for avarded item");                                           ((BufferedWriter) writer).newLine();
                writer.write("  #    - 'It is the sword for reached first level!'");                                    ((BufferedWriter) writer).newLine();
                writer.write("  #    - '&aIt &6is &lexample &blore&k!!!'");                                             ((BufferedWriter) writer).newLine();
                writer.write("  #  enchantments: -> Set the enchants for avarded item, list of all enchants: http://combostruct.com/4AFi");                                                     ((BufferedWriter) writer).newLine();
                writer.write("  #    - DAMAGE_ALL#8");                                                                  ((BufferedWriter) writer).newLine();
                writer.write("  #    - KNOCKBACK#5");                                                                   ((BufferedWriter) writer).newLine();
                writer.write("  #    - FIRE_ASPECT");                                                                   ((BufferedWriter) writer).newLine();


                writer.write("  LevelRewards:");                                     				             	    ((BufferedWriter) writer).newLine();
                writer.write("    1:"); 		                                    				  		            ((BufferedWriter) writer).newLine();
                writer.write("      item: IRON_SWORD"); 		                                    				    ((BufferedWriter) writer).newLine();
                writer.write("      durability: 1");					                                     			((BufferedWriter) writer).newLine();
                writer.write("      count: 1");					                                     				    ((BufferedWriter) writer).newLine();
                writer.write("      name: '§aPoczątkowy miecz'");                                     				    ((BufferedWriter) writer).newLine();
                writer.write("      lore:");                                     				             	 	    ((BufferedWriter) writer).newLine();
                writer.write("        - 'Ten miecz otrzymujesz po osiągnięciu 1 poziomu!'");                          	((BufferedWriter) writer).newLine();
                writer.write("        - 'Niech ci dzielnie służy'");              							            ((BufferedWriter) writer).newLine();
                writer.write("      enchantments:");                                     				            	((BufferedWriter) writer).newLine();
                writer.write("        - DAMAGE_ALL#1");                                     				            ((BufferedWriter) writer).newLine();
                writer.write("        - KNOCKBACK#2");                                     				            	((BufferedWriter) writer).newLine();
                writer.write("    5:"); 		                                    				   		            ((BufferedWriter) writer).newLine();
                writer.write("      item: IRON_SWORD");		                                    				        ((BufferedWriter) writer).newLine();
                writer.write("      durability: 1");					                                     			((BufferedWriter) writer).newLine();
                writer.write("      count: 1");					                                     				    ((BufferedWriter) writer).newLine();
                writer.write("      name: '§aPoczątkowy miecz'");                                     				    ((BufferedWriter) writer).newLine();
                writer.write("      lore:");                                     				             	 	    ((BufferedWriter) writer).newLine();
                writer.write("        - 'Ten miecz otrzymujesz po osiągnięciu 1 poziomu!'");                          	((BufferedWriter) writer).newLine();
                writer.write("        - 'Niech ci dzielnie służy'");              							            ((BufferedWriter) writer).newLine();
                writer.write("      enchantments:");                                     				            	((BufferedWriter) writer).newLine();
                writer.write("        - DAMAGE_ALL#1");                                     				            ((BufferedWriter) writer).newLine();
                writer.write("        - KNOCKBACK#2");                                     				            	((BufferedWriter) writer).newLine();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        getVariables();
    }
	
	public static void getVariables()
	{
		FileConfiguration conf = YamlConfiguration.loadConfiguration(CONFIG);

		lang = conf.getString("Config.lang");
		databaseType = conf.getString("Config.Database.type");
		host = conf.getString("Config.Database.host");
		base = conf.getString("Config.Database.base");
		user = conf.getString("Config.Database.user");
		pass = conf.getString("Config.Database.pass");
		port = conf.getInt("Config.Database.port");
		
		antyLogoutIsEnabled = conf.getBoolean("Config.AntyLogout.enabled");
		antyLogoutTime = conf.getInt("Config.AntyLogout.time");
		arrowTrailsIsEnabled = conf.getBoolean("Config.ArrowTrails.enabled");
		bloodIsEnabled = conf.getBoolean("Config.Blood.enabled");
		deathEffectIsEnabled = conf.getBoolean("Config.DeathEffect.enabled");
		nightPvPOnPlotIsEnabled = conf.getBoolean("Config.NightPvPOnPlot.enabled");
		scoreboardsIsEnabled = conf.getBoolean("Config.Scoreboards.enabled");
		tabStatsIsEnabled = conf.getBoolean("Config.TabStats.enabled");
		anvilRepairIsEnabled = conf.getBoolean("Config.AnvilRepair.enabled");
		betterRespawnWithCorposeIsEnabled = conf.getBoolean("Config.BetterRespawnWithCorpose.enabled");
		
		tag = conf.getString("Config.TAG").replace('&', '§');
		
		gadgetsIsEnabled = conf.getBoolean("Config.Gadgets.enabled");
		thorItem = conf.getString("Config.Gadgets.Thor.item");
		thorTimeToReUseInSec = conf.getInt("Config.Gadgets.Thor.timeToReUseInSec");
		
		fireBallItem = conf.getString("Config.Gadgets.FireBall.item");
		fireBallTimeToReUseInSec = conf.getInt("Config.Gadgets.FireBall.timeToReUseInSec");
		fireBallTimeToReUseInSecByVIP = conf.getInt("Config.Gadgets.FireBall.timeToReUseInSecByVIP");
		
		xpForKill = conf.getInt("Config.Levels.xpForKill");
		xpForAssists = conf.getInt("Config.Levels.xpForAssist");
		levelOfCoreValue = conf.getInt("Config.Levels.coreOfLevel");
		xpFromMobsIsEnabled = conf.getBoolean("Config.Levels.XpFromMobs.enabled");
		xpBat = conf.getString("Config.Levels.XpFromMobs.bat");
		xpChicken = conf.getString("Config.Levels.XpFromMobs.chicken");
		xpCod = conf.getString("Config.Levels.XpFromMobs.cod");
		xpCow = conf.getString("Config.Levels.XpFromMobs.cow");
		xpDonkey = conf.getString("Config.Levels.XpFromMobs.donkey");
		xpHorse = conf.getString("Config.Levels.XpFromMobs.horse");
		xpMushroomCow = conf.getString("Config.Levels.XpFromMobs.mushroomCow");
		xpMule = conf.getString("Config.Levels.XpFromMobs.mule");
		xpOcelot = conf.getString("Config.Levels.XpFromMobs.ocelot");
		xpParrot = conf.getString("Config.Levels.XpFromMobs.parrot");
		xpPig = conf.getString("Config.Levels.XpFromMobs.pig");
		xpRabbit = conf.getString("Config.Levels.XpFromMobs.rabbit");
		xpSheep = conf.getString("Config.Levels.XpFromMobs.sheep");
		xpSkeletonHorse = conf.getString("Config.Levels.XpFromMobs.skeletonHorse");
		xpSalmon = conf.getString("Config.Levels.XpFromMobs.salmon");
		xpSquid = conf.getString("Config.Levels.XpFromMobs.squid");
		xpTurtle = conf.getString("Config.Levels.XpFromMobs.turtle");
		xpTropicalFish = conf.getString("Config.Levels.XpFromMobs.tropicalFish");
		xpVillager = conf.getString("Config.Levels.XpFromMobs.villager");
		xpLlama = conf.getString("Config.Levels.XpFromMobs.llama");
		xpPufferfish = conf.getString("Config.Levels.XpFromMobs.pufferfish");
		xpDolphin = conf.getString("Config.Levels.XpFromMobs.dolphin");
		xpPolarBear = conf.getString("Config.Levels.XpFromMobs.polarBear");
		xpWolf = conf.getString("Config.Levels.XpFromMobs.wolf");
		xpCaveSpider = conf.getString("Config.Levels.XpFromMobs.caveSpider");
		xpEnderman = conf.getString("Config.Levels.XpFromMobs.enderman");
		xpSpider = conf.getString("Config.Levels.XpFromMobs.spider");
		xpPigZombie = conf.getString("Config.Levels.XpFromMobs.pigZombie");
		xpBlaze = conf.getString("Config.Levels.XpFromMobs.blaze");
		xpCreeper = conf.getString("Config.Levels.XpFromMobs.creeper");
		xpDrowned = conf.getString("Config.Levels.XpFromMobs.drowned");
		xpElderGuardian = conf.getString("Config.Levels.XpFromMobs.elderGuardian");
		xpEndermite = conf.getString("Config.Levels.XpFromMobs.endermite");
		xpEvoker = conf.getString("Config.Levels.XpFromMobs.evoker");
		xpGhast = conf.getString("Config.Levels.XpFromMobs.ghast");
		xpGuardian = conf.getString("Config.Levels.XpFromMobs.guardian");
		xpHusk = conf.getString("Config.Levels.XpFromMobs.husk");
		xpMagmaCube = conf.getString("Config.Levels.XpFromMobs.magmaCube");
		xpPhantom = conf.getString("Config.Levels.XpFromMobs.phantom");
		xpShulker = conf.getString("Config.Levels.XpFromMobs.shulker");
		xpSilverfish = conf.getString("Config.Levels.XpFromMobs.silverfish");
		xpSkeleton = conf.getString("Config.Levels.XpFromMobs.skeleton");
		xpSlime = conf.getString("Config.Levels.XpFromMobs.slime");
		xpStray = conf.getString("Config.Levels.XpFromMobs.stray");
		xpVex = conf.getString("Config.Levels.XpFromMobs.vex");
		xpVindicator = conf.getString("Config.Levels.XpFromMobs.vindicator");
		xpWitch = conf.getString("Config.Levels.XpFromMobs.witch");
		xpWitherSkeleton = conf.getString("Config.Levels.XpFromMobs.witherSkeleton");
		xpZombie = conf.getString("Config.Levels.XpFromMobs.zombie");
		xpZombieVillager = conf.getString("Config.Levels.XpFromMobs.zombieVillager");
		xpIronGolem = conf.getString("Config.Levels.XpFromMobs.ironGolem");
		xpSnowman = conf.getString("Config.Levels.XpFromMobs.snowman");
		xpEnderDragon = conf.getString("Config.Levels.XpFromMobs.enderDragon");
		xpWither = conf.getString("Config.Levels.XpFromMobs.wither");
		xpGiant = conf.getString("Config.Levels.XpFromMobs.giant");
		xpIllusioner = conf.getString("Config.Levels.XpFromMobs.illusioner");
		xpZombieHorse = conf.getString("Config.Levels.XpFromMobs.zombieHorse");
		
		blockLostXP = conf.getBoolean("Config.AnvilRepair.blockLostXP");
		defaultCost = conf.getDouble("Config.AnvilRepair.defaultCost");
		specialCost = conf.getDouble("Config.AnvilRepair.specialCost");
		
		teleportOnJoinIsEnabled = conf.getBoolean("Config.TeleportOnJoin.enabled");
		//TASK TO LOAD WORLD
		teleportOnJoinWorld = conf.getString("Config.TeleportOnJoin.world");
		teleportOnJoinX = conf.getDouble("Config.TeleportOnJoin.x");
		teleportOnJoinY = conf.getDouble("Config.TeleportOnJoin.y");
		teleportOnJoinZ = conf.getDouble("Config.TeleportOnJoin.z");
		teleportOnJoinPitch = conf.getDouble("Config.TeleportOnJoin.pitch");
		teleportOnJoinYaw = conf.getDouble("Config.TeleportOnJoin.yaw");
		
		respawnWorld = conf.getString("Config.BetterRespawnWithCorpose.world");
		respawnX = conf.getDouble("Config.BetterRespawnWithCorpose.x");
		respawnY = conf.getDouble("Config.BetterRespawnWithCorpose.y");
		respawnZ = conf.getDouble("Config.BetterRespawnWithCorpose.z");
		respawnPitch = conf.getDouble("Config.BetterRespawnWithCorpose.pitch");
		respawnYaw = conf.getDouble("Config.BetterRespawnWithCorpose.yaw");

		antyLogOutBlockedCMDS = conf.getStringList("Config.AntyLogOut.blocked-cmds");
		
		nightPvPOnPlotWorldName = conf.getString("Config.NightPvPOnPlot.world");
		antyLastKillInRowTimeDelay = conf.getInt("Config.AntyLastKillInRow.timeDelay");
		
		timeZone = conf.getString("Config.timeZone");
		
		
		ConfigurationSection defined = conf.getConfigurationSection("Config.LevelRewards");
		List<Integer> levels = new ArrayList<Integer>();
		
		if(defined != null)
		{
			for(String keys : defined.getKeys(false))
			{
				levels.add(Integer.valueOf(keys));
			}
		}
		
		for(int i = 0; i< levels.size(); i++)
		{
			String item = conf.getString("Config.LevelRewards." + levels.get(i) + ".item");
			short durability = Short.valueOf(conf.getString("Config.LevelRewards." + levels.get(i) + ".durability"));
			int amount = conf.getInt("Config.LevelRewards." + levels.get(i) + ".count");
			String name = conf.getString("Config.LevelRewards." + levels.get(i) + ".name");
			List<String> lore = conf.getStringList("Config.LevelRewards." + levels.get(i) + ".lore");
			List<String> enchantments = conf.getStringList("Config.LevelRewards." + levels.get(i) + ".enchantments");
			String[][] enchantNames = new String[30][2];
			
			int en = 0;
			for(String ench : enchantments)
			{
				int indexOfHash = ench.indexOf('#');
				
				enchantNames[en][0] = ench.substring(0, indexOfHash);
				enchantNames[en][1] = ench.substring(indexOfHash + 1, ench.length());
				en++;
			}
			new Items(levels.get(i), item, name, amount, durability, lore, enchantNames);
		}
		
		loadMessagesVariables();

	}
	public static void loadMessagesVariables()
	{
		FileConfiguration conf;
		if(lang.equalsIgnoreCase("PL"))
		{
			conf = YamlConfiguration.loadConfiguration(PL);
		}
		else
		{
			conf = YamlConfiguration.loadConfiguration(EN);
		}
		//MESSAGES
		mWarnYouMustBePlayer = conf.getString("Messages.warnYouMustBePlayer").replace('&', '§').replace('|', '\n');
		mWarnYouMustEnterNumber = conf.getString("Messages.warnYouMustEnterNumber").replace('&', '§').replace('|', '\n');
		mWarnBadArgument = conf.getString("Messages.warnBadArgument").replace('&', '§').replace('|', '\n');
		mWarnInFight = conf.getString("Messages.warnInFight").replace('&', '§').replace('|', '\n');
		mOutFight = conf.getString("Messages.warnOutFight").replace('&', '§').replace('|', '\n');
		mFightMode = conf.getString("Messages.fightMode").replace('&', '§').replace('|', '\n');
		mJoinToFightMode = conf.getString("Messages.joinToFightMode").replace('&', '§').replace('|', '\n');
		mWarnCanNotTeleportInFightMode = conf.getString("Messages.warnCanNotTeleportInFightMode").replace('&', '§').replace('|', '\n');
		mPlayerLogOutDuringFight = conf.getString("Messages.playerLogOutDuringFight").replace('&', '§').replace('|', '\n');
		mWarnPlayerCanNotRepair = conf.getString("Messages.warnPlayerCanNotRepair").replace('&', '§').replace('|', '\n');
		mPvPOnPlotOFF = conf.getString("Messages.pvpOnPlotOFF").replace('&', '§').replace('|', '\n');
		mPvPOnPlotON = conf.getString("Messages.pvpOnPlotON").replace('&', '§').replace('|', '\n');
		mRespawnOnDeath = conf.getString("Messages.respawnOnDeath").replace('&', '§');
		mRespawnOnDeathSub = conf.getString("Messages.respawnOnDeathSub").replace('&', '§');
		mRespawnBackToSpawn = conf.getString("Messages.respawnBackToSpawn").replace('&', '§');
		mRespawnBackToSpawnSub = conf.getString("Messages.respawnBackToSpawnSub").replace('&', '§');
		mScoreboardLevel = conf.getString("Messages.scoreboardLevel").replace('&', '§');
		mScoreboardKills = conf.getString("Messages.scoreboardKills").replace('&', '§');
		mScoreboardDeaths = conf.getString("Messages.scoreboardDeaths").replace('&', '§');
		mScoreboardAssists = conf.getString("Messages.scoreboardAssists").replace('&', '§');
		mScoreboardKDR = conf.getString("Messages.scoreboardKDR").replace('&', '§');
		mScoreboardHealth = conf.getString("Messages.scoreboardHealth").replace('&', '§');
		mPlayerKill = conf.getString("Messages.playerKill").replace('&', '§').replace('|', '\n');
		mPlayerKillSub = conf.getString("Messages.playerKillSub").replace('&', '§').replace('|', '\n');
		mAntyLastKillInRow = conf.getString("Messages.antyLastKillInRow").replace('&', '§').replace('|', '\n');
		mPlayerKillPlayerUsed = conf.getString("Messages.playerKillPlayerUsed").replace('&', '§').replace('|', '\n');
		mPlayerAssisted = conf.getString("Messages.playerAssisted").replace('&', '§').replace('|', '\n');
		mPlayerAssistedSub = conf.getString("Messages.playerAssistedSub").replace('&', '§').replace('|', '\n');
		mSuccessfullyAddedXP = conf.getString("Messages.successfullyAddedXP").replace('&', '§').replace('|', '\n');
		mSuccessfullySetXP = conf.getString("Messages.successfullySetXP").replace('&', '§').replace('|', '\n');
		mSuccessfullySetLevel = conf.getString("Messages.successfullySetLevel").replace('&', '§').replace('|', '\n');
		mWarnCanNotFindUser = conf.getString("Messages.warnCanNotFindUser").replace('&', '§').replace('|', '\n');
		
		mThorName = conf.getString("Messages.thorName").replace('&', '§');
		mThorLore = conf.getString("Messages.thorLore").replace('&', '§');
		mThorUnlocked = conf.getString("Messages.thorUnlocked").replace('&', '§').replace('|', '\n');
		mThorNotYetToUse = conf.getString("Messages.thorNotYetToUse").replace('&', '§').replace('|', '\n');
		mThorWaitToUse = conf.getString("Messages.thorWaitToUse").replace('&', '§').replace('|', '\n');
		mThorNoPerm = conf.getString("Messages.thorNoPerm").replace('&', '§');
		
		mFireBallName = conf.getString("Messages.fireBallName").replace('&', '§');
		mFireBallLore = conf.getString("Messages.fireBallLore").replace('&', '§');
		mFireBallUnlocked = conf.getString("Messages.fireBallUnlocked").replace('&', '§').replace('|', '\n');
		mFireBallNotYetToUse = conf.getString("Messages.fireBallNotYetToUse").replace('&', '§').replace('|', '\n');
		mFireBallWaitToUse = conf.getString("Messages.fireBallWaitToUse").replace('&', '§').replace('|', '\n');
		mFireBallWaitToUseVIP = conf.getString("Messages.fireBallWaitToUseVIP").replace('&', '§').replace('|', '\n');
		mFireBallNoPerm = conf.getString("Messages.fireBallNoPerm").replace('&', '§').replace('|', '\n');
		mWarnGadgetsIsDisabled = conf.getString("Messages.warnGadgetsIsDisabled").replace('&', '§').replace('|', '\n');
		
		mTabHeader = conf.getString("Messages.tabHeader");
		mTabFooter = conf.getString("Messages.tabFooter");
		mTabAssists = conf.getString("Messages.tabAssists");
		mTabDeaths = conf.getString("Messages.tabDeaths");
		mTabInflictedDamage = conf.getString("Messages.tabInflictedDamage");
		mTabInformation = conf.getString("Messages.tabInformation");
		mTabKDR = conf.getString("Messages.tabKDR");
		mTabKilledMobs = conf.getString("Messages.tabKilledMobs");
		mTabKills = conf.getString("Messages.tabKills");
		mTabLevel = conf.getString("Messages.tabLevel");
		mTabNowOnline = conf.getString("Messages.tabNowOnline");
		mTabPing = conf.getString("Messages.tabPing");
		mTabProfil = conf.getString("Messages.tabProfil");
		mTabReceivedDamage = conf.getString("Messages.tabReceivedDamage");
		mTabStats = conf.getString("Messages.tabStats");
		mTabTime = conf.getString("Messages.tabTime");
		mTabTOPKillers = conf.getString("Messages.tabTOPKillers");
		mTabTOPLevels = conf.getString("Messages.tabTOPLevels");
		mTabXP = conf.getString("Messages.tabXP");
		
		setArrowTrailsEffectsFromConfig();
	}
	
	public static void setArrowTrailsEffectsFromConfig()
	{
		FileConfiguration conf = YamlConfiguration.loadConfiguration(CONFIG);
		
		ConfigurationSection defined = conf.getConfigurationSection("Config.ArrowTrails.EffectForLevel");
		HashMap<String, Particle> data = new HashMap<String, Particle>();
		
		if(defined != null)
		{
			for(String keys : defined.getKeys(false))
			{
				data.put(keys, Particle.valueOf(conf.getString("Config.ArrowTrails.EffectForLevel." + keys)));
			}
		}
		ArrowTrails.setParticleForLevels(data);
	}
}