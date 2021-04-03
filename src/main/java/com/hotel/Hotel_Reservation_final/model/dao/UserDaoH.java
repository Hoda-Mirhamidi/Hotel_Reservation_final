package com.hotel.Hotel_Reservation_final.model.dao;

import com.hotel.Hotel_Reservation_final.hibernate.HibernateUtil;
import com.hotel.Hotel_Reservation_final.model.entity.RoomReservation;
import com.hotel.Hotel_Reservation_final.model.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoH {

    public static boolean signUp(User user){
        Session session = HibernateUtil.sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(user);
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

    public static User getUserById(int user_id){

        Session session = HibernateUtil.sessionFactory.openSession();
        User user = session.get(User.class,user_id);
        session.close();
        return user;
    }

}
