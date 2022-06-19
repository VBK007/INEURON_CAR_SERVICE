package com.ineuron.carservice.controller;


import com.ineuron.carservice.model.Address;
import com.ineuron.carservice.model.LatLang;
import com.ineuron.carservice.response.SuccessResponse;
import com.ineuron.carservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address/{id}")
    public SuccessResponse getAddressById(@PathVariable("id") UUID id) {
        return new SuccessResponse(addressService.getAddressById(id));
    }

    @PostMapping("/address")
    public SuccessResponse createAddress(@RequestBody Address address) {
        addressService.saveOrUpdate(address);
        return new SuccessResponse("Address Created Successfully");
    }

    @PutMapping("/address")
    public SuccessResponse updateAddress(@RequestBody Address address) {
        addressService.saveOrUpdate(address);
        return new SuccessResponse("Address Updated Successfully");
    }

    @GetMapping("/nearestWorkshops")
    public SuccessResponse getNearestWorkshops(@RequestBody LatLang latlang) {
        return new SuccessResponse(addressService.listNearestWorkshops(latlang));

    }

}
