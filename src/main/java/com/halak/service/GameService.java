package com.halak.service;

import com.halak.model.entity.GameState;

import java.util.Optional;

public interface GameService {

    GameState create();

    GameState play(Long gameId, int pitIndex) throws Exception;

    Optional<GameState> getInfo(Long gameId);
}
