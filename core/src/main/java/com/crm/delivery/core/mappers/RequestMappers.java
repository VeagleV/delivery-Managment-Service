package com.crm.delivery.core.mappers;

import com.crm.delivery.core.dto.RequestResponse;
import com.crm.delivery.core.entities.Request;
import com.crm.delivery.core.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestMappers {

    public static ShipmentService shipmentService;
    @Autowired
    public void setShipmentService(ShipmentService shipmentService) {
        RequestMappers.shipmentService = shipmentService;
    }

    public static RequestResponse createRequestResponse(Request request) {
        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setRequestId(request.getId());
        requestResponse.setUserId(request.getUserId());
        requestResponse.setWarehouseId(request.getWarehouseId());
        requestResponse.setCondition(request.getCondition());
        requestResponse.setStatus(request.getStatus());
        requestResponse.setCreatedAt(request.getCreatedAt());
        requestResponse.setShipments(shipmentService.getAllShipmentsByRequestId(request.getId()));
        return requestResponse;
    }


}
