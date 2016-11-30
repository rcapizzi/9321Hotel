package businessLogic.javaClass;
import businessLogic.jdbc.*;
import java.sql.*;

public class Offer {
	private float discount;
	private String hotel_name;
	private String room_type;
	private String startDate;
	private String endDate;
	
	public Offer(){
		this.discount = 0;
		this.hotel_name = "";
		this.room_type = "";
		this.startDate = "";
		this.endDate = "";
	
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_id) throws SQLException{
		RoomDAO roomdao = new RoomDAO();
		String hotelName = roomdao.getHotelName(hotel_id);
		this.hotel_name = hotelName;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
