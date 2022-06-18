package com.ineuron.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "WORKSHOP")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Workshop {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "workshop_name", nullable = false)
    private String workshop_name;
    @Column(name = "owner_id", nullable = false)
    private Integer owner_id;
    @Column(name = "address_id", nullable = false)
    private Integer address_id;
    @Column(name = "vehicles_service_ability", nullable = false)
    private Integer vehicles_service_ability;
}
