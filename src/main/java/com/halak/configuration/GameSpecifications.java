package com.halak.configuration;

public class GameSpecifications {

    public static final int PITS_COUNT_PER_PLAYER = 6;

    public static final int STONES_COUNT_PER_PIT = 6;

    public static final int KALAHS_COUNT_PER_PLAYER = 1;

    public static final int PLAYERS_COUNT = 2;

    public static final int PITS_COUNT_OVERALL = (GameSpecifications.PITS_COUNT_PER_PLAYER + GameSpecifications.KALAHS_COUNT_PER_PLAYER) * GameSpecifications.PLAYERS_COUNT;

    public static final String FIRST_PLAYER_NAME = "South";

    public static final String SECOND_PLAYER_NAME = "North";

    public static final String DRAW_GAME_TEXT = "None. Draw Game";

}
