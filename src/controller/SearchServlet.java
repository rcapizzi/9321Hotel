package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.javaClass.Room;
import businessLogic.javaClass.Search;
import businessLogic.jdbc.RoomDAO;


@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String[] bookroom = request.getParameterValues("bookroom");
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String check_in = "'"+request.getParameter("check_in")+"'";
		String check_out = "'"+request.getParameter("check_out")+"'";
		String city = request.getParameter("city");
		String price1 = request.getParameter("price");
		int price = 99999999;
		if (!price1.equals("")){
			price = Integer.parseInt(price1);
		}
		RoomDAO roomDAO = new RoomDAO();
		ArrayList<Search> rooms;
		try {
			
			rooms = roomDAO.findAll(check_in,check_out,city,price);
			System.out.println("size: "+rooms.size());
			request.getSession().setAttribute("roomResult", rooms);
			request.getSession().setAttribute("check_in", check_in);
			request.getSession().setAttribute("check_out", check_out);
			request.getSession().setAttribute("city", city);
			request.getRequestDispatcher("searchResult.jsp").forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
