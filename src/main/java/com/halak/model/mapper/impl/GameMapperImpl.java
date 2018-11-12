package com.halak.model.mapper.impl;

import com.halak.model.dto.GameDto;
import com.halak.model.game.Game;
import com.halak.model.mapper.GameMapper;
import com.halak.model.mapper.PitMapper;
import com.halak.service.rules.GameContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameMapperImpl implements GameMapper {

    @Autowired
    private PitMapper pitMapper;

    @Override
    public GameDto toDto(Game game) {
        return GameDto.builder().gameId(game.getGameId())
                .pits(pitMapper.toDtoList(game.getBoard().getPits()))
                .activePlayer(game.getActivePlayer().getName())
                .build();
    }

    @Override
    public GameContext toContext(Game game, int selectedPitId) {
        return GameContext.builder().activePlayer(game.getActivePlayer())
                .complete(game.isComplete())
                .started(game.isStarted())
                .lastSownPitId(game.getLastSownPitId())
                .gameBoard(game.getBoard())
                .players(game.getPlayers())
                .selectedPitId(selectedPitId)
                .build();
    }

    @Override
    public Game toEntity(GameContext gameContext, String gameId) {
        return Game.builder().activePlayer(gameContext.getActivePlayer())
                .gameId(gameId)
                .complete(gameContext.isComplete())
                .started(gameContext.isStarted())
                .lastSownPitId(gameContext.getLastSownPitId())
                .board(gameContext.getGameBoard())
                .players(gameContext.getPlayers())
                .build();
    }
}
