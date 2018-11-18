package com.halak.model.mapper;

import com.halak.model.dto.GameDto;
import com.halak.model.entity.GameState;
import com.halak.service.rules.GameContext;

public interface GameMapper {

    GameDto toDto(GameState gameState);

    GameContext toContext(GameState gameState, int selectedPitId);

    GameState toEntity(GameContext gameContext);
}
