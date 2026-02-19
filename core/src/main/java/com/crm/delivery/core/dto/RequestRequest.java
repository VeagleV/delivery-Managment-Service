package com.crm.delivery.core.dto;

import com.crm.delivery.core.enums.Condition;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRequest {
    @JsonProperty("warehouse_id")
    private Integer warehouseId;

    @JsonProperty("condition")
    private Condition condition;

    @JsonProperty("shipments")
    private List<ShipmentRequest> shipments;
}
