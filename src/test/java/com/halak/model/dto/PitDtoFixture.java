package com.halak.model.dto;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PitDtoFixture {

    public static List<PitDto> listOf14Pits() {
        List<PitDto> pits = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            pits.add(regular());
        }

        return pits;
    }

    private static PitDto regular() {
        return PitDto.builder().stonesCount(6).build();
    }
}