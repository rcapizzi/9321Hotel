package businessLogic.jdbc;

import java.util.ArrayList;
import java.sql.*;

import businessLogic.javaClass.*;


public class RoomDAO {
	
	public ArrayList<Room> randomRoom(int n) throws SQLException
	{
		ArrayList<Room> rooms = new ArrayList<Room>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "select * from room ORDER BY RAND() limit "+n;
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room this_room = new Room();
			this_room.setRoomId(rs.getInt(1));
			this_room.setHotelId(rs.getInt(2));
			this_room.setRoomType(rs.getString(3));
			this_room.setRoomNo(rs.getString(4));
			this_room.setPrice(rs.getInt(5));
			this_room.setRoomDescription(rs.getString(6));
			rooms.add(this_room);	
		}

		o.closeDB(connection);
		return rooms;
	}
	
	//find all search result
	public ArrayList<Search> findAll(String StartDate, String EndDate,String City,int price) throws SQLException{
		ArrayList<Search> Rooms1 = findavailablerooms(StartDate, EndDate,City,price);
		ArrayList<Search> Rooms2 = findallbooking(StartDate, EndDate,City,price);
		
		for (int i =0;i<Rooms1.size();i++){
			for(int j =0;j<Rooms2.size();j++){
				if(Rooms1.get(i).getHotel_id()==Rooms2.get(j).getHotel_id() && Rooms1.get(i).getRoomtype().equals(Rooms2.get(j).getRoomtype())){
					Rooms1.get(i).setNo(Rooms1.get(i).getNo()-Rooms2.get(j).getNo());
				}
			}
		}
		

		return Rooms1;
		
	}
	
	public ArrayList<Search> findavailablerooms(String StartDate, String EndDate,String City,int price) throws SQLException{
		ArrayList<Search> allRooms = new ArrayList<Search>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT hotel_id, room_type,count(*)AS num_of_room,room.normal_price FROM room natural join hotel WHERE room_id not in "+
			"(SELECT room.room_id FROM room natural join room_status "
			+ "where room_status.status = 'repair' AND "
			+ "(room_status.end_date <= "+EndDate+" AND room_status.end_date > "+EndDate+") OR "
			+ "(room_status.start_date < "+EndDate+" AND room_status.start_date >= "+StartDate+")) "
			+ "AND hotel.city = '"+City+"'"+" AND room.normal_price < "+price+
			" group by hotel_id,room_type,room.normal_price;";
		
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Search this_room = new Search();
			this_room.setNo(rs.getInt(3));
			this_room.setHotel_id(rs.getInt(1));
			this_room.setroomtype(rs.getString(2));
			this_room.setPrice(rs.getFloat(4));
			query = "SELECT * FROM hotel.offer where room_type = '"+rs.getString(2)+"' AND hotel_id = "+rs.getInt(1)+" "
					+"AND ((start >="+StartDate+" AND start < "+EndDate+") "
					+ "OR (end <= "+EndDate+" AND end > "+StartDate+"))";
			System.out.println(query);
			ResultSet rs1 = o.searchDB(connection, query);
			if (rs1.next()) {
				this_room.setCurrentPrice(rs1.getFloat("offer_price"));
			} else {
				this_room.setCurrentPrice(rs.getFloat(4));
			}
			
			allRooms.add(this_room);	
		}
		return allRooms;
	}
	
	public  ArrayList<Search> findallbooking(String StartDate, String EndDate,String City,int price) throws SQLException{
		ArrayList<Search> allRooms = new ArrayList<Search>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "select booking_detail.hotel_id, booking_detail.room_type,sum(booking_detail.num_of_room) FROM booking_detail join hotel on hotel.hotel_id=booking_detail.hotel_id AND hotel.city = '"+City+"'"+
				" group by booking_detail.hotel_id,booking_detail.room_type;";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Search this_room = new Search();
			this_room.setNo(rs.getInt(3));
			this_room.setHotel_id(rs.getInt(1));
			this_room.setroomtype(rs.getString(2));
			//this_room.setPrice(rs.getFloat(4));
			allRooms.add(this_room);	
		}
		return allRooms;
	}
	
	public ArrayList<Room> findAllRoom(String check_in,String check_out,int hotel_id,int price) throws SQLException
	{
		ArrayList<Room> rooms = new ArrayList<Room>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * FROM room natural join hotel WHERE room_id not in "+
				"(SELECT room.room_id FROM room natural join room_status "+
"where (room_status.end_date <= "+ check_out+" AND room_status.end_date > "+check_in+")"+
" OR (room_status.start_date < " + check_out+"AND room_status.start_date >= "+check_in+")) and hotel_id="+hotel_id; //todo sql query
		System.out.println(query);
		if(price>0){
			query=query+"and price <"+price;
		}
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room this_room = new Room();
			this_room.setRoomId(rs.getInt(2));
			System.out.println("RoonmID"+rs.getInt(2));
			this_room.setHotelId(rs.getInt(1));
			this_room.setRoomType(rs.getString(3));
			this_room.setRoomNo(rs.getString(4));
			this_room.setPrice(rs.getFloat(5));
			this_room.setRoomDescription(rs.getString(6));
			rooms.add(this_room);	
		}
		o.closeDB(connection);
		return rooms;
	}
	
	
	
	public Room findRoomById(int n) throws SQLException
	{
		Room room = new Room();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * FROM room WHERE room_id = "+n;
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			room.setRoomId(rs.getInt(1));
			room.setHotelId(rs.getInt(2));
			room.setRoomType(rs.getString(3));
			room.setRoomNo(rs.getString(4));
			room.setPrice(rs.getInt(5));
			room.setRoomDescription(rs.getString(6));
		}
		return room;
	}
	
   public ArrayList<Room> allOccRooms(int hotel_id) throws SQLException {
      ArrayList<Room> occRooms = new ArrayList<Room>();
      MysqlOperation o = new MysqlOperation();
      Connection connection = o.DBConnect();
      String query = "SELECT * FROM room  join room_status on room_status.room_id = room.room_id where room_status.status = 'occupied' and room.hotel_id="
            + "" + hotel_id;
      System.out.println(query);
      ResultSet rs = o.searchDB(connection, query);
      if (rs != null) {
         while (rs.next()) {
            Room room = new Room();
            room.setRoomId(rs.getInt(1));
            room.setHotelId(rs.getInt(2));
            room.setRoomType(rs.getString(3));
            room.setRoomNo(rs.getString(4));
            room.setPrice(rs.getInt(5));
            room.setRoomDescription(rs.getString(6));
            occRooms.add(room);
         }
      }
      return occRooms;
   }
	
	//returns the amount of rooms that are unavailable at a hotel id. 
	public int unavailablerooms(int hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT COUNT(hotel_id) FROM room_status WHERE (status = 'occupied' OR status = 'maintenance') AND hotel_id = " + hotel_id;
		ResultSet rs = o.searchDB(connection, query);
		int i = 0;
		while(rs.next()){
			i = rs.getInt(1);
		}
		return i;
	}
	public ArrayList<String> maintenanceRoomNames(String hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT room_no FROM room WHERE room_id IN (SELECT room_id FROM room_status WHERE room_status.status = 'maintenance' AND room_status.hotel_id = " + hotel_id + ")";
		ResultSet rs = o.searchDB(connection, query);
		ArrayList<String> maintRooms = new ArrayList<String>();
		while(rs.next()){
			String i = rs.getString(1);
			maintRooms.add(i);
		}
		return maintRooms;
	}
	
	public ArrayList<String> occupiedRoomNames(String hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT room_no FROM room WHERE room_id IN (SELECT room_id FROM room_status WHERE room_status.status = 'occupied' AND room_status.hotel_id = " + hotel_id + ")";
		ResultSet rs = o.searchDB(connection, query);
		ArrayList<String> occRooms = new ArrayList<String>();
		while(rs.next()){
			String i = rs.getString(1);
			occRooms.add(i);
		}
		return occRooms;
	}
	
	public String getHotelName(String hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT hotel_name FROM hotel WHERE hotel_id = " + hotel_id +"";
		ResultSet rs = o.searchDB(connection, query);
		String hotelName = "";
		while(rs.next()){
			hotelName = rs.getString(1);
		}
		return hotelName;
	}
	
	public String getHotel_Id(String hotel_name) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT hotel_id FROM hotel WHERE hotel_name = " +"'"+ hotel_name +"'";
		ResultSet rs = o.searchDB(connection, query);
		String hotelName = "";
		while(rs.next()){
			hotelName = rs.getString(1);
		}
		return hotelName;
	}
	
	public String getRoom_Id(String hotel_id, String room_no) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT room_id FROM room WHERE (room.hotel_id = " + hotel_id + " AND room.room_no = " + room_no + ")";
		ResultSet rs = o.searchDB(connection, query);
		String roomID = "";
		while(rs.next()){
			roomID = rs.getString(1);
		}
		return roomID;
	}
	
	public int checkRecord(String room_id) throws SQLException {
		int i = 0;
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT COUNT(room_id) FROM room_status WHERE room_id = " + room_id + " AND status = 'maintenance'";
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			i = Integer.valueOf(rs.getString(1));
		}
		return i;
		
	}
	public int checkOffer(String hotel_id, String room_type) throws SQLException {
		int i = 0;
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT COUNT(*) FROM offer WHERE hotel_id = " + hotel_id + " AND room_type = " + "'" + room_type + "'";
		
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			i = Integer.valueOf(rs.getString(1));
		}
		return i;
		
	}
	
	
	
	//find all avaiable rooms in a hotel of a type.
	public ArrayList<Room> allAvailableRooms(int hotel_id,String roomType) throws SQLException
	{
		ArrayList<Room> occRooms= new ArrayList<Room>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * FROM room  where room.room_id NOT in(select room_status.room_id from room_status where room_status.status = 'repair' or room_status.status = 'occupied') and room.hotel_id= "+hotel_id+" and room.room_type='"+roomType+"'";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room room = new Room();
			room.setRoomId(rs.getInt(1));
			room.setHotelId(rs.getInt(2));
			room.setRoomType(rs.getString(3));
			room.setRoomNo(rs.getString(4));
			room.setPrice(rs.getInt(5));
			room.setRoomDescription(rs.getString(6));
			occRooms.add(room);
		}
		return occRooms;
	}
	
	
	public void markRoomAccupied(int hotel_id,int room_id,int booking_id) throws SQLException{ 
		// i think you should be able to get booking_id, if you don't booking_id was useful, just detele it
		MysqlOperation o = new MysqlOperation();
		PreparedStatement pst = null;
		Connection connection = o.DBConnect();
		String query = "INSERT INTO room_status (hotel_id, room_id, booking_id,status) VALUES ("+hotel_id+","+room_id+","+booking_id+",'occupied')";
		
		ResultSet rs = o.searchDB(connection, query);
		
	}
	

	public void markRoomFree(int room_id){
		MysqlOperation o = new MysqlOperation();
		PreparedStatement pst = null;
		Connection connection = o.DBConnect();
		String query = "DELETE FROM room_status WHERE status ='occupied' and room_id="+room_id;
		ResultSet rs = o.searchDB(connection, query);
	}
	// to get all offers
	public ArrayList<Offer> getOffers() throws SQLException{
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

		return offers;
	}
	//to get offers for a specific hotel
	public ArrayList<Offer> getOffersForHotel(String hotel_id) throws SQLException{
		ArrayList<Offer> offers = new ArrayList<Offer>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT offer_price, hotel_id, room_type, start, end FROM offer WHERE offer.hotel_id = " + hotel_id;
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

		return offers;
	}
	
	public ArrayList<Period> getPeriods() throws SQLException{
		ArrayList<Period> periods= new ArrayList<Period>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * FROM peakperiod";
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Period period = new Period();
			period.setPeriodID(rs.getInt(1));
			period.setPeriodName(rs.getString(2));
			period.setPriceIncrease(rs.getInt(3));
			period.setStartDate(rs.getString(4));
			period.setEndDate(rs.getString(5));
			periods.add(period);
		}
		return periods;
	}
	
	public String getCity(String hotel_id) throws SQLException {
		String city = "";
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT city FROM hotel WHERE hotel.hotel_id = '" + hotel_id + "'";
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			city = rs.getString(1);
		}
		return city;
		
	}
		
	
}
