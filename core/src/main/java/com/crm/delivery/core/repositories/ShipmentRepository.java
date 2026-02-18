package com.crm.delivery.core.repositories;

import com.crm.delivery.core.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    Shipment findById(int id);
    Optional<Shipment> findByRequestId(Integer requestId);
}
