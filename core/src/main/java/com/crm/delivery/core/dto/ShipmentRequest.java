package com.crm.delivery.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentRequest {
    @Schema(description = "Идентификатор товара")
    @PositiveOrZero
    @JsonProperty("item_id")
    private Integer itemId;

    @Schema(description = "Количество товара")
    @Positive
    @JsonProperty("quantity")
    private Integer quantity;

}
