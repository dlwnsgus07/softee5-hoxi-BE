package com.allroundbeauty.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallPlaceDto {
    private String source;
    private String destination;
}
