package businessLogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.ArrayList;

import businessLogic.javaClass.ShoppingCart;
import businessLogic.javaClass.Room;
import businessLogic.javaClass.Search;
public class ShoppingCartDAO {
	//useless for now
	public ShoppingCart addToCart(String check_in,String check_out,Search room)
	{
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setcheck_in(check_in);
		shoppingCart.setcheck_out(check_out);
		shoppingCart.sethotel_id(room.getHotel_id());
		shoppingCart.setroomType(room.getRoomtype());
		shoppingCart.setprice(room.getPrice()); //todo offer
		shoppingCart.setno(room.getNo());
		return shoppingCart;
		
	}
	
	
	//insert to database
	public void insert(ArrayList<ShoppingCart> rooms,int user_id) throws SQLException
	{
		float total_price = 0;
		MysqlOperation o = new MysqlOperation();
		for (int i = 0; i < rooms.size(); i++) {
			total_price =total_price+ rooms.get(i).getprice(); 
		}
		
		insertBooking(user_id,total_price);
		int id = findLastBookingID();
		for (int i =0; i<rooms.size();i++){
			insertBooking_detail(id,rooms.get(i));
		}
	}
	
	public static void insertBooking(int user_id,float total_price){
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		int randomPIN = (int)(Math.random()*9000)+1000;
		String query = "INSERT INTO booking (user_id,pin,total_price) VALUES ("+user_id+","+randomPIN+","+total_price+")";
		System.out.println(query);
		o.updateDB(connection, query);
		}
	
	public static void insertBooking_detail(int booking_id,ShoppingCart cart){
		
				MysqlOperation o = new MysqlOperation();
				PreparedStatement pst = null;
				Connection connection = o.DBConnect();
				String query = "INSERT INTO booking_detail (booking_id,hotel_id,room_type,num_of_room,extra_bed,checkin,checkout,assign) VALUES ("+booking_id+","+cart.gethotel_id()+",'"+cart.getroomType()+"',"+cart.getno()+","+cart.getextrabed()+","+cart.getcheck_in()+","+cart.getcheck_out()+",'0')";
				System.out.println(query);
				o.updateDB(connection, query);
			}
			
		
	
	public int findLastBookingID() throws SQLException{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT  booking_id FROM booking ORDER BY booking_id DESC LIMIT 1";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		rs.next();
		int i = rs.getInt("booking_id");
		return i;
		
	}
	
	public String findpin(int booking_id) throws SQLException{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT pin FROM booking where booking_id="+booking_id;
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		rs.next();
		String i = rs.getString("pin");
		return i;
		
	}
	
}
