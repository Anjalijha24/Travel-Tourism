package com.travelling.travels.dao;

import com.travelling.travels.module.HotelBookings;
import com.travelling.travels.module.Bookings;
import com.travelling.travels.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookingDao {
    public void saveBooking(Bookings booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(booking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}