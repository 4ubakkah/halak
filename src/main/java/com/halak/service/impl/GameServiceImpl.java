package com.halak.service.impl;

import com.halak.model.entity.GameState;
import com.halak.model.entity.factory.GameStateFactory;
import com.halak.model.exception.GameDoesNotExistException;
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
        GameState currentGame = gameStateRepository.findGameById(gameId).orElseThrow(() -> new GameDoesNotExistException("Game with provided id: [%s] doesn't exist.", gameId));

        // Maps GameState retrieved from persistent store to GameContext structure
        GameContext gameContext = gameMapper.toContext(currentGame, pitIndex);

        // Creates instance of Game Chain
        KalahGameChain kalahGameChain = chainFactory.kalahGameChain();

        //Starts chain execution with current state of game context
        kalahGameChain.execute(gameContext);

        currentGame = gameMapper.toEntity(gameContext);

        return gameStateRepository.save(currentGame);
    }

    @Override
    //TODO add authority checks
    //@PreAuthorize("hasAuthority('GAME_INFO_READ')")
    public Optional<GameState> getInfo(Long gameId) {
        return gameStateRepository.findGameById(gameId);
    }
}
