package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.javaClass.Hotel;
import businessLogic.jdbc.HotelDAO;
import businessLogic.jdbc.UserDAO;

/**
 * Servlet implementation class StaffServlet For staff login
 */
@WebServlet(name = "StaffServlet", urlPatterns = "/staff")
public class StaffServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (request.getParameter("staff_logout") != null) {
         // if it's a logout request, invalidate the session
         request.getSession().invalidate();
         response.sendRedirect(request.getContextPath() + "/welcome");

      } else if (request.getSession().getAttribute("logged_in_owner") != null) {
         // if they're logged in as owner, redirect to owner
         response.sendRedirect(request.getContextPath() + "/availability");

      } else if (request.getSession().getAttribute("logged_in_manager") != null) {
         // if they're logged in as manager, redirect to manager
         response.sendRedirect(request.getContextPath() + "/manage");

      } else {
         // otherwise, first visit, so just show the staff login page
         RequestDispatcher rd = request.getRequestDispatcher("/staff.jsp");
         rd.forward(request, response);
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      if (request.getParameter("staff_login") == null) {
         // not actually requesting with the login form
         doGet(request, response);
      } else {

         String username = request.getParameter("staff_username");
         String password = request.getParameter("staff_pswd");
         UserDAO userDao = new UserDAO();
         if (userDao.findOwner(username, password)) {
            request.getSession().setAttribute("logged_in_owner", true);
            response.sendRedirect(request.getContextPath() + "/availability");

         } else {
            int checkHotelID = userDao.findManager(username, password);
            if (checkHotelID < 0) {
               // don't want to give as much detail as to customers; either they're legit management or they're not.
               request
                     .setAttribute("staff_error",
                           "Invalid username or password. Please ask your supervisor, or return to the home page to log in as a customer.");
               RequestDispatcher rd = request.getRequestDispatcher("/staff.jsp");
               rd.forward(request, response);

            } else {
               request.getSession().setAttribute("logged_in_manager", true);
               HotelDAO hotelDao = new HotelDAO();
               try {
                  Hotel realHotel = hotelDao.findHotelById(checkHotelID);
                  request.getSession().setAttribute("manager_hotel", realHotel);
               } catch (SQLException e) {
                  Hotel failHotel = new Hotel();
                  failHotel.setHotelName("Error");
                  request.getSession().setAttribute("manager_hotel", failHotel);
                  e.printStackTrace();
               }
               response.sendRedirect(request.getContextPath() + "/manage");
            }

         }
      }
   }
}
