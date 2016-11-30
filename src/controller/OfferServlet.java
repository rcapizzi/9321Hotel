package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import businessLogic.javaClass.Offer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.jdbc.*;

@WebServlet("/offers")
public class OfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OfferServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   if (request.getSession().getAttribute("logged_in_owner") == null) {
		         response.sendRedirect(request.getContextPath() + "/staff");
		      } else {

		String hotel_id = ""; 
		hotel_id = request.getParameter("offerHotel");
		RoomDAO roomdao = new RoomDAO();
		if ( hotel_id != null && hotel_id.length() > 0){
			try{
			RequestDispatcher rd = request.getRequestDispatcher("/ownerOffers.jsp");
			String hotelName = roomdao.getHotelName(hotel_id);
			request.setAttribute("hotelName", hotelName);
			ArrayList<Offer> offers = roomdao.getOffersForHotel(hotel_id);
			request.setAttribute("hotelOffers", offers);
			rd.forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
		String startDate = "";
		String endDate =  "";
		String hotelName = request.getParameter("hidden");
		String roomType = request.getParameter("roomType");
		float disc1; 
		
		startDate = request.getParameter("startDate");
		endDate = request.getParameter("endDate");
		OwnerDAO ownerdao = new OwnerDAO();
		
		if ( startDate != null && startDate.length() > 0){
			disc1 = Float.parseFloat(request.getParameter("price"));
			float discount = disc1 / 100;
			try{
				RequestDispatcher rd = request.getRequestDispatcher("availability");
				String oHotelId = roomdao.getHotel_Id(hotelName);
				java.sql.Date dateStart = java.sql.Date.valueOf(startDate);
				java.sql.Date dateEnd = java.sql.Date.valueOf(endDate);

				if ((roomdao.checkOffer(oHotelId, roomType)) == 0) {
					ownerdao.insertOffer(discount, oHotelId, roomType, dateStart, dateEnd);
				}
				if ((roomdao.checkOffer(oHotelId, roomType)) != 0) {
					ownerdao.removeOffer(oHotelId, roomType);	
					ownerdao.insertOffer(discount, oHotelId, roomType, dateStart, dateEnd);
					}

				rd.forward(request, response);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
}


}
}


