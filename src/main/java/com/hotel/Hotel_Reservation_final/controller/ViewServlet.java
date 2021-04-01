package com.hotel.Hotel_Reservation_final.controller;

import com.hotel.Hotel_Reservation_final.dao.RoomReservationDao;
import com.hotel.Hotel_Reservation_final.dao.RoomReservationDaoH;
import com.hotel.Hotel_Reservation_final.model.RoomReservation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.SyncFailedException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "ViewServlet")
public class ViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        out.println("<html><body>");
        //LinkedList<RoomReservation> reservations = RoomReservationDao.viewAllRecords(Integer.parseInt(id));
        List reservations = RoomReservationDaoH.viewAllRecords(Integer.parseInt(id));
        if(reservations != null){
            for(Iterator iterator = reservations.iterator(); iterator.hasNext();){
                RoomReservation r = (RoomReservation) iterator.next();
                out.println("Room Reservation code : "+r.getReservation_code()+"  , Start date : "+r.getStart_date()+"  , End date : "+r.getEnd_date()+"  , Capacity : "+r.getCapacity()+"  , Room : "+r.getRoom()+"<br><br>");
            }
        }
        else{
            out.println("No reservations found !");
        }
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
