package com.hotel.Hotel_Reservation_final.controller;


import com.hotel.Hotel_Reservation_final.model.dao.RoomReservationDaoH;
import com.hotel.Hotel_Reservation_final.model.entity.RoomReservation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
        Cookie[] ck = request.getCookies();
        out.println("<html><body>");
        if(ck.length==0){
            out.println("You have to login first !");
            out.println("<a href=\"index.jsp\">LOG IN HERE</a>");
        }
        else {
            Cookie fnameC = ck[0];
            Cookie lnameC = ck[1];
            Cookie idC = ck[2];
            out.println("Welcome " + fnameC.getValue() + " " + lnameC.getValue() + " !");
            response.addCookie(fnameC);
            response.addCookie(lnameC);
            response.addCookie(idC);
            String code = request.getParameter("code");
            //RoomReservation reservation = RoomReservationDao.showAllInfo(code);
            RoomReservation reservation = RoomReservationDaoH.getReservationByCode(code);
            if(reservation != null){
                if(RoomReservationDaoH.cancel(code)){ // Previously used RoomReservationDao cancel method
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
