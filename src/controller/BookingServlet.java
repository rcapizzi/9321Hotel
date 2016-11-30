package controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import businessLogic.jdbc.*;
import businessLogic.javaClass.*;
@WebServlet(name = "BookingServlet", urlPatterns = "/booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	String check_in = (String)request.getSession().getAttribute("check_in");
    	String check_out = (String)request.getSession().getAttribute("check_out");
    	String city = (String)request.getSession().getAttribute("city");
    	
    	String hotel_id[] = request.getParameterValues("hotelid");
    	String roomtype[] = request.getParameterValues("roomtype");
    	String no[] = request.getParameterValues("number_of_room");
    	String price[] = request.getParameterValues("price");
    	String extrabed[] = request.getParameterValues("extrabed");

		ArrayList<ShoppingCart> thisCart = new ArrayList<ShoppingCart>();
		if(request.getSession().getAttribute("ShoppingCart") != null){
			thisCart = (ArrayList)request.getSession().getAttribute("ShoppingCart");
		}
		for (int i =0;i<no.length;i++){
			if (Integer.parseInt(no[i])>0){
				ShoppingCart newItem = new ShoppingCart();
				newItem.setcheck_in(check_in);
				newItem.setcheck_out(check_out);
				newItem.sethotel_id(Integer.parseInt(hotel_id[i]));
				newItem.setno(Integer.parseInt(no[i]));
				Float total = Float.parseFloat(price[i])*Integer.parseInt(no[i]);
				newItem.setprice(total);
				newItem.setroomType(roomtype[i]);
				newItem.setextrabed(Integer.parseInt(extrabed[i]));
				thisCart.add(newItem);
			}
		
		}
		request.getSession().setAttribute("ShoppingCart", thisCart);
		request.getRequestDispatcher("shoppingcart.jsp").forward(request,response);		
    }
    
}
