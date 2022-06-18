package com.ineuron.carservice.dao;

import com.ineuron.carservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, UUID> {

}