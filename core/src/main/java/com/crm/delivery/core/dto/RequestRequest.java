package com.crm.delivery.core.dto;

import com.crm.delivery.core.entities.Shipment;
import com.crm.delivery.core.enums.Condition;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestRequest {
    @JsonProperty("warehouse_id")
    private Integer warehouseId;

    @JsonProperty("condition")
    private Condition condition;

    @JsonProperty("shipments")
    private List<ShipmentRequest> shipments;

}
