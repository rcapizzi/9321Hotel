package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

import businessLogic.jdbc.*;
import businessLogic.javaClass.*;


public class AvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AvailabilityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	if (request.getSession().getAttribute("logged_in_owner") == null) {
            response.sendRedirect(request.getContextPath() + "/staff");
         } else {
    	RequestDispatcher rd = request.getRequestDispatcher("/owner.jsp");
		int occupancy[] = {0,0,0,0,0,0,0,0,0};
		RoomDAO roomdao = new RoomDAO();
		ArrayList<Offer> offers = new ArrayList<Offer>();
		ArrayList<Period> periods = new ArrayList<Period>();
		
		try{
		occupancy[1] = roomdao.unavailablerooms(1);
		occupancy[2] = roomdao.unavailablerooms(2);
		occupancy[3] = roomdao.unavailablerooms(3);
		occupancy[4] = roomdao.unavailablerooms(4);
		occupancy[5] = roomdao.unavailablerooms(5);
		occupancy[6] = roomdao.unavailablerooms(6);
		occupancy[7] = roomdao.unavailablerooms(7);
		occupancy[8] = roomdao.unavailablerooms(8);
		
		offers = roomdao.getOffers();
		periods = roomdao.getPeriods();
		
		}catch (Exception e){
			e.printStackTrace();
		}
				
		HashMap<String, Integer> occs = new HashMap<String, Integer>();
		occs.put("Rampage-SYD-1", occupancy[1]);
		occs.put("Rampage-SYD-2", occupancy[2]);
		occs.put("Rampage-MEL-1", occupancy[3]);
		occs.put("Rampage-MEL-2", occupancy[4]);
		occs.put("Rampage-BRI-1", occupancy[5]);
		occs.put("Rampage-ADE-1", occupancy[6]);
		occs.put("Rampage-PER-1", occupancy[7]);
		occs.put("Rampage-HOB-1", occupancy[8]);
		
		
		request.setAttribute("occupancies", occs);
		request.setAttribute("offers", offers);
		request.setAttribute("periods", periods);
		rd.forward(request, response);


       
          
       
       }

    }
}
