package com.hotel.Hotel_Reservation_final.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogOutServlet")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<html><body>");

        Cookie fname=new Cookie("fname","");
        Cookie lname= new Cookie("lname","");
        Cookie id=new Cookie("id","");
        fname.setMaxAge(0);
        lname.setMaxAge(0);
        id.setMaxAge(0);
        response.addCookie(fname);
        response.addCookie(lname);
        response.addCookie(id);

        out.print("you are successfully logged out!");
        request.getRequestDispatcher("index.jsp").include(request, response);
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
