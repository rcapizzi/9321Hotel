package businessLogic.javaClass;

public class ShoppingCart implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hotel_id;
	private int no;
	private String check_in;
	private String check_out;
	private float price;
	private String roomType;
	private int extrabed;
	
	public ShoppingCart() {
		
	}	
	public int getno() {
		return no;
	}
	public void setno(int no) {
		this.no = no;
	}
	public int gethotel_id() {
		return hotel_id;
	}
	public void sethotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getcheck_in() {
		return check_in;
	}
	public void setcheck_in(String check_in) {
		this.check_in = check_in;
	}
	public String getcheck_out() {
		return check_out;
	}
	public void setcheck_out(String check_out) {
		this.check_out = check_out;
	}
	public float getprice() {
		return price;
	}
	public void setprice(float price) {
		this.price = price;
	}
	public void setroomType(String roomType) {
		this.roomType = roomType;
	}
	public String getroomType() {
		return roomType;
	}
	public void setextrabed(int extrabed) {
		this.extrabed = extrabed;
	}
	public int getextrabed() {
		return extrabed;
	}
}
