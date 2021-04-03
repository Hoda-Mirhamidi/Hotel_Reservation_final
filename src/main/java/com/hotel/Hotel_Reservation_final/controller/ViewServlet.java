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
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ViewServlet")
public class ViewServlet extends HttpServlet {
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
            String id = idC.getValue();
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
