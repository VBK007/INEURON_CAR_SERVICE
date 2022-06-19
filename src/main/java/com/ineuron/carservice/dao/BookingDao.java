package com.ineuron.carservice.dao;

import com.ineuron.carservice.model.Booking;
import com.ineuron.carservice.model.VService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingDao extends JpaRepository<Booking, UUID> {
    List<Booking> findByWorkshop_id(UUID id);

}
