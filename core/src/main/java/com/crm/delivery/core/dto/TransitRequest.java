package com.crm.delivery.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransitRequest {
    @JsonProperty("route_id")
    private Integer routeId;

    @JsonProperty("distance")
    private Double distance;

    @JsonProperty("cost")
    private Double cost;

    @JsonProperty("time")
    private Duration time;
}
