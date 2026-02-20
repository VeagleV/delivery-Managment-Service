package com.crm.delivery.core.dto;

import com.crm.delivery.core.entities.Shipment;
import com.crm.delivery.core.enums.Condition;
import com.crm.delivery.core.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse {

    @Schema(description = "Идентификатор заявки")
    @PositiveOrZero
    @JsonProperty("request_id")
    private Integer requestId;

    @Schema(description = "Идентификатор пользователя")
    @PositiveOrZero
    @JsonProperty("user_id")
    private Integer userId;

    @Schema(description = "Идентификатор склада")
    @PositiveOrZero
    @JsonProperty("warehouse_id")
    private Integer warehouseId;

    @Schema(description = "Статус заявки")
    @JsonProperty("status")
    private Status status;

    @Schema(description = "Условие доставки")
    @JsonProperty("condition")
    private Condition condition;

    @Schema(description = "Время создания заявки")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "Партия товаров")
    @NotEmpty
    @JsonProperty("shipments")
    private List<ShipmentResponse> shipments;
}
