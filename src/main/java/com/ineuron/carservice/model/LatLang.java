package com.ineuron.carservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LatLang {
    private Double latitude;
    private  Double longitude;
    private  String city;

}
