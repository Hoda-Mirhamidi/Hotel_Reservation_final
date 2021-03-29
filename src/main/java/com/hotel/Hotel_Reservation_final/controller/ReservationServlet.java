package com.hotel.Hotel_Reservation_final.controller;

import com.hotel.Hotel_Reservation_final.dao.RoomReservationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReservationServlet")
public class ReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        int id = Integer.parseInt(request.getParameter("id"));
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        RoomReservationDao.addRecord(id,fname,lname,start,end,capacity);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
