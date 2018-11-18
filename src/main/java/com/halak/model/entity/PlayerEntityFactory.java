package com.halak.model.entity;

import static com.halak.configuration.GameSpecifications.FIRST_PLAYER_NAME;
import static com.halak.configuration.GameSpecifications.SECOND_PLAYER_NAME;

public class PlayerEntityFactory {

    public static PlayerEntity firstPlayer() {
        return new PlayerEntity(6, FIRST_PLAYER_NAME);
    }

    public static PlayerEntity secondPlayer() {
        return new PlayerEntity(13, SECOND_PLAYER_NAME);
    }
}
