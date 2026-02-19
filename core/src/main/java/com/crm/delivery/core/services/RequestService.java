package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.RequestRequest;
import com.crm.delivery.core.dto.RequestResponse;
import com.crm.delivery.core.dto.ShipmentRequest;
import com.crm.delivery.core.entities.Request;
import com.crm.delivery.core.enums.Status;
import com.crm.delivery.core.mappers.RequestMappers;
import com.crm.delivery.core.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RequestResponse> getRequest(Integer id) {
        Optional<Request> request = requestRepository.findById(id);
        if (request.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>( request.stream()
                .map(RequestMappers::createRequestResponse)
                .findFirst()
                .orElse(null),
                HttpStatus.OK);
    }

    public ResponseEntity<List<RequestResponse>> getAllRequests() {
        return  new ResponseEntity<>(requestRepository.findAll().stream()
                .map(RequestMappers::createRequestResponse)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }



    //POST-запросы
    public ResponseEntity<RequestResponse> createRequest(RequestRequest requestRequest, Integer userId) {
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
        return new ResponseEntity<RequestResponse> (RequestMappers.createRequestResponse(request), HttpStatus.CREATED);
    }

    //PUT
    public ResponseEntity<RequestResponse> updateRequestStatus(Integer requestId, Status status) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if(request == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        request.setStatus(status);
        Request puttedRequests = requestRepository.save(request);
        return new ResponseEntity<>(RequestMappers.createRequestResponse(puttedRequests), HttpStatus.NO_CONTENT);
    }

    //DELETE
    public ResponseEntity<RequestResponse> deleteRequest(Integer requestId) {
        Request request = requestRepository.findById(requestId).orElse(null);
        if(request == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        requestRepository.delete(request);
        return new ResponseEntity<>(RequestMappers.createRequestResponse(request), HttpStatus.NO_CONTENT);
    }

}
