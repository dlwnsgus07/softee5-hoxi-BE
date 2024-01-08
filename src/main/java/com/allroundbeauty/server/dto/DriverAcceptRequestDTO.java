package com.allroundbeauty.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DriverAcceptRequestDTO {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("call_id")
    private Long callId;
    @JsonProperty("position_x")
    private double positionX;
    @JsonProperty("position_y")
    private double positionY;
}
