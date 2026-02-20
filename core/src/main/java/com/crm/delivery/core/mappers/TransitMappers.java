package com.crm.delivery.core.mappers;

import com.crm.delivery.core.dto.TransitRequest;
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

    public static Transit createTransit(TransitRequest transitRequest) {
        Transit transit = new Transit();
        return getTransit(transit, transitRequest);
    }

    public static Transit updateTransport(Transit existingTransit, TransitRequest transitRequest) {
        return getTransit(existingTransit, transitRequest);
    }

    private static Transit getTransit(Transit transit, TransitRequest transitRequest) {
        transit.setRouteId(transitRequest.getRouteId());
        transit.setCost(transitRequest.getCost());
        transit.setTime(transitRequest.getTime());
        transit.setDistance(transitRequest.getDistance());
        return transit;
    }

}
