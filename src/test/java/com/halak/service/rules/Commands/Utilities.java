package com.halak.service.rules.Commands;

import com.halak.model.entity.PitEntity;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.halak.configuration.GameSpecifications.KALAHS_COUNT_PER_PLAYER;
import static com.halak.configuration.GameSpecifications.PITS_COUNT_PER_PLAYER;
import static com.halak.configuration.GameSpecifications.PLAYERS_COUNT;

@UtilityClass
public class Utilities {
    public static List<PitEntity> composeEmptyPits() {
        return IntStream.range(0, (PITS_COUNT_PER_PLAYER + KALAHS_COUNT_PER_PLAYER) * PLAYERS_COUNT)
                .mapToObj(i -> PitEntity.builder().index(i).stonesCount(0).build())
                .collect(Collectors.toList());
    }
}
