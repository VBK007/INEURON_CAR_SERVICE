package com.ineuron.carservice.service;

import com.ineuron.carservice.dao.BookingDao;
import com.ineuron.carservice.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingDao bookingDao;

    public Booking saveOrUpdate(final Booking booking) {
        return bookingDao.save(booking);
    }

    public Booking getBookingById(UUID id) {
        Optional<Booking> bookingObj = bookingDao.findById(id);
        return bookingObj.orElse(null);
    }

    public List<Booking> getBookings() {
        return bookingDao.findAll();
    }

    public List<Booking> getBookingsByShop(UUID id) {
        return bookingDao.findByWorkshop_id(id);
    }


}
