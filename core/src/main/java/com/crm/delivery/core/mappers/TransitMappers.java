package com.crm.delivery.core.mappers;

import com.crm.delivery.core.dto.TransitResponse;
import com.crm.delivery.core.entities.Transit;
import org.springframework.stereotype.Component;

@Component
public class TransitMappers {
    public static TransitResponse createTransitResponse(Transit transit) {
        TransitResponse response = new TransitResponse();
        response.setRouteId(transit.getRouteId());
        response.setCost(transit.getCost());
        response.setTime(transit.getTime());
        response.setDistance(transit.getDistance());
        return response;
    }
}
