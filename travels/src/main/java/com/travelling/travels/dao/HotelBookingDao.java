package com.travelling.travels.dao;

import com.travelling.travels.module.HotelBookings;
import com.travelling.travels.utility.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HotelBookingDao {
    public void saveHotelBooking(HotelBookings hotelBooking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(hotelBooking);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
