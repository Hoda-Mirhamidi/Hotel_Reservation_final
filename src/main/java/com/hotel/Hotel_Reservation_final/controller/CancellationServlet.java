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

@WebServlet(name = "CancellationServlet")
public class CancellationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String code = request.getParameter("code");
        RoomReservation reservation = RoomReservationDao.showAllInfo(code);
        //RoomReservation reservation = RoomReservationDaoH.showAllInfo(code);
        if(reservation != null){
            if(RoomReservationDao.cancel(code)){ // Previously used RoomReservationDao cancel method
                out.println("Reservation canceled successfully !");
            }
            else{
                out.println("Sorry ! An error has occurred . Please try again ...");
                request.getRequestDispatcher("cancellation.html");
            }
        }
        else{
            out.println("Code not found !");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
