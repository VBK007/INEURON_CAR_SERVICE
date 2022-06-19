package com.ineuron.carservice.service;

import com.ineuron.carservice.dao.VServiceDao;
import com.ineuron.carservice.dao.WorkshopDao;
import com.ineuron.carservice.model.VService;
import com.ineuron.carservice.model.Workshop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VServiceService {

    @Autowired
    private VServiceDao vServiceDao;

    public VService saveOrUpdate(final VService vService) {
        return vServiceDao.save(vService);
    }

    public VService getVServiceById(UUID id) {
        Optional<VService> customerObj = vServiceDao.findById(id);
        return customerObj.orElse(null);

    }

//    public List<VService> getVServices() {
//        return vServiceDao.findAll();
//    }

    public List<VService> getVServicesbyWorkshop(UUID workshop_id){
        return vServiceDao.findByWorkshop_id(workshop_id);

    }
}
