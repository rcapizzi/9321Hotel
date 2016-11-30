package businessLogic.javaClass;

public class User {
	private int userId;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String firstname;
	private String lastname;
	private String address;
	private boolean emailVerification;
	private String creditCardType;
	private String creditCardNum;
	private String creditCardExpMonth;
	private String creditCardExpYear;
	private String creditCardCvv;
	
	public User() {
		
	}
	
	
	public User(int UserId) {
		this.userId = UserId;
		this.username = "";
		this.password = "";
		this.email = "";
		this.nickname = "";
		this.firstname = "";
		this.lastname = "";
		this.address = "";
		this.emailVerification = false;
		this.creditCardType = "";
		this.creditCardNum = "";
		this.creditCardExpMonth = "";
		this.creditCardExpYear = "";
		this.creditCardCvv = "";
		
		
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int UserID) {
		this.userId = UserID;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String Username) {
		this.username = Username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String Password) {
		this.password = Password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String Email) {
		this.email = Email;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String Nickname) {
		this.nickname = Nickname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String Firstname) {
		this.firstname = Firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String Lastname) {
		this.lastname = Lastname;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String Address) {
		this.address = Address;
	}
	
	public Boolean getEmailVerification() {
		return emailVerification;
	}
	public void setEmailVerification(int EmailVerification) {
		if (EmailVerification == 0) this.emailVerification = false;
		else this.emailVerification = true;
	}
	
	public String getCreditCardType() {
		return creditCardType;
	}
	public void setCreditCardType(String CreditCardType) {
		this.creditCardType = CreditCardType;
	}
	
	public String getCreditCardNum() {
		return creditCardNum;
	}
	public void setCreditCardNum(String CreditCardNum) {
		this.creditCardNum = CreditCardNum;
	}
	
	public String getCreditCardExpMonth() {
		return creditCardExpMonth;
	}
	public void setCreditCardExpMonth(String CreditCardExpMonth) {
		this.creditCardExpMonth = CreditCardExpMonth;
	}
	
	public String getCreditCardExpYear() {
		return creditCardExpYear;
	}
	public void setCreditCardExpYear(String CreditCardExpYear) {
		this.creditCardExpYear = CreditCardExpYear;
	}
	
	public String getCreditCardCvv() {
		return creditCardCvv;
	}
	public void setCreditCardCvv(String CreditCardCvv) {
		this.creditCardCvv = CreditCardCvv;
	}
}
