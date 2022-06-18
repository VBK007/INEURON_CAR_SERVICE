package com.ineuron.carservice.dao;

import com.ineuron.carservice.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkshopDao extends JpaRepository<Workshop, UUID> {

}