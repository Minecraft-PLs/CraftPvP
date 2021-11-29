package pl.mines.xcraftrayx.CraftPvP.Database;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zaxxer.hikari.HikariDataSource;

import pl.mines.xcraftrayx.CraftPvP.Config;
import pl.mines.xcraftrayx.CraftPvP.Stats.User;

public class Database
{
//	private static Connection conn;
//	private static Statement stat;
//	private static String DRIVER;
	
	public static HikariDataSource hikari;
	public static String DB_URL;
	

	private static File SQLite = new File("plugins/CraftPvP/Database.db");;
	
	public static void connect()
	{
//		try
//		{
//			if(Config.databaseType.equalsIgnoreCase("MySQL"))
//			{
//				DRIVER = "com.mysql.jdbc.Driver";
//			}
//			else
//			{
//				DRIVER = "org.sqlite.JDBC";
//				if (!SQLite.exists())
//		        {
//		            try
//		            {
//		                SQLite.createNewFile();
//		            }
//		            catch (IOException e)
//		            {
//		                e.printStackTrace();
//		            }
//		        }
//			}
//			
//			Class.forName(Database.DRIVER);
//		}
//		catch (ClassNotFoundException e)
//		{
//			System.err.println("Problem with MySQL Driver...");
//			e.printStackTrace();
//		}
		
		if(Config.databaseType.equalsIgnoreCase("MySQL"))
		{
			DB_URL = "jdbc:mysql://" + Config.host + ":" + Config.port + "/" + Config.base + "?autoReconnect=true&user=" + Config.user + "&password=" + Config.pass;
		}
		else
		{
			DB_URL = "jdbc:sqlite:" + SQLite.getPath();
		}
		
		hikari = new HikariDataSource();
        hikari.setJdbcUrl(DB_URL);

			
//		if(conn == null)
//		{
//			conn = DriverManager.getConnection(DB_URL);
//			stat = conn.createStatement();
//		}
//		}
//		catch(SQLException e)
//		{
//			System.err.println("Problem with opening connection with Database...");
//			e.printStackTrace();
//		}
			
		createTable();
	}
	
//	public static void closeConnection()
//	{
//		try
//		{
//			if(conn != null)
//			{
//				conn.close();
//			}
//		}
//		catch (SQLException e)
//		{
//		 System.err.println("Problem with closing connection with Database...");
//	        e.printStackTrace();
//	    }
//	}
	
    public static HikariDataSource getHikari()
    {
        return hikari;
    }

	public static boolean createTable()
	{
		String createTable = "CREATE TABLE IF NOT EXISTS CraftPvP (id INTEGER AUTO_INCREMENT, username VARCHAR(100), kills INT(11), deaths INT(11), assists INT(11), kdr DOUBLE(40,2), xp INT(11), UNIQUE (username), PRIMARY KEY(id))";
		
		try(Connection connection = hikari.getConnection();
				Statement statement = connection.createStatement())
		{
		    statement.execute(createTable);
		}
		catch (SQLException e)
		{
		    System.err.println("Problem with creating table");
		    e.printStackTrace();
	        return false;
	    }
	  	return true;
	}
	
	public static boolean insertUser(String username, int kills, int deaths, int assists, double kdr, int xp)
	{
		try(Connection connection = hikari.getConnection();
				PreparedStatement insert = connection.prepareStatement("INSERT INTO CraftPvP VALUES (NULL, '" + username +"', " + kills +", " + deaths + ", " + assists + ", " + kdr + ", " + xp + ");"))
		{
			insert.execute();
	    }
	    catch (SQLException e)
	    {
	    	System.err.println("Problem with adding user to MySQL!");
	        e.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	public static ArrayList<User> getUsers()
	{
		ArrayList<User> users = new ArrayList<User>();
		
		 try (Connection connection = hikari.getConnection();
                 PreparedStatement select = connection.prepareStatement("SELECT * FROM CraftPvP;"))
		 {
			ResultSet result = select.executeQuery();
			
			while(result.next())
			{
				String userName = result.getString("username");
		        int kills = result.getInt("kills");
		        int deaths = result.getInt("deaths");
		        int assists = result.getInt("assists");
		        double kdr = result.getDouble("kdr");
		        int xp = result.getInt("xp");
		        
		        User u = new User(userName, kills, deaths, assists, kdr, xp);
		        users.add(u);
			}
			result.close();
		}
		catch (SQLException e)
		{
			System.err.println("Problem with getting all users!");
			e.printStackTrace();
			return null;
		}
		return users;
	}
	
	public static boolean updateUser(String username, int kills, int deaths, int assists, double kdr, int xp)
	{
		 try (Connection connection = hikari.getConnection();
                 PreparedStatement update = connection.prepareStatement("UPDATE CraftPvP SET kills = " + kills + ", deaths = " + deaths + ", assists = " + assists + ", kdr = " + kdr + ", xp = " + xp + " WHERE username LIKE '" + username + "';"))
        {
            update.execute();
        }
        catch (SQLException e)
        {
            System.err.println("Problem with updating user stats in MySQL");
            e.printStackTrace();
            return false;
        }
        return true;
	}
}