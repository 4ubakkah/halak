package com.halak.service.impl;

import com.halak.model.exception.GameDoesntExistException;
import com.halak.model.game.Game;
import com.halak.model.mapper.GameMapper;
import com.halak.persistance.GameRepository;
import com.halak.service.GameService;
import com.halak.service.rules.GameContext;
import com.halak.service.rules.KalahGameChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMapper gameMapper;

    @Override
    public Game create() {
        Game game = gameRepository.createGame();

        log.info("Created game: {}", game);

        return game;
    }

    @Override
    public Game play(String gameId, int pitId) throws Exception {
        Game currentGame = gameRepository.getGameById(gameId);

        GameContext gameContext = gameMapper.toContext(currentGame, pitId);
        new KalahGameChain().execute(gameContext);
        currentGame = gameMapper.toEntity(gameContext, gameId);

        return gameRepository.saveGame(currentGame);
    }

    @Override
    public Optional<Game> getInfo(String gameId) {
        return gameRepository.findGameById(gameId);
    }
}
