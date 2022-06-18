package com.ineuron.carservice.controller;


import com.ineuron.carservice.model.Workshop;
import com.ineuron.carservice.response.SuccessResponse;
import com.ineuron.carservice.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/workshop")
public class VehicleController {
    @Autowired
    private WorkshopService workshopService;

    @GetMapping("/{id}")
    public SuccessResponse getWorkshopById(@PathVariable("id") UUID id) {
        return new SuccessResponse(workshopService.getWorkshopById(id));
    }

    @GetMapping
    public SuccessResponse getWorkshops() {
        return new SuccessResponse(workshopService.getWorkshops());
    }

    @PostMapping
    public SuccessResponse createWorkshop(@RequestBody Workshop workshop) {
        workshopService.saveOrUpdate(workshop);
        return new SuccessResponse("Workshop Created Successfully");
    }

    @PutMapping
    public SuccessResponse updateWorkshop(@RequestBody Workshop workshop) {
        workshopService.saveOrUpdate(workshop);
        return new SuccessResponse("Workshop Updated Successfully");
    }

}
