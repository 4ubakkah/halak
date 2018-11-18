package com.halak.service.rules.Commands;

import com.halak.model.entity.GameState;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameStartCommandTest {

    @Test
    @DisplayName("[Should] switch state to started true [Given] started false")
    void execute() {
        GameStartCommand gameStartCommand = new GameStartCommand();

        GameContext gameContext = GameContext.builder().gameState(GameState.builder().started(false).build())
                .build();

        boolean execute = gameStartCommand.execute(gameContext);

        assertThat(execute).isFalse();
        assertThat(gameContext.isStarted()).isTrue();
    }
}