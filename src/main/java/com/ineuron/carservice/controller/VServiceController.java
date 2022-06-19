package com.ineuron.carservice.controller;


import com.ineuron.carservice.model.VService;
import com.ineuron.carservice.model.Workshop;
import com.ineuron.carservice.response.SuccessResponse;
import com.ineuron.carservice.service.VServiceService;
import com.ineuron.carservice.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class VServiceController {
    @Autowired
    private VServiceService vServiceService;

    @GetMapping("/vservice/{id}")
    public SuccessResponse getVServicesById(@PathVariable("id") UUID id) {
        return new SuccessResponse(vServiceService.getVServiceById(id));
    }

    @GetMapping("/vservice/workshop/{id}")
    public SuccessResponse getVServicesByWorkshop(@PathVariable("id") UUID id) {
        return new SuccessResponse(vServiceService.getVServicesbyWorkshop(id));
    }

    @PostMapping("/vservice")
    public SuccessResponse createWorkshop(@RequestBody VService vService) {
        vServiceService.saveOrUpdate(vService);
        return new SuccessResponse("Vehicle Services Created Successfully");
    }

    @PutMapping("/vservice")
    public SuccessResponse updateWorkshop(@RequestBody VService vService) {
        vServiceService.saveOrUpdate(vService);
        return new SuccessResponse("Vehicle servicess Updated Successfully");
    }

}
