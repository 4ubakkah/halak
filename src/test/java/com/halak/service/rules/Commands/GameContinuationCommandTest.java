package com.halak.service.rules.Commands;

import com.halak.model.entity.GameState;
import com.halak.model.exception.GameIsAlreadyCompleteException;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameContinuationCommandTest {

    @Test
    @DisplayName("[Should] throw exception [Given] game is flagged as complete")
    void shouldThrowException() {
        GameContinuationCommand gameContinuationCommand = new GameContinuationCommand();

        GameContext gameContext = GameContext.builder().gameState(GameState.builder().complete(true).build())
                .build();

        assertThatThrownBy(() -> gameContinuationCommand.execute(gameContext)).isInstanceOf(GameIsAlreadyCompleteException.class)
                .hasMessageContaining("Game is complete already");
    }

    @Test
    @DisplayName("[Should] proceed without breaking chain [Given] game is not flagged as complete")
    void shouldProceed() {
        GameContinuationCommand gameContinuationCommand = new GameContinuationCommand();

        GameContext gameContext = GameContext.builder().gameState(GameState.builder().complete(false).build())
                .build();

        boolean execute = gameContinuationCommand.execute(gameContext);

        assertThat(execute).isFalse();
    }
}