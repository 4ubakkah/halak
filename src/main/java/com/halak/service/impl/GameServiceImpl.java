package com.halak.service.impl;

import com.halak.model.entity.GameState;
import com.halak.model.entity.GameStateFactory;
import com.halak.model.exception.GameDoesntExistException;
import com.halak.model.mapper.GameMapper;
import com.halak.persistance.GameStateRepository;
import com.halak.service.GameService;
import com.halak.service.rules.ChainFactory;
import com.halak.service.rules.GameContext;
import com.halak.service.rules.KalahGameChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer which orchestrates components interaction and is used to exclude possible business logic from controller layer
 */
@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private GameStateRepository gameStateRepository;

    @Autowired
    private GameStateFactory gameStateFactory;

    @Autowired
    private ChainFactory chainFactory;

    @Override
    public GameState create() {
        GameState gameState = gameStateFactory.defaultGameState();

        return gameStateRepository.save(gameState);
    }

    @Override
    public GameState play(Long gameId, int pitIndex) throws Exception {
        GameState currentGame = gameStateRepository.findGameById(gameId).orElseThrow(() -> new GameDoesntExistException("Game with provided id: [%s] doesn't exist.", gameId));

        GameContext gameContext = gameMapper.toContext(currentGame, pitIndex);

        KalahGameChain kalahGameChain = chainFactory.kalahGameChain();
        kalahGameChain.execute(gameContext);

        currentGame = gameMapper.toEntity(gameContext);

        return gameStateRepository.save(currentGame);
    }

    @Override
    public Optional<GameState> getInfo(Long gameId) {
        return gameStateRepository.findGameById(gameId);
    }
}
