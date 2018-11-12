package com.halak.model.game;

public class GameSpecifications {

    public static final int PITS_COUNT_PER_PLAYER = 6;

    public static final int STONES_COUNT_PER_PIT = 6;

    public static final int KALAHS_COUNT_PER_PLAYER = 1;

    public static final int PLAYERS_COUNT = 2;

    public static final int PITS_COUNT_OVERALL = (GameSpecifications.PITS_COUNT_PER_PLAYER + GameSpecifications.KALAHS_COUNT_PER_PLAYER) * GameSpecifications.PLAYERS_COUNT;

}
