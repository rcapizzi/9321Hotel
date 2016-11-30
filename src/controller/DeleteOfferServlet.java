package controller;

import java.io.IOException;

import businessLogic.javaClass.*;
import businessLogic.jdbc.*;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteoffers")
	public class DeleteOfferServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    public DeleteOfferServlet() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	      if (request.getSession().getAttribute("logged_in_owner") == null) {
            response.sendRedirect(request.getContextPath() + "/staff");
         } else {
            
			String hotelName = request.getParameter("hidden");
			String roomType = request.getParameter("roomType");
			RoomDAO roomdao = new RoomDAO();
			RequestDispatcher rd = request.getRequestDispatcher("availability");
			OwnerDAO ownerdao = new OwnerDAO();
			try{
			String hotel_id = roomdao.getHotel_Id(hotelName);
			ownerdao.removeOffer(hotel_id, roomType);
			
			}catch (Exception e){
				e.printStackTrace();
			}
			rd.forward(request, response);
			
         }
}
}
