package businessLogic.javaClass;

public class Search {
	private String roomtype;
	private int no;
	private int hotel_id;
	private float price;
	private float currentPrice;
	public Search() {
}

	public String getRoomtype() {
		return roomtype;
	}
	public void setroomtype(String roomtype) {
		this.roomtype = roomtype;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getHotel_id(){
		return hotel_id; 
	}
	public void setHotel_id(int hotel_id){
		this.hotel_id=hotel_id;
	}
	public float getPrice(){
		return price;
	}
	public void setPrice(float price){
		this.price = price;
	}
	public float getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(float CurrentPrice) {
		this.currentPrice = CurrentPrice;
	}
}