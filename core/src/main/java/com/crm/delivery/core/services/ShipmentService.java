package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.ShipmentRequest;
import com.crm.delivery.core.dto.ShipmentResponse;
import com.crm.delivery.core.entities.Request;
import com.crm.delivery.core.entities.Shipment;
import com.crm.delivery.core.mappers.ShipmentMappers;
import com.crm.delivery.core.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<ShipmentResponse> getAllShipmentsByRequestId(Integer requestId) {
        List<Shipment> shipments = shipmentRepository.findByRequestId(requestId);
        return shipments.stream()
                .map(ShipmentMappers::createShipmentResponse)
                .collect(Collectors.toList());
    }

    public void createShipment(ShipmentRequest shipment, Request request) {
        Shipment newShipment = new Shipment();
        newShipment.setRequest(request);
        newShipment.setItemId(shipment.getItemId());
        newShipment.setQuantity(shipment.getQuantity());
        shipmentRepository.save(newShipment);
    }

}
