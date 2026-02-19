package com.crm.delivery.core.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Data
@Entity
@Table(name = "transit")
public class Transit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transit_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "warehouse_src")
    private Integer warehouseSrc;
}
