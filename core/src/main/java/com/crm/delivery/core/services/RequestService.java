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
    private final RequestRepository requestRepository;
    private final ShipmentService shipmentService;

    @Autowired
    public RequestService(RequestRepository requestRepository, ShipmentService shipmentService) {
        this.requestRepository = requestRepository;
        this.shipmentService = shipmentService;
    }


    // GET-запросы
    public RequestResponse getRequest(Integer id) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isEmpty()) return null;
        return request.stream()
                .map(RequestMappers::createRequestResponse)
                .findFirst()
                .orElse(null);
    }

    public List<RequestResponse> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        if (requests.isEmpty()) return null;
        return  requests.stream()
                .map(RequestMappers::createRequestResponse)
                .collect(Collectors.toList());
    }

    //POST-запросы
    public RequestResponse createRequest(RequestRequest requestRequest, Integer userId) {
        Request createdRequest = requestRepository.save(RequestMappers.createRequest(requestRequest, userId));
        List<ShipmentRequest> shipments = requestRequest.getShipments();
        for(ShipmentRequest shipmentRequest : shipments) {
            shipmentService.createShipment(shipmentRequest, createdRequest);
        }
        return RequestMappers.createRequestResponse(createdRequest);
    }

    //PUT
    public RequestResponse updateRequestStatus(Integer requestId, Status status) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if(request == null) return null;
        request.setStatus(status);
        Request puttedRequests = requestRepository.save(request);
        return RequestMappers.createRequestResponse(puttedRequests);
    }

    //DELETE
    public RequestResponse deleteRequest(Integer requestId) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if(request == null) return null;
        requestRepository.delete(request);
        return RequestMappers.createRequestResponse(request);
    }

}
