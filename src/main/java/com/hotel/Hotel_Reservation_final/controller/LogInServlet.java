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

@WebServlet(name = "LogInServlet")
public class LogInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String pass= request.getParameter("pass");
        out.println("<html><body>");
        User retrieved = UserDaoH.getUserById(Integer.parseInt(id));
        if(retrieved != null){
            if(retrieved.getPassword().equals(pass)){
                out.println("LOGIN WAS SUCCESSFUL ! WELCOME ! ");
                Cookie fnameC = new Cookie("fname",retrieved.getFname());
                Cookie lnameC = new Cookie("lname",retrieved.getLname());
                Cookie idC = new Cookie("id",String.valueOf(retrieved.getUser_id()));
                response.addCookie(fnameC);
                response.addCookie(lnameC);
                response.addCookie(idC);
                request.getRequestDispatcher("main.html").include(request,response);
            }
            else{
                out.println("Wrong password ! Please try again !");
                request.getRequestDispatcher("logIn.html").include(request,response);
            }
        }
        else{
            out.println("No such user exists ! Please sign up ...");
            request.getRequestDispatcher("signUp.html").include(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
