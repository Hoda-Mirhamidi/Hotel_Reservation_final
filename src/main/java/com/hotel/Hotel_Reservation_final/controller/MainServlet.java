package com.hotel.Hotel_Reservation_final.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //PrintWriter out = response.getWriter();
        String option = request.getParameter("options");
        if(option.equals("reserve")){
            request.getRequestDispatcher("/reservation.html").include(request,response);
        }
        else if(option.equals("modify")){
            request.getRequestDispatcher("/modification.html");
        }
        else if(option.equals("view")){
            request.getRequestDispatcher("/view.html");
        }
        else{
            request.getRequestDispatcher("/cancellation.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
