package com.halak.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PitDto {

    private int stonesCount;

    private int index;
}
