package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.AlgoRequest;
import com.crm.delivery.core.dto.SpanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "algorithm-Service")
public interface AlgoService {
    @PostMapping("/algo/startAlgorithm")
    ResponseEntity<?> startAlgorithm(AlgoRequest algoRequest);

    @GetMapping("/algo/spans")
    ResponseEntity<List<SpanResponse>> getSpanByRouteId(@RequestParam Integer routeId);

}
