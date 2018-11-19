package com.halak.model.entity.factory;

import com.halak.model.entity.PlayerEntity;

import static com.halak.configuration.GameSpecifications.FIRST_PLAYER_NAME;
import static com.halak.configuration.GameSpecifications.SECOND_PLAYER_NAME;

public class PlayerEntityFactory {

    public static PlayerEntity firstPlayer() {
        return PlayerEntity.builder().kalahIndex(6).name(FIRST_PLAYER_NAME).build();
    }

    public static PlayerEntity secondPlayer() {
        return PlayerEntity.builder().kalahIndex(13).name(SECOND_PLAYER_NAME).build();

    }
}
