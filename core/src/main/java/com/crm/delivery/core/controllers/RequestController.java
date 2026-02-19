package com.crm.delivery.core.controllers;

import com.crm.delivery.core.dto.RequestRequest;
import com.crm.delivery.core.dto.RequestResponse;
import com.crm.delivery.core.dto.TransitRequest;
import com.crm.delivery.core.dto.TransitResponse;
import com.crm.delivery.core.enums.Status;
import com.crm.delivery.core.services.RequestService;
import com.crm.delivery.core.services.TransitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final TransitService transitService;
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService, TransitService transitService) {
        this.requestService = requestService;
        this.transitService = transitService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RequestResponse>> getRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<RequestResponse> getRequestById(@PathVariable Integer requestId) {
        return requestService.getRequest(requestId);
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //TODO: user_id потом будем получать из jwt (надеюсь)
    @PostMapping("/{userId}")
    public ResponseEntity<RequestResponse> createRequest(@PathVariable Integer userId ,
                                                        @RequestBody RequestRequest request) {
        return requestService.createRequest(request, userId);
    }

    @PutMapping("/status/{requestId}")
    public ResponseEntity<RequestResponse> updateRequestStatus(@PathVariable Integer requestId,
                                                               @RequestParam Status status) {
        RequestResponse request = requestService.getRequest(requestId).getBody();
        return requestService.updateRequestStatus(requestId, status);
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<RequestResponse> deleteRequest(@PathVariable Integer requestId) {
        return requestService.deleteRequest(requestId);
    }

    @PostMapping("/transit")
    public ResponseEntity<TransitResponse> createTransit(@RequestBody TransitRequest request) {
        return transitService.createTransit(request);
    }
}
