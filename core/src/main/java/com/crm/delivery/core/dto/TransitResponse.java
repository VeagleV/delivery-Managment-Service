package com.crm.delivery.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransitResponse {
    @Schema(description = "Идентификатор пути")
    @PositiveOrZero
    @JsonProperty("route_id")
    private Integer routeId;

    @Schema(description = "Общее расстояние")
    @Positive
    @JsonProperty("distance")
    private Double distance;

    @Schema(description = "Общая стоимость")
    @Positive
    @JsonProperty("cost")
    private Double cost;

    @Schema(description = "Общее время пути")
    @JsonProperty("time")
    private Duration time;
}
