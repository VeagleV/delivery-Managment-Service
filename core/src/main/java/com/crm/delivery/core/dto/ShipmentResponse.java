package com.crm.delivery.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponse {
    @JsonProperty("item_id")
    private Integer itemId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("name")
    private String name;
}
