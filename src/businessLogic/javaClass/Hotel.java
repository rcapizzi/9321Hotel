package businessLogic.javaClass;

public class Hotel {
	private int hotelID;
	private String hotelName;
	private String city;
	private String hotelAddress;
	
	public Hotel(){
		this.hotelID = 0;
		this.hotelName = "";
		this.hotelAddress = "";
		this.city = "";
	}
	
	public void setHotelId(int HotelId) {
		this.hotelID = HotelId;
	}
	public int getHotelId() {
		return hotelID;
	}
	
	public void setHotelName(String HotelName) {
		this.hotelName = HotelName;
	}
	public String getHotelName() {
		return hotelName;
	}
	
	public void setHotelAddress(String HotelAddress) {
		this.hotelAddress = HotelAddress;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	
	public void setCity(String City) {
		this.city = City;
	}
	public String getCity() {
		return city;
	}

}
