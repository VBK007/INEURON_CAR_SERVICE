package com.ineuron.carservice.service;

import com.ineuron.carservice.dao.AddressDao;
import com.ineuron.carservice.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    public Address saveOrUpdate(final Address address) {
        return addressDao.save(address);
    }

    public Address getAddressById(UUID id) {
        Optional<Address> customerObj = addressDao.findById(id);
        return customerObj.orElse(null);
    }

}
