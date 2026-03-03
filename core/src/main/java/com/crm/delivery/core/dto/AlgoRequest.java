package com.crm.delivery.core.dto;

import com.crm.delivery.core.enums.Condition;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlgoRequest {

    @JsonProperty("request_id")
    private Integer requestId;

    @PositiveOrZero
    @JsonProperty("warehouse_id")
    private Integer warehouseId;

    @JsonProperty("condition")
    private Condition condition;

    @JsonProperty("shipments")
    private List<ShipmentRequest> shipmentRequestList;


}