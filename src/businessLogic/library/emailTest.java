package businessLogic.library;

public class emailTest {
	public static void main(String [] args) {
		EmailApi e = new EmailApi();
		e.sendBookingConfirmation(2, "liuhaonan", "liuhaonan00@gmail.com", "1234");
	}
}
