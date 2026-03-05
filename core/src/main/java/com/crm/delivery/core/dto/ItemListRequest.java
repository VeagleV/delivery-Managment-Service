package com.crm.delivery.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemListRequest {
    @JsonProperty("item_id")
    private Integer itemId;
    @JsonProperty("warehouse_id")
    private Integer warehouseId;
    @JsonProperty("quantity")
    private Integer quantity;
}
