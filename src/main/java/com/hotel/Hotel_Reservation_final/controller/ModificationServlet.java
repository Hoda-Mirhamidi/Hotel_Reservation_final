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

@WebServlet(name = "ModificationServlet")
public class ModificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        String code = request.getParameter("code");
        try {
            RoomReservation reservation = RoomReservationDao.showAllInfo(code);
            if(reservation != null){
                out.println("Here's your last reservation information : ");
                out.println(reservation.toString());
                request.getRequestDispatcher("reservation.html").include(request,response);
            }
        }catch (NullPointerException e){
            out.println("Code not found !");
        }
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
