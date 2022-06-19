package com.ineuron.carservice.controller;

import com.ineuron.carservice.model.Booking;
import com.ineuron.carservice.model.Vehicle;
import com.ineuron.carservice.response.SuccessResponse;
import com.ineuron.carservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public SuccessResponse getBookings() {
        return new SuccessResponse(bookingService.getBookings());
    }

    @GetMapping("/booking/shop/{id}")
    public SuccessResponse getBookingsByShop(@PathVariable("id") UUID id) {
        return new SuccessResponse(bookingService.getBookingsByShop(id));
    }

    @GetMapping("/booking/{id}")
    public SuccessResponse getBookingById(@PathVariable("id") UUID id) {
        return new SuccessResponse(bookingService.getBookingById(id));
    }

    @PostMapping("/booking")
    public SuccessResponse createBooking(@RequestBody Booking booking) {
        bookingService.saveOrUpdate(booking);
        return new SuccessResponse("Booking Created Successfully");
    }

    @PutMapping("/booking")
    public SuccessResponse updateBooking(@RequestBody Booking booking) {
        bookingService.saveOrUpdate(booking);
        return new SuccessResponse("Booking Updated Successfully");
    }
}
