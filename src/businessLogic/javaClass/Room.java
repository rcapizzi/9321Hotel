package businessLogic.javaClass;
import businessLogic.jdbc.*;
import java.sql.*;

public class Room {
	private int roomID;
	private int hotelID;
	private String roomType;
	private String roomNo;
	private float price;
	private String roomDescription;
	private String city;
	private float currentPrice;
	
	public Room() {
		this.roomID = 0;
		this.hotelID = 0;
		this.roomType = "";
		this.roomNo = "";
		this.price = 0;
		this.currentPrice = 0;
		this.city = "";
		this.roomDescription = "";
	}
	
	public int getRoomId() {
		return roomID;
	}
	public void setRoomId(int RoomID) {
		this.roomID = RoomID;
	}
	
	public int getHotelId() {
		return hotelID;
	}
	public void setHotelId(int HotelID) throws SQLException {
		this.hotelID = HotelID;
		String hotel_id = String.valueOf(this.hotelID);
		RoomDAO roomdao = new RoomDAO();
		String city2 = roomdao.getCity(hotel_id);
		setCity(city2);
	}
	
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String RoomType) {
		this.roomType = RoomType;
	}
	
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String RoomNo) {
		this.roomNo = RoomNo;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float Price) {
		this.price = Price;
	}
	
	public float getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(float CurrentPrice) {
		this.currentPrice = CurrentPrice;
	}

	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String RoomDescription) {
		this.roomDescription = RoomDescription;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String City) {
		this.city = City;
	}
}
