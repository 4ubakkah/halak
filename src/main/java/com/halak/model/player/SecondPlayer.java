package com.halak.model.player;

import lombok.Data;

@Data
public class SecondPlayer implements Player {

    private static final String SECOND_PLAYER_NAME = "Second Player (North Side)";

    @Override
    public String getName() {
        return SECOND_PLAYER_NAME;
    }

    @Override
    public int getKalahIndex() {
        return 13;
    }
}
