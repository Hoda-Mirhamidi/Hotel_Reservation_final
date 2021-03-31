package com.hotel.Hotel_Reservation_final.dao;

import com.hotel.Hotel_Reservation_final.hibernate.HibernateUtil;
import com.hotel.Hotel_Reservation_final.model.RoomReservation;
import org.hibernate.Session;

import java.util.Random;

public class RoomReservationDaoH {

    public static RoomReservation addRecord(int customer_id , String customer_fname , String customer_lname , String start , String end , int capacity){
        Random r = new Random( System.currentTimeMillis() );
        int random_code = r.nextInt(99999);
        RoomReservation reservation = new RoomReservation(customer_id,customer_fname,customer_lname,start,end,capacity,String.valueOf(random_code));

        Session session = HibernateUtil.sessionFactory.openSession();
        session.beginTransaction();
        session.save(reservation);
        session.getTransaction().commit();
        session.close();

        return reservation;
    }

}
