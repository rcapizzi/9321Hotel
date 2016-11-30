package businessLogic.jdbc;
import businessLogic.javaClass.*;
import businessLogic.jdbc.*;

import java.sql.*;
import java.util.ArrayList;


public class test {
	public static void main(String args[]) throws SQLException  {
		ArrayList<Offer> offers = new ArrayList<Offer>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT offer_price, hotel_id, room_type, start, end FROM offer";
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Offer offer = new Offer();
			offer.setDiscount(rs.getFloat(1));
			offer.setHotel_name(rs.getString(2));
			offer.setRoom_type(rs.getString(3));
			offer.setStartDate(rs.getString(4));
			offer.setEndDate(rs.getString(5));
			offers.add(offer);
		}
		
		for (Offer offs : offers){
			System.out.println(offs.getDiscount());
		}
	}

}
