package com.ineuron.carservice.service;

import com.ineuron.carservice.dao.VehicleDao;
import com.ineuron.carservice.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    public Vehicle saveOrUpdate(final Vehicle vehicle) {
        return vehicleDao.save(vehicle);
    }

    public Vehicle getVehicleById(UUID id) {
        Optional<Vehicle> customerObj = vehicleDao.findById(id);
        return customerObj.orElse(null);
    }

    public List<Vehicle> getVehicles() {
        return vehicleDao.findAll();
    }


}
