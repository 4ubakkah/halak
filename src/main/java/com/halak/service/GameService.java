package com.halak.service;

import com.halak.model.game.Game;

import java.util.Optional;

public interface GameService {

    Game create();

    Game play(String gameId, int pitId) throws Exception;

    Optional<Game> getInfo(String gameId);
}
