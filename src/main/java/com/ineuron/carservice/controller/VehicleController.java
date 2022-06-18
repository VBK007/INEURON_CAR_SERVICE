package com.ineuron.carservice.controller;

import com.ineuron.carservice.model.User;
import com.ineuron.carservice.model.Vehicle;
import com.ineuron.carservice.response.SuccessResponse;
import com.ineuron.carservice.service.UserService;
import com.ineuron.carservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/{id}")
    public SuccessResponse getVehicleById(@PathVariable("id") UUID id) {
        return new SuccessResponse(vehicleService.getVehicleById(id));
    }

    @GetMapping
    public SuccessResponse getVehicles() {
        return new SuccessResponse(vehicleService.getVehicles());
    }

    @PostMapping
    public SuccessResponse createVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.saveOrUpdate(vehicle);
        return new SuccessResponse("Vehicle Created Successfully");
    }

    @PutMapping
    public SuccessResponse updateVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.saveOrUpdate(vehicle);
        return new SuccessResponse("Vehicle Updated Successfully");
    }

}
