package com.ineuron.carservice.service;

import com.ineuron.carservice.dao.WorkshopDao;
import com.ineuron.carservice.model.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopDao workshopDao;

    public Workshop saveOrUpdate(final Workshop vehicle) {
        return workshopDao.save(vehicle);
    }

    public Workshop getWorkshopById(UUID id) {
        Optional<Workshop> customerObj = workshopDao.findById(id);
        return customerObj.orElse(null);
    }

    public List<Workshop> getWorkshops() {
        return workshopDao.findAll();
    }


}
