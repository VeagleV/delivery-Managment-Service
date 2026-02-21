package com.crm.delivery.core.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(nullable = false)
    private Integer quantity;
}
