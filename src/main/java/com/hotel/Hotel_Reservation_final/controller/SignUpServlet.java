package com.hotel.Hotel_Reservation_final.controller;

import com.hotel.Hotel_Reservation_final.model.dao.UserDaoH;
import com.hotel.Hotel_Reservation_final.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String id = request.getParameter("id");
        String pass= request.getParameter("pass");
        User user = new User(Integer.parseInt(id),fname,lname,pass);
        if(UserDaoH.signUp(user)){
            out.println("SIGN UP WAS SUCCESSFUL ! WELCOME !");
            Cookie fnameC = new Cookie("fname",fname);
            Cookie lnameC = new Cookie("lname",lname);
            Cookie idC = new Cookie("id",id);
            response.addCookie(fnameC);
            response.addCookie(lnameC);
            response.addCookie(idC);
            request.getRequestDispatcher("main.html").include(request,response);
        }
        else{
            out.println("An error has occurred ! Please try again ...");
            request.getRequestDispatcher("index.jsp").include(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
