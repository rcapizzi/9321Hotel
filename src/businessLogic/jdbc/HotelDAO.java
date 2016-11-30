package businessLogic.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import businessLogic.javaClass.Hotel;

public class HotelDAO {

   public Hotel findHotelById(int n) throws SQLException {
      Hotel hotel = new Hotel();
      MysqlOperation o = new MysqlOperation();
      Connection connection = o.DBConnect();
      String query = "SELECT * FROM hotel WHERE hotel_id = " + n;
      ResultSet rs = o.searchDB(connection, query);
      while (rs.next()) {
         hotel.setHotelId(rs.getInt(1));
         hotel.setHotelName(rs.getString(2));
         hotel.setHotelAddress(rs.getString(3));
         hotel.setCity(rs.getString(4));
      }
      return hotel;
   }

}
