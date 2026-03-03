package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.AlgoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "algorithm-Service")
public interface AlgoService {
    @PostMapping("/api/algo/startAlgorithm")
    ResponseEntity<?> startAlgorithm(AlgoRequest algoRequest);
}
