package com.halak.model.dto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GameDtoFixture {

    public static GameDto regular() {
        return GameDto.builder().gameId(1L)
                .activePlayer("Player1")
                .pits(PitDtoFixture.listOf14Pits())
                .build();
    }
}