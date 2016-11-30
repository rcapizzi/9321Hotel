package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import businessLogic.javaClass.*;
import businessLogic.jdbc.*;
@WebServlet(name = "ReviewBookingServlet", urlPatterns = "/ReviewBooking")
public class ReviewBookingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ReviewBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String[] bookroom = request.getParameterValues("bookroom");
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String booking_id = (String) request.getSession().getAttribute("bookingid");
		String pin = request.getParameter("pin");
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		request.getSession().setAttribute("review_booking",bookings);
		request.getRequestDispatcher("reviewBookingDetail.jsp").forward(request,response);		
	}

}
