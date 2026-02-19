package com.crm.delivery.core.controllers;

import com.crm.delivery.core.dto.RequestRequest;
import com.crm.delivery.core.dto.RequestResponse;
import com.crm.delivery.core.entities.Request;
import com.crm.delivery.core.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {
    RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RequestResponse>> getRequests() {
        List<RequestResponse> requests = requestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponse> getRequestById(@PathVariable Integer id) {
        RequestResponse request = requestService.getRequest(id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<RequestResponse> createRequest(@PathVariable Integer userId ,
                                                        @RequestBody RequestRequest request) {
        RequestResponse response = requestService.createRequest(request, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
