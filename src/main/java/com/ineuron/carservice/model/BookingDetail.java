package com.ineuron.carservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ineuron.carservice.service.VehicleService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "BOOKING_DETAILS")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BookingDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "is_custom_service", nullable = false)
    private boolean isCustomService;

    @Column(name = "pickup_at_door_step", nullable = false)
    private boolean pickupAtDoorStep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", nullable = false)
    private VService vehicleService;

    @Column(name = "pickup_date_time", nullable = false)
    private LocalDateTime pickup_date_time;

    @Column(name = "WALKIN_DATE_TIME", nullable = false)
    private LocalDateTime walkinDateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
}
