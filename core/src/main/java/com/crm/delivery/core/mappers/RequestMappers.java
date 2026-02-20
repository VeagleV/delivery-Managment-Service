package com.crm.delivery.core.mappers;

import com.crm.delivery.core.dto.RequestRequest;
import com.crm.delivery.core.dto.RequestResponse;
import com.crm.delivery.core.entities.Request;
import com.crm.delivery.core.enums.Status;
import com.crm.delivery.core.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    public static Request createRequest(RequestRequest requestRequest, Integer userId) {
        Request request = new Request();
        return getRequest(request, requestRequest, userId);
    }

    public static Request updateRequest(Request existingRequest, RequestRequest requestRequest, Integer userId) {
        return getRequest(existingRequest, requestRequest, userId);
    }

    private static Request getRequest(Request request, RequestRequest requestRequest, Integer userId) {
        request.setWarehouseId(requestRequest.getWarehouseId());
        request.setCondition(requestRequest.getCondition());
        request.setStatus(Status.PENDING);
        request.setCreatedAt(LocalDateTime.now());
        request.setUserId(userId);
        return request;
    }

}
