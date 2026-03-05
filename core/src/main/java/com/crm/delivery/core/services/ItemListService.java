package com.crm.delivery.core.services;

import com.crm.delivery.core.dto.ItemListRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "item-Management-Service")
public interface ItemListService {
    @PutMapping("/items/itemsList")
    ResponseEntity<?> changeItemQuantity(@RequestBody List<ItemListRequest> itemsQuantity);
}
