package com.hotel.Hotel_Reservation_final.controller;

import com.hotel.Hotel_Reservation_final.dao.RoomReservationDao;
import com.hotel.Hotel_Reservation_final.model.RoomReservation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

@WebServlet(name = "ViewServlet")
public class ViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        out.println("<html><body>");
        LinkedList<RoomReservation> reservations = RoomReservationDao.viewAllRecords(Integer.parseInt(id));
        if(reservations != null){
            reservations.stream().map(RoomReservation::toString).forEach(out::println);
        }
        else{
            out.println("No reservations found !");
        }
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
