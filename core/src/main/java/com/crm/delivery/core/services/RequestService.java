package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.RequestRequest;
import com.crm.delivery.core.dto.RequestResponse;
import com.crm.delivery.core.dto.ShipmentRequest;
import com.crm.delivery.core.entities.Request;
import com.crm.delivery.core.enums.Status;
import com.crm.delivery.core.mappers.RequestMappers;
import com.crm.delivery.core.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {
    RequestRepository requestRepository;
    ShipmentService shipmentService;

    @Autowired
    public RequestService(RequestRepository requestRepository, ShipmentService shipmentService) {
        this.requestRepository = requestRepository;
        this.shipmentService = shipmentService;
    }


    // GET-запросы
    public RequestResponse getRequest(Integer id) {
        Optional<Request> request = requestRepository.findById(id);
        return request.stream()
                .map(RequestMappers::createRequestResponse)
                .findFirst()
                .orElse(null);
    }

    public List<RequestResponse> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(RequestMappers::createRequestResponse)
                .collect(Collectors.toList());
    }



    //POST-запросы
    public RequestResponse createRequest(RequestRequest requestRequest, Integer userId) {
        Request newRequest = new Request();
        newRequest.setWarehouseId(requestRequest.getWarehouseId());
        newRequest.setStatus(Status.PENDING);
        newRequest.setCondition(requestRequest.getCondition());
        newRequest.setUserId(userId);
        Request request = requestRepository.save(newRequest);

        List<ShipmentRequest> shipments = requestRequest.getShipments();
        for(ShipmentRequest shipmentRequest : shipments) {
            shipmentService.createShipment(shipmentRequest, request);
        }

        return RequestMappers.createRequestResponse(request);
    }
}
