package com.hotel.Hotel_Reservation_final.controller;

import com.hotel.Hotel_Reservation_final.model.entity.RoomReservation;
import com.hotel.Hotel_Reservation_final.model.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReservationServlet")
public class ReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Cookie[] ck = request.getCookies();
        out.println("<html><body>");
        if(ck.length==0){
            out.println("You have to login first !");
            out.println("<a href=\"index.jsp\">LOG IN HERE</a>");
        }
        else{
            Cookie fnameC = ck[0];
            Cookie lnameC = ck[1];
            Cookie idC = ck[2];
            out.println("Welcome "+fnameC.getValue()+" "+lnameC.getValue()+" !");
            response.addCookie(fnameC);
            response.addCookie(lnameC);
            response.addCookie(idC);
            String fname = fnameC.getValue();
            String lname = lnameC.getValue();
            int id = Integer.parseInt(idC.getValue());
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            String option = request.getParameter("options");
            if(option.equals("reserve")){
                int lastRoomNum = RoomReservationDaoH.getRoomNum();
                String code = String.valueOf(10001+lastRoomNum);
                RoomReservation reservation = new RoomReservation(id,fname,lname,start,end,capacity,code);
                reservation.setUser(UserDaoH.getUserById(id));
                RoomReservation checkReservation = RoomReservationDaoH.addRecord(reservation);
                //RoomReservation reservation = RoomReservationDao.addRecord(id,fname,lname,start,end,capacity);
                if(checkReservation != null){
                    out.println("Reservation was successful ! ");
                    out.println("Reservation code : "+checkReservation.getReservation_code()+" , Room number : "+checkReservation.getRoom());
                }
                else {
                    out.println("An error occurred ! Please try again ...");
                    request.getRequestDispatcher("reservation.html").include(request,response);
                }
            }
            else{
                //String code = RoomReservationDao.reservation.getReservation_code();
                RoomReservation reservation = RoomReservationDaoH.keptReservation;
                if(RoomReservationDaoH.updateInfo(reservation)) {
                    out.println("Update was Successful ! ");
                    out.println("New Information : ");
                    out.println(RoomReservationDaoH.getReservationByCode(RoomReservationDaoH.keptReservation.getReservation_code()).toString());
                }
                else {
                    out.println("An error occurred ! Please try again ...");
                    request.getRequestDispatcher("index.jsp").include(request,response);
                }
                //RoomReservationDao.reservation = new RoomReservation();
                RoomReservationDaoH.keptReservation = new RoomReservation();
            }
            out.println("</body></html>");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
