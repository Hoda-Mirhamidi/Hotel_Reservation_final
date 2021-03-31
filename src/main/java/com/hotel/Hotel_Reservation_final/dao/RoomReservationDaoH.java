package com.hotel.Hotel_Reservation_final.dao;

import com.hotel.Hotel_Reservation_final.hibernate.HibernateUtil;
import com.hotel.Hotel_Reservation_final.model.RoomReservation;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
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

    public static List viewAllRecords(int customer_id){

        Session session = HibernateUtil.sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<RoomReservation> criteriaQuery = criteriaBuilder.createQuery(RoomReservation.class);
        Root<RoomReservation> root = criteriaQuery.from(RoomReservation.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("customer_id"),customer_id));
        Query query = session.createQuery(criteriaQuery);
        List reservations = query.getResultList();
        session.close();
        return reservations;
    }

    public static RoomReservation showAllInfo (String reservation_code){

        Session session = HibernateUtil.sessionFactory.openSession();
        RoomReservation reservation = session.get(RoomReservation.class,reservation_code);
        session.close();
        return reservation;
    }

    public static boolean cancel(String reservation_code){

        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            RoomReservation reservation = showAllInfo(reservation_code);
            session.delete(reservation);
            transaction.commit();
            return true;
        }catch (HibernateException exception){
            if(transaction != null){
              transaction.rollback();
            }
            return false;
        }finally {
            session.close();
        }
    }



}
