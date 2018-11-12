package com.halak.model.mapper;

import com.halak.model.dto.GameDto;
import com.halak.model.game.Game;
import com.halak.service.rules.GameContext;

public interface GameMapper {

    GameDto toDto(Game game);

    GameContext toContext(Game game, int selectedPitId);

    Game toEntity(GameContext gameContext, String gameId);
}
