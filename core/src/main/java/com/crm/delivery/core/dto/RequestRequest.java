package com.crm.delivery.core.dto;

import com.crm.delivery.core.enums.Condition;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность запроса - форма запроса(request)")
public class RequestRequest {
    @Schema(description = "Идентификатор склада")
    @PositiveOrZero
    @JsonProperty("warehouse_id")
    private Integer warehouseId;

    @Schema(description = "Условие доставки", allowableValues = {"CHEAPER", "FASTER"})
    @JsonProperty("condition")
    private Condition condition;

    @Schema(description = "Партия товаров")
    @NotEmpty
    @JsonProperty("shipments")
    private List<ShipmentRequest> shipments;
}
