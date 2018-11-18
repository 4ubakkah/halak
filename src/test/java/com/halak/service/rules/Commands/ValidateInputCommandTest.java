package com.halak.service.rules.Commands;

import com.halak.configuration.GameSpecifications;
import com.halak.model.exception.NonEligibleMoveException;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidateInputCommandTest {

    @ParameterizedTest(name = "Run #{index} with pit index:[{arguments}]")
    @ValueSource(ints = { -1, GameSpecifications.PITS_COUNT_OVERALL})
    @DisplayName("[Should] throw exception [Given] pit index is outside of legal range")
    void shouldThrowException(int pitIndex) {
        ValidateInputCommand command = new ValidateInputCommand();

        GameContext gameContext = GameContext.builder().selectedPitIndex(pitIndex).build();

        assertThatThrownBy(() -> command.execute(gameContext)).isInstanceOf(NonEligibleMoveException.class)
                .hasMessageContaining("outside of game board range");
    }

    @ParameterizedTest(name = "Run #{index} with pit index:[{arguments}]")
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 })
    @DisplayName("[Should Not] succeed without breaking execution chain [Given] pit index within legal range")
    void shouldSucceed(int pitIndex) {
        ValidateInputCommand command = new ValidateInputCommand();

        GameContext gameContext = GameContext.builder().selectedPitIndex(pitIndex).build();

        assertThat(command.execute(gameContext)).isFalse();
    }
}