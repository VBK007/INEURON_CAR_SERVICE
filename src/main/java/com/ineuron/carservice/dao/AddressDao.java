package com.ineuron.carservice.dao;

import com.ineuron.carservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressDao extends JpaRepository<Address, UUID> {
    List<Address> findAllByCity(String city);
}