package com.hotel.Hotel_Reservation_final.model.dao;

import com.hotel.Hotel_Reservation_final.hibernate.HibernateUtil;
import com.hotel.Hotel_Reservation_final.model.entity.RoomReservation;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoomReservationDaoH {


    public static RoomReservation keptReservation = new RoomReservation();

    public static RoomReservation addRecord(RoomReservation reservation){
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction =  session.beginTransaction();
            session.save(reservation);
            transaction.commit();
            return reservation;
        }catch (HibernateException exception){
            if(transaction != null){
                transaction.rollback();
            }
            return null;
        }finally {
            session.close();
        }
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

    public static RoomReservation getReservationByCode(String reservation_code){

        Session session = HibernateUtil.sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<RoomReservation> cq = cb.createQuery(RoomReservation.class);
        Root<RoomReservation> root = cq.from(RoomReservation.class);
        cq.select(root).where(cb.equal(root.get("reservation_code"),reservation_code));
        List reservations = session.createQuery(cq).getResultList();
        session.close();
        if(reservations.size() == 0){
            return null;
        }
        return (RoomReservation) reservations.get(0);
    }

    public static boolean cancel(String reservation_code){

        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            RoomReservation reservation = getReservationByCode(reservation_code);
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

    public static boolean updateInfo(RoomReservation reservation){

        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.update(reservation);
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
