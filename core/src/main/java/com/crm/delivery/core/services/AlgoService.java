package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.AlgoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "algorithm-Service", url = "http://91.193.239.198:81/api/algo")
public interface AlgoService {
    @PostMapping("/startAlgorithm")
    ResponseEntity<?> startAlgorithm(AlgoRequest algoRequest);
}
