package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.seriea.model.PartiteGiocate;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class SerieADAO {

	public List<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons";
		List<Season> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Season(res.getInt("season"), res.getString("description")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Team> listTeams(Map<String, Team> idMap) {
		String sql = "SELECT team FROM teams";
		List<Team> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				if (idMap.get(res.getString("team")) == null) {
					Team t = new Team(res.getString("team"));
					result.add(t);
					idMap.put(t.getTeam(), t);
				} else {
					result.add(idMap.get(res.getString("team")));
				}
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<PartiteGiocate> getPartiteGiocateEpeso(Map<String, Team> idMap) {
		String sql="SELECT m.HomeTeam ht, m.AwayTeam AT, COUNT(*)*2 AS peso\r " + 
				"FROM matches m\r " + 
				"WHERE m.HomeTeam> m.AwayTeam\r " + 
				"GROUP BY m.HomeTeam, m.AwayTeam\r " ;
		
		List<PartiteGiocate> result=new ArrayList<PartiteGiocate>();
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			
			while (res.next()) {
				Team t1= idMap.get(res.getString("ht"));
				Team t2= idMap.get(res.getString("at"));
				double peso= res.getDouble("peso");
				
				PartiteGiocate pg= new PartiteGiocate(t1, t2, peso);
				result.add(pg);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
