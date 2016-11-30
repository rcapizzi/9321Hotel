package controller;
import java.io.IOException;

import businessLogic.javaClass.*;
import businessLogic.jdbc.*;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/peakperiods")
public class PeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PeriodServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (request.getSession().getAttribute("logged_in_owner") == null) {
          response.sendRedirect(request.getContextPath() + "/staff");
       } else {

    	RequestDispatcher rd = request.getRequestDispatcher("availability");
    	if(request.getParameter("pName") != null){
    	String pName = request.getParameter("pName");
    	String StringIncrease = request.getParameter("pIncrease");
    	String pStart = request.getParameter("pStart");
    	String pEnd = request.getParameter("pEnd");
    	
    	java.sql.Date dateStart = java.sql.Date.valueOf(pStart);
		java.sql.Date dateEnd = java.sql.Date.valueOf(pEnd);
		int pIncrease = Integer.valueOf(StringIncrease);
		OwnerDAO ownerdao = new OwnerDAO();
		
		try{
		
		ownerdao.insertPeriod(pName, pIncrease, dateStart, dateEnd);
		rd.forward(request, response);
		
		}catch (Exception e){
			e.printStackTrace();
		}
    	}
		
		String rName = "";
		if(request.getParameter("removeName") != null){
			OwnerDAO ownerdao2 = new OwnerDAO();
			try{
			rName = request.getParameter("removeName");
			ownerdao2.removePeriod(rName);	
			rd.forward(request, response);
			}catch (Exception e){
				e.printStackTrace();
			}
		};
		 
       } 
}
}