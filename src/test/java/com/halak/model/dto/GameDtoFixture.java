package com.halak.model.dto;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class GameDtoFixture {

    public static GameDto regular() {
        return GameDto.builder().gameId(UUID.randomUUID().toString())
                .activePlayer("Player1")
                .pits(PitDtoFixture.listOf14Pits())
                .build();
    }
}