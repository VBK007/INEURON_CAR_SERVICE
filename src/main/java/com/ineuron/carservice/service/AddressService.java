package com.ineuron.carservice.service;

import com.ineuron.carservice.dao.AddressDao;
import com.ineuron.carservice.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ineuron.carservice.model.LatLang;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return dist * 1.609344;


    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public Address saveOrUpdate(final Address address) {
        return addressDao.save(address);
    }

    public Address getAddressById(UUID id) {
        Optional<Address> customerObj = addressDao.findById(id);
        return customerObj.orElse(null);
    }

    public ArrayList<Address> listNearestWorkshops(LatLang latlang){
        String city = latlang.getCity();
        Double lat = latlang.getLatitude();
        Double lng = latlang.getLongitude();
        List<Address> workshopsInCity = addressDao.findAllByCity(city);
        ArrayList<Address> nearest = new ArrayList<Address>();
        for (Address loc:workshopsInCity){
            double d = distance(loc.getLatitude(),loc.getLongitude(),lat,lng);
            if (nearest.size() < 5){
                loc.setDistance(d);
                nearest.add(loc);
            } else {
                nearest.sort((a,b)->a.getDistance().compareTo(b.getDistance()));

                for(int i=0;i<5;i++){
                        double currAddressDistance = nearest.get(i).getDistance();
                        if (currAddressDistance> d){
                            nearest.remove(4);
                            nearest.add(i,loc);
                        }

                    }


            }
        }
        return nearest;

    }

}
