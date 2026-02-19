package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.TransitRequest;
import com.crm.delivery.core.dto.TransitResponse;
import com.crm.delivery.core.entities.Transit;
import com.crm.delivery.core.mappers.TransitMappers;
import com.crm.delivery.core.repositories.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransitService {
    private final TransitRepository transitRepository;

    @Autowired
    public TransitService(TransitRepository transitRepository) {
        this.transitRepository = transitRepository;
    }

    public ResponseEntity<TransitResponse> createTransit(TransitRequest transitRequest) {
        Transit transit = new Transit();
        transit.setRouteId(transitRequest.getRouteId());
        transit.setCost(transitRequest.getCost());
        transit.setTime(transitRequest.getTime());
        transit.setDistance(transitRequest.getDistance());
        Transit createdTransit = transitRepository.save(transit);
        return new ResponseEntity<>(TransitMappers.createTransitResponse(createdTransit), HttpStatus.CREATED);
    }
}
