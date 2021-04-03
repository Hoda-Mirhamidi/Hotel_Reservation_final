package com.hotel.Hotel_Reservation_final.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
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
            Cookie fname = ck[0];
            Cookie lname = ck[1];
            Cookie id = ck[2];
            out.println("Welcome "+fname.getValue()+" "+lname.getValue()+" !");
            String option = request.getParameter("options");
            response.addCookie(fname);
            response.addCookie(lname);
            response.addCookie(id);
            if(option.equals("reserve")){
                request.getRequestDispatcher("/reservation.html").include(request,response);
            }
            else if(option.equals("modify")){
                request.getRequestDispatcher("/modification.html").include(request,response);
            }
            else if(option.equals("view")){
                request.getRequestDispatcher("/view.html").include(request,response);
            }
            else{
                request.getRequestDispatcher("/cancellation.html").include(request,response);
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
