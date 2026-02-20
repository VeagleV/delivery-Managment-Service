package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.TransitRequest;
import com.crm.delivery.core.dto.TransitResponse;
import com.crm.delivery.core.entities.Transit;
import com.crm.delivery.core.mappers.TransitMappers;
import com.crm.delivery.core.repositories.TransitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransitService {
    private final TransitRepository transitRepository;

    @Autowired
    public TransitService(TransitRepository transitRepository) {
        this.transitRepository = transitRepository;
    }

    public List<TransitResponse> getAllTransits() {
        List<Transit> transits = transitRepository.findAll();
        if (transits.isEmpty()) return null;
        return transits.stream()
                .map(TransitMappers::createTransitResponse)
                .collect(Collectors.toList());
    }

    public TransitResponse getTransitById(Integer id) {
        Transit transit = transitRepository.findById(id).orElse(null);
        if (transit == null) return null;
        return TransitMappers.createTransitResponse(transit);
    }

    public TransitResponse createTransit(TransitRequest transitRequest) {
        Transit createdTransit = transitRepository.save(TransitMappers.createTransit(transitRequest));
        if (createdTransit == null) return null;
        return TransitMappers.createTransitResponse(createdTransit);
    }
}
