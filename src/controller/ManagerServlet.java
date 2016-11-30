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

import businessLogic.javaClass.Booking;
import businessLogic.javaClass.Hotel;
import businessLogic.javaClass.Room;
import businessLogic.jdbc.BookingDAO;
import businessLogic.jdbc.RoomDAO;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet(name = "ManagerServlet", urlPatterns = "/manage")
public class ManagerServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      if (request.getSession().getAttribute("logged_in_manager") == null) {
         response.sendRedirect(request.getContextPath() + "/staff");
      } else {
         Hotel managedHotel = (Hotel) request.getSession().getAttribute("manager_hotel");
         ArrayList<Room> occupiedRooms = new ArrayList<Room>();
         ArrayList<Booking> emptyBookings = new ArrayList<Booking>();
         ArrayList<Booking> filledBookings = new ArrayList<Booking>();

         // get a list of currently occupied rooms for this hotel
         RoomDAO roomDao = new RoomDAO();
         try {
            occupiedRooms = roomDao.allOccRooms(managedHotel.getHotelId());
         } catch (SQLException e) {
            e.printStackTrace();
         }

         // get a list of current bookings which are not yet assigned rooms
         BookingDAO bookingDao = new BookingDAO();
         try {
            emptyBookings = bookingDao.currentUnassignedBooking(managedHotel.getHotelId());
         } catch (SQLException e) {
            e.printStackTrace();
         }

         // get a list of current bookings which are already assigned rooms
         try {
            filledBookings = bookingDao.currentAssignedBooking(managedHotel.getHotelId());
         } catch (SQLException e) {
            e.printStackTrace();
         }

         request.setAttribute("manager_occupancy", occupiedRooms);
         request.setAttribute("manager_empty_bookings", emptyBookings);
         request.setAttribute("manager_filled_bookings", filledBookings);

         RequestDispatcher rd = request.getRequestDispatcher("/manager.jsp");
         rd.forward(request, response);
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // post back to manage is a "remove room"

      if (request.getSession().getAttribute("logged_in_manager") == null) {
         response.sendRedirect(request.getContextPath() + "/staff");
      } else {
         String[] roomsToClear = request.getParameterValues("clear_rooms");
         String clearedRooms = "";
         RoomDAO roomDao = new RoomDAO();

         if (roomsToClear != null) {
            for (String roomID : roomsToClear) {
               try {
                  Room room = roomDao.findRoomById(Integer.parseInt(roomID));
                  roomDao.markRoomFree(room.getRoomId());
                  clearedRooms = clearedRooms + room.getRoomNo() + ' ';
               } catch (NumberFormatException e) {
                  e.printStackTrace();
               } catch (SQLException e) {
                  e.printStackTrace();
               }
            }
            request.setAttribute("manager_message", "Room(s) cleared: " + clearedRooms);
         }

         doGet(request, response);
      }
   }
}
