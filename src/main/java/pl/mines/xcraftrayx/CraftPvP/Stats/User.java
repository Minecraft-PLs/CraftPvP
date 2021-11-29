package pl.mines.xcraftrayx.CraftPvP.Stats;

import java.util.ArrayList;

/**
 * @author craft
 *
 */
public class User
{
	public String userName;
	public int kills = 0;
	public int deaths = 0;
	public int assists = 0;
	public double kdr = 0;
	public ArrayList<String> assistersOfDeath = new ArrayList<String>();
	public int xp = 0;
	public int level = 0;

	public User(String userName, int kills, int deaths, int assists, double kdr, int xp)
	{
		this.userName = userName;
		this.kills = kills;
		this.deaths = deaths;
		this.assists = assists;
		this.kdr = kdr;
		this.xp = xp;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public void addDeaths(int deaths) {
		this.deaths = this.deaths + deaths;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public void addAssists(int assists) {
		this.assists = this.assists + assists;
	}

	public double getKDR() {
		return kdr;
	}

	public void setKDR(double kdr) {
		this.kdr = kdr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public void addKills(int kills) {
		this.kills = this.kills + kills;
	}

	public int getXp()
	{
		return xp;
	}
	
	public void addXp(int xp)
	{
		this.xp = this.xp + xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	@Override
	public String toString() {
		return "[userName=" + getUserName() + ",kills=" + getKills() + ",deaths=" + getDeaths() + ",assists="
				+ getAssists() + ",kdr=" + getKDR() + ",xp= " + getXp() + "]";
	}

}