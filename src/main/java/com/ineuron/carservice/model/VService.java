package com.ineuron.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ineuron.carservice.service.WorkshopService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "SERVICES")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class VService {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "service_name", nullable = false)
    private Integer serviceName;

    @Column(name = "slots_needed", nullable = false)
    private Integer slotsNeeded;

    @Column(name = "service_price", nullable = false)
    private Integer servicePrice;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workshop_id", nullable = false)
    private Workshop workshop;

}
