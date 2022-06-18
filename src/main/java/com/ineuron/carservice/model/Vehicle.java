package com.ineuron.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name= "VEHICLE_TYPES")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Vehicle {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID id;


    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "vehicle_type", nullable = false)
    private String vehicleType;

}
