package com.travelling.travels.dao;

import com.travelling.travels.module.Flights;
import com.travelling.travels.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FlightDao {
    public void saveFlight(Flights flight) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(flight);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
