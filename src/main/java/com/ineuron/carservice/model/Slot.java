package com.ineuron.carservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ineuron.carservice.service.WorkshopService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "SLOTS_BOOKED")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Slot {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "slot_date", nullable = false)
    private Date slotDate;

    @Column(name = "slot1_booked_count", nullable = false)
    private Integer slot1BookedCount;

    @Column(name = "slot2_booked_count", nullable = false)
    private Integer slot2BookedCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workshop", nullable = false)
    private Workshop workshop;

}
