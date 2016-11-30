package businessLogic.library;
 
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * Gmail account: comp9321.group.rampage@gmail.com
 * Password: Rampage9321
 * 
 */
 
public class EmailApi {
 
	public Properties mailServerProperties;
	public Session getMailSession;
	public MimeMessage generateMailMessage;
 
	public void verifyEmail(int userID, String username, String email)  {
		try{
			System.out.println("\n 1st ===> setup Mail Server Properties..");
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			System.out.println("Mail Server Properties have been setup successfully..");
	 
			// Step2
			System.out.println("\n\n 2nd ===> get Mail Session..");
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//			generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
			generateMailMessage.setSubject("Email Verification: "+ username);
			String urlLink = "http://localhost:8080/Assignment2/emailverify?user_id="
					+Integer.toString(userID)+"&email="+email+"&username="+username;
			String emailBody = "";
			emailBody = "Dear "+username+": <br><br>"
					  + "This is a confirmation email<br><br>"
					  + "To verify this email address, please click here: <br><br>"
					  + "<a href="+urlLink+">Vericy Email</a><br><br>"
					  + "Best Regards";
			
			
			generateMailMessage.setContent(emailBody, "text/html");
			System.out.println("Mail Session has been created successfully..");
	 
			// Step3
			System.out.println("\n\n 3rd ===> Get Session and Send mail");
			Transport transport = getMailSession.getTransport("smtp");
	 
			// Enter your correct gmail UserID and Password
			// if you have 2FA enabled then provide App Specific Password
			transport.connect("smtp.gmail.com", "comp9321.group.rampage", "Rampage9321");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
			System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
		} catch (MessagingException e) {
	        System.out.println("send failed, exception: " + e);
	    }
		// Step1		
	}
	
	public void sendBookingConfirmation(int bookingId, String username, String email, String pin){
		try {
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
			
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			generateMailMessage.setSubject("Booking Confirmation: "+ username);
			
			String emailBody = "";
			emailBody = "Dear "+username+": <br><br>"
					  + "This is a booking confirmation email<br><br>"
					  + "Your booking ID is: "+bookingId+", "
					  + "and your pin code is: "+pin+" <br><br>"
					  + "You can user your bookingID and pin to check your bookings <br><br>"
					  + "via http://localhost:8080/Assignment2/reviewBooking.jsp?"+bookingId
					  + "Best Regards";
			
			generateMailMessage.setContent(emailBody, "text/html");
			Transport transport = getMailSession.getTransport("smtp");		 
			// Enter your correct gmail UserID and Password
			// if you have 2FA enabled then provide App Specific Password
			transport.connect("smtp.gmail.com", "comp9321.group.rampage", "Rampage9321");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
	        System.out.println("send failed, exception: " + e);
	    }
	}
}