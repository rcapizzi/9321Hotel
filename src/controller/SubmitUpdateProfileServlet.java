package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import businessLogic.javaClass.User;
import businessLogic.jdbc.UserDAO;
import businessLogic.library.EmailApi;

/**
 * Servlet implementation class SubmitUpdateProfile
 */
@WebServlet(name = "SubmitUpdateProfileServlet", urlPatterns = "/submitupdateprofile")
public class SubmitUpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitUpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("Submit Update Servlet");
		int userId = Integer.parseInt((String)request.getParameter("user_id"));
		String username = (String)request.getParameter("username");
		User user = new User(userId);
		user.setEmail((String)request.getParameter("email"));
		user.setNickname((String)request.getParameter("nickname"));
		user.setFirstname((String)request.getParameter("firstname"));
		user.setLastname((String)request.getParameter("lastname"));
		user.setAddress((String)request.getParameter("address"));
		user.setCreditCardType((String)request.getParameter("credit_card_type"));
		user.setCreditCardNum((String)request.getParameter("credit_card_number"));
		user.setCreditCardExpYear((String)request.getParameter("credit_card_exp_year"));
		user.setCreditCardExpMonth((String)request.getParameter("credit_card_exp_month"));
		user.setCreditCardCvv((String)request.getParameter("credit_card_cvv"));
		UserDAO u = new UserDAO();
		int result = u.updateUser(user);
		if (result == 1) {
			System.out.println("update user profile successful");
		} else {
			System.out.println("update successful, but the email: "+ (String)request.getParameter("email")+" must be verified first!");
			EmailApi emailApi = new EmailApi();
			emailApi.verifyEmail(userId, username, (String)request.getParameter("email"));
		}
		
		
		
	}

}
