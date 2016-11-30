package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import businessLogic.jdbc.*;

/**
 * Servlet implementation class EmailVerifyServlet
 */
@WebServlet(name = "EmailVerifyServlet", urlPatterns = "/emailverify")
public class EmailVerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailVerifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("EmailVerify Servlet");
		int userId = Integer.parseInt(request.getParameter("user_id"));
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		UserDAO u = new UserDAO();
		int result = u.verifyEmail(userId);
		if (result == 0) {
			request.setAttribute("result", "Error: no such email address: "+ email);
		} else if (result == 1) {
			request.setAttribute("result", "Error: The email: "+ email+ " has already been verified.");
		} else {
			request.setAttribute("result", "Successful: The email: "+ email+ " is now activated.");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/verify.jsp");
		rd.forward(request, response);
		
//		System.out.println(userId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
