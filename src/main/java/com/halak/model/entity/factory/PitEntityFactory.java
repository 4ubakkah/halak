package com.halak.model.entity.factory;

import com.halak.model.entity.PitEntity;
import lombok.experimental.UtilityClass;

import static com.halak.configuration.GameSpecifications.INITIAL_STONES_COUNT_PER_KALAH;
import static com.halak.configuration.GameSpecifications.INITIAL_STONES_COUNT_PER_PIT;

@UtilityClass
public class PitEntityFactory {

    public static PitEntity pit(int index) {
        return PitEntity.builder().stonesCount(INITIAL_STONES_COUNT_PER_PIT).index(index).kalah(false).build();
    }

    public static PitEntity kalahPit(int index) {
        return PitEntity.builder().stonesCount(INITIAL_STONES_COUNT_PER_KALAH).index(index).kalah(true).build();
    }
}
