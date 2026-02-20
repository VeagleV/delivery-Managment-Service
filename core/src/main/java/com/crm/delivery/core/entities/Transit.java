package com.crm.delivery.core.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;

@Data
@Entity
@Table(name = "transit")
public class Transit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transit_id")
    private Integer id;

    @Column(name = "route_id")
    private Integer routeId;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "time", columnDefinition = "INTERVAL")
    private Duration time;
}
