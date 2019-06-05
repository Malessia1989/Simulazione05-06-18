package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

import it.polito.tdp.model.Anno;
import it.polito.tdp.model.Distretto;
import it.polito.tdp.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public List<Anno> getAnno() {
		
		String sql="SELECT DISTINCT YEAR(e.reported_date) as anno " + 
				"FROM `events` e " + 
				"ORDER BY  YEAR(e.reported_date) ";
		
		List<Anno> result= new ArrayList<Anno>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					Anno a=new Anno(Year.of(res.getInt("anno")));
					result.add(a);

					
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}

	public List<Distretto> getDistretti(Year anno) {
		String sql="SELECT e.district_id id, AVG(e.geo_lon) mediaLon, AVG(e.geo_lat) mediaLat " + 
				"FROM `events` e  " + 
				"WHERE YEAR(e.reported_date) =? " + 
				"GROUP BY e.district_id ";
		
		List<Distretto> result= new ArrayList<Distretto>();
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, anno.getValue());
			
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					int id=res.getInt("id");
//					double avgLon=res.getDouble("mediaLon");
//					double avgLat= res.getDouble("mediaLat");
					
					Distretto d= new Distretto(id, new LatLng(res.getDouble("mediaLat"), res.getDouble("mediaLon")));
					result.add(d);
					
				} catch (Throwable t) {
					t.printStackTrace();
				
				}
			}
			
			conn.close();
			return result ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	

}













