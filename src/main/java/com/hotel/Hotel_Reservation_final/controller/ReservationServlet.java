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
import java.sql.SQLException;

@WebServlet(name = "ReservationServlet")
public class ReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        int id = Integer.parseInt(request.getParameter("id"));
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        out.println("<html><body>");
        String option = request.getParameter("options");
        if(option.equals("reserve")){
            //RoomReservation reservation = RoomReservationDaoH.addRecord(id,fname,lname,start,end,capacity);
            RoomReservation reservation = RoomReservationDao.addRecord(id,fname,lname,start,end,capacity);
            if(reservation != null){
                out.println("Reservation was successful ! ");
                out.println("Reservation code : "+reservation.getReservation_code()+" , Room number : "+reservation.getRoom());
            }
            else {
                out.println("An error occurred ! Please try again ...");
                request.getRequestDispatcher("reservation.html").include(request,response);
            }
        }
        else{
            //String code = RoomReservationDaoH.keptReservation.getReservation_code();
            String code = RoomReservationDao.reservation.getReservation_code();
            RoomReservation reservation = new RoomReservation(id,fname,lname,start,end,capacity,code);

            if(RoomReservationDao.updateInfo(reservation) != null) {
                out.println("Update was Successful ! ");
                out.println("New Information : ");
                out.println(RoomReservationDao.showAllInfo(code).toString());
            }
            else {
                out.println("An error occurred ! Please try again ...");
                request.getRequestDispatcher("index.jsp").include(request,response);
            }
            RoomReservationDao.reservation = new RoomReservation();
            //RoomReservationDaoH.keptReservation = new RoomReservation();
        }
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
