package com.ineuron.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name= "ADDRESS")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID id;
    @Column(name = "pin_code", nullable = false)
    private String pin_code;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "address1", nullable = false)
    private Double address1;

    @Column(name = "address2")
    private Double address2;
}
