package com.halak.model.mapper.impl;

import com.halak.model.dto.GameDto;
import com.halak.model.entity.GameState;
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
    public GameDto toDto(GameState gameState) {
        return GameDto.builder().gameId(gameState.getId())
                .pits(pitMapper.toDtoList(gameState.getGameBoard().getPitEntities()))
                .activePlayer(gameState.getActivePlayerEntity().getName())
                .build();
    }

    @Override
    public GameContext toContext(GameState gameState, int selectedPitId) {
        return GameContext.builder().gameState(gameState)
                .selectedPitIndex(selectedPitId)
                .build();
    }

    @Override
    public GameState toEntity(GameContext gameContext) {
        return gameContext.getGameState();
    }
}
