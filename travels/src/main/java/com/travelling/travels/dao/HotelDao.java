package com.travelling.travels.dao;

import com.travelling.travels.module.Hotels; // Importing the Hotels class
import com.travelling.travels.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HotelDao {
    public void saveHotel(Hotels hotel) {
        Transaction transaction = null;         
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(hotel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
