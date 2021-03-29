package com.hotel.Hotel_Reservation_final.dao;

import com.hotel.Hotel_Reservation_final.dbConnection.DBConnection;
import com.hotel.Hotel_Reservation_final.model.RoomReservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class RoomReservationDao {

    private static String insertQuery = "INSERT INTO reservations (customer_id,customer_fname,customer_lname,start_date,end_date,capacity,reservation_code) VALUES (?,?,?,?,?,?,?)";
    private static String getAllReservationsQuery = "SELECT * FROM reservations WHERE customer_id = ? ";
    private static String getAllInfoByCode = "SELECT * FROM reservations WHERE reservation_code = ? ";
    private static String deleteQuery = "DELETE from reservations WHERE reservation_code = ?";
    //private static String updateQuery = "UPDATE reservations SET customer_id = ? , customer_fname = ? , customer_lname = ? start_date = ? , end_date = ? , capacity = ? WHERE reservation_code = ?";

    private static Connection connection = DBConnection.INSTANCE.getConnection();



    public static RoomReservation addRecord(int customer_id , String customer_fname , String customer_lname , String start , String end , int capacity){
        Random r = new Random( System.currentTimeMillis() );
        int random_code = r.nextInt(99999);
        try {
            PreparedStatement register = connection.prepareStatement(insertQuery);
            register.setInt(1,customer_id);
            register.setString(2,customer_fname);
            register.setString(3,customer_lname);
            register.setString(4,start);
            register.setString(5,end);
            register.setInt(6,capacity);
            register.setString(7, String.valueOf(random_code));
            register.executeUpdate();
            register.executeBatch();
            PreparedStatement getRoomNumber = connection.prepareStatement(getAllInfoByCode);
            getRoomNumber.setString(1, String.valueOf(random_code));
            ResultSet rs = getRoomNumber.executeQuery();
            rs.next();
            int room = rs.getInt("room");
            RoomReservation reservation = new RoomReservation(customer_id,customer_fname,customer_lname,start,end,capacity,String.valueOf(random_code));
            reservation.setRoom(room);
            return reservation;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void viewAllRecords(int customer_id){
        try {
            PreparedStatement getAllReservations = connection.prepareStatement(getAllReservationsQuery);
            getAllReservations.setInt(1,customer_id);
            ResultSet rs = getAllReservations.executeQuery();
            System.out.println("Customer First Name : "+rs.getString("customer_fname")+" , Customer Last Name : "+rs.getString("customer_lname"));
            while (rs.next()){
                System.out.println("\nRESERVATION CODE : "+rs.getString("reservation_code"));
                System.out.println("Starting date : "+rs.getString("start_date"));
                System.out.println("Ending date : "+rs.getString("end_date"));
                System.out.println("Room number : "+rs.getString("room"));
                System.out.println("Capacity : "+rs.getString("capacity"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void cancel(String reservation_code){
        try {
            PreparedStatement cancelReservation = connection.prepareStatement(deleteQuery);
            cancelReservation.setString(1,reservation_code);
            cancelReservation.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static RoomReservation showAllInfo (String reservation_code){
        try {
            PreparedStatement reservationInfo = connection.prepareStatement(getAllInfoByCode);
            reservationInfo.setString(1,reservation_code);
            ResultSet rs = reservationInfo.executeQuery();
            int id = rs.getInt("customer_id");
            String fname = rs.getString("customer_fname");
            String lname = rs.getString("customer_lname");
            String start = rs.getString("start_date");
            String end = rs.getString("end_date");
            int capacity = rs.getInt("capacity");
            RoomReservation reservation = new RoomReservation(id,fname,lname,start,end,capacity,reservation_code);
            return reservation;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
