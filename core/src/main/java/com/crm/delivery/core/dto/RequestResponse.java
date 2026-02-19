package com.crm.delivery.core.dto;

import com.crm.delivery.core.entities.Shipment;
import com.crm.delivery.core.enums.Condition;
import com.crm.delivery.core.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse {
    @JsonProperty("request_id")
    private Integer requestId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("warehouse_id")
    private Integer warehouseId;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("condition")
    private Condition condition;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("shipments")
    private List<ShipmentResponse> shipments;
}
