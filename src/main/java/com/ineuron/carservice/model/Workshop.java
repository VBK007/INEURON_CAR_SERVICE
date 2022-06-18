package com.ineuron.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name= "WORKSHOP")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Workshop {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID id;
    @Column(name = "workshop_name", nullable = false)
    private String workshop_name;
    @Column(name = "owner_id", nullable = false)
    private Integer owner_id;
    @Column(name = "address_id", nullable = false)
    private Integer address_id;
    @Column(name = "vehicles_service_ability", nullable = false)
    private Integer vehicles_service_ability;
}
