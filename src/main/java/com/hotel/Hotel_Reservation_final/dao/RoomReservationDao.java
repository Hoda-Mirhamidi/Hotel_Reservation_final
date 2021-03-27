package com.hotel.Hotel_Reservation_final.dao;

import com.hotel.Hotel_Reservation_final.dbConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomReservationDao {

    private static String insertQuery = "INSERT INTO reservations (customer_id,customer_fname,customer_lname,start_date,end_date,capacity,code) VALUES (?,?,?,?,?,?,?)";
    private static String getAllReservationsQuery = "SELECT * WHERE customer_id = ? ";
    private static String getRoomQuery = "SELECT room WHERE reservation_code = ? ";
    private static Connection connection = DBConnection.INSTANCE.getConnection();
    private static int first_code=10100;


    public static void addRecord(int customer_id , String customer_fname , String customer_lname , String start , String end , int capacity){
        try {
            PreparedStatement register = connection.prepareStatement(insertQuery);
            register.setInt(1,customer_id);
            register.setString(2,customer_fname);
            register.setString(3,customer_lname);
            register.setString(4,start);
            register.setString(5,end);
            register.setInt(6,capacity);
            register.setString(7, String.valueOf(first_code));
            register.executeUpdate();
            register.executeBatch();
            PreparedStatement getRoomNumber = connection.prepareStatement(getRoomQuery);
            getRoomNumber.setString(1, String.valueOf(first_code));
            ResultSet rs = getRoomNumber.executeQuery();
            int room = rs.getInt("room");
            System.out.println("Reservation code : "+first_code+"\nRoom number : "+room);
            first_code++;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("An error occurred ! Please try again ...");
        }
}
}
