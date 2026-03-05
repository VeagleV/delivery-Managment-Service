package com.crm.delivery.core.services;

import com.sun.jdi.connect.Transport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "transport-Management-Service")
public interface TransportService {
    @PutMapping("/transports/send")
    ResponseEntity<?> setInTransitStatus(@RequestBody List<Integer> transportIdList);

    @PutMapping("/transports/RTB")
    ResponseEntity<?> setGoBackStatus(@RequestBody List<Integer> transportIdList);
}
