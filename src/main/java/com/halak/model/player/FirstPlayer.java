package com.halak.model.player;

import lombok.Data;

@Data
public class FirstPlayer implements Player {

    private static final String FIRST_PLAYER_NAME = "First Player (South Side)";

    @Override
    public String getName() {
        return FIRST_PLAYER_NAME;
    }

    @Override
    public int getKalahIndex() {
        return 6;
    }
}
