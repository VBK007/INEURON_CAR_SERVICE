package com.ineuron.carservice.dao;

import com.ineuron.carservice.model.VService;
import com.ineuron.carservice.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VServiceDao extends JpaRepository<VService, UUID> {
        List<VService> findByWorkshop_id(UUID workshop_id);

}