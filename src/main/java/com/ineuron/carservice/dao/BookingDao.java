package com.ineuron.carservice.dao;

import com.ineuron.carservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingDao extends JpaRepository<Booking, UUID> {

}
