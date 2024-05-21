package com.travelling.travels;

import com.travelling.travels.dao.BookingDao;
import com.travelling.travels.dao.FlightDao;
import com.travelling.travels.dao.HotelBookingDao;
import com.travelling.travels.dao.HotelDao;
import com.travelling.travels.module.Bookings;
import com.travelling.travels.module.Flights;
import com.travelling.travels.module.HotelBookings;
import com.travelling.travels.module.Hotels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Welcome to Travel Tourism App!");

        // Menu
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book a Flight");
            System.out.println("2. Book a Hotel");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    bookFlight(scanner, dateFormat);
                    break;
                case 2:
                    bookHotel(scanner, dateFormat);
                    break;
                case 3:
                    System.out.println("Thank you for using Travel Tourism App. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bookFlight(Scanner scanner, SimpleDateFormat dateFormat) {
        System.out.println("\nFlight Booking:");
        Flights flight = new Flights();

        System.out.print("Airline Name: ");
        flight.setAirlineName(scanner.nextLine());

        System.out.print("Departure Airport: ");
        flight.setDepartureAirport(scanner.nextLine());

        System.out.print("Destination Airport: ");
        flight.setDestinationAirport(scanner.nextLine());

        try {
            System.out.print("Date (yyyy-mm-dd): ");
            flight.setDate(new java.sql.Date(dateFormat.parse(scanner.nextLine()).getTime()));
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-mm-dd format.");
            return;
        }

        System.out.print("Time (hh:mm:ss): ");
        flight.setTime(java.sql.Time.valueOf(scanner.nextLine()));

        System.out.print("Price: ");
        flight.setPrice(scanner.nextDouble());

        FlightDao flightDao = new FlightDao();
        flightDao.saveFlight(flight);
        System.out.println("Flight booked successfully!");
    }

    private static void bookHotel(Scanner scanner, SimpleDateFormat dateFormat) {
        System.out.println("\nHotel Booking:");
        HotelBookings hotelBooking = new HotelBookings();

        System.out.print("User ID: ");
        hotelBooking.setUserId(scanner.nextInt());

        System.out.print("Hotel ID: ");
        hotelBooking.setHotelId(scanner.nextInt());

        scanner.nextLine(); // consume newline

        try {
            System.out.print("Check-in Date (yyyy-mm-dd): ");
            hotelBooking.setCheckInDate(new java.sql.Date(dateFormat.parse(scanner.nextLine()).getTime()));

            System.out.print("Check-out Date (yyyy-mm-dd): ");
            hotelBooking.setCheckOutDate(new java.sql.Date(dateFormat.parse(scanner.nextLine()).getTime()));
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-mm-dd format.");
            return;
        }

        System.out.print("Total Price: ");
        hotelBooking.setTotalPrice(scanner.nextDouble());

        System.out.print("Guest Count: ");
        hotelBooking.setGuestCount(scanner.nextInt());

        HotelBookingDao hotelBookingDao = new HotelBookingDao();
        hotelBookingDao.saveHotelBooking(hotelBooking);
        System.out.println("Hotel booking saved successfully!");
    }
}
