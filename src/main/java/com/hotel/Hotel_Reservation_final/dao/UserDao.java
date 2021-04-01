package com.hotel.Hotel_Reservation_final.dao;

import com.hotel.Hotel_Reservation_final.hibernate.HibernateUtil;
import com.hotel.Hotel_Reservation_final.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {

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
}
