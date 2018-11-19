package com.halak.model.entity.factory;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.GameState;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GameStateFactory {

    public GameState defaultGameState() {
        GameState gameState = new GameState();
        gameState.setPlayerEntities(Arrays.asList(PlayerEntityFactory.firstPlayer(), PlayerEntityFactory.secondPlayer()));
        gameState.setActivePlayerEntity(gameState.getPlayerEntities().get(0));
        gameState.setGameBoard(new GameBoardEntity());

        return gameState;
    }
}
