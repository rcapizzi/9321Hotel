package businessLogic.jdbc;
import java.util.ArrayList;
import java.sql.*;

import businessLogic.javaClass.*;

public class OwnerDAO {
	
	public void insertMaint(String hotel_id, String room_id, java.sql.Date start_date, java.sql.Date end_date) throws SQLException {
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Statement stmt = connection.createStatement();
		String sql = "INSERT INTO room_status (hotel_id, room_id, status, start_date, end_date) VALUES"
				+ "(" + hotel_id + "," + room_id + ",'maintenance','" + start_date + "','" + end_date + "')";
				stmt.executeUpdate(sql);
	}
	public void removeMaint(String room_id) throws SQLException {
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Statement stmt = connection.createStatement();
		String sql = "DELETE FROM room_status WHERE room_status.room_id = " + room_id;
		stmt.executeUpdate(sql);
	}
	
	public void insertOffer(float discount, String hotel_id, String room_type, java.sql.Date start, java.sql.Date end) throws SQLException {
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Statement stmt = connection.createStatement();
		String insert = "INSERT INTO offer (offer_price, hotel_id, room_type, start, end) VALUES ("
				+ discount +"," + hotel_id + ",'" + room_type + "','" + start + "','" + end + "')";
		stmt.executeUpdate(insert);
	}
	
	public void removeOffer(String hotel_id, String room_type) throws SQLException{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Statement stmt = connection.createStatement();
		String delete = "DELETE FROM offer WHERE offer.hotel_id = " + hotel_id + " AND offer.room_type = '" + room_type + "'";
		stmt.executeUpdate(delete);
	}
	
	public void insertPeriod(String period_name, int price_increase, java.sql.Date start, java.sql.Date end) throws SQLException {
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Statement stmt = connection.createStatement();
		String insert = "INSERT INTO peakperiod (period_name, price_increase, start, end) VALUES ('"
				+ period_name + "'," + price_increase + ",'" + start + "','" + end + "')";
		stmt.executeUpdate(insert);
	}
	public void removePeriod(String period_name) throws SQLException {
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Statement stmt = connection.createStatement();
		String delete = "DELETE FROM peakperiod WHERE period_name = '" + period_name + "'";
		stmt.executeUpdate(delete);
	}
	
	

}

