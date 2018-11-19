package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.GameState;
import com.halak.model.entity.factory.GameStateFactory;
import com.halak.model.entity.PitEntity;
import com.halak.model.exception.NonEligibleMoveException;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.halak.service.rules.Commands.Utilities.composeEmptyPits;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CheckEligibilityCommandTest {

    @Test
    @DisplayName("[Should] not break chain [Given] index belongs to active player and is not Kalah")
    void execute_succeed() {
        CheckEligibilityCommand command = new CheckEligibilityCommand();

        GameContext gameContext = GameContext.builder()
                .gameState(new GameStateFactory().defaultGameState())
                //Initial player is South, so selecting pit with index 0 should be eligible move
                .selectedPitIndex(0)
                .build();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isFalse();
    }

    @Test
    @DisplayName("[Should] throw exception [Given] index belongs to active player and is not Kalah")
    void execute_throwsException_givenKalahIndex() {
        CheckEligibilityCommand command = new CheckEligibilityCommand();

        GameContext gameContext = GameContext.builder()
                .gameState(new GameStateFactory().defaultGameState())
                //Initial player is South, so selecting pit with index 6 is his own Kalah
                .selectedPitIndex(6)
                .build();

        assertThatThrownBy(() -> command.execute(gameContext)).isInstanceOf(NonEligibleMoveException.class).hasMessageContaining("No interaction with Kalah is allowed");
    }

    @Test
    @DisplayName("[Should] throw exception [Given] index belongs to active player and is not Kalah")
    void execute_throwsException_givenOpponentsPitIndex() {
        CheckEligibilityCommand command = new CheckEligibilityCommand();

        GameContext gameContext = GameContext.builder()
                .gameState(new GameStateFactory().defaultGameState())
                //Initial player is South, so selecting pit with index 7 should be opponents pit - so non eligible move
                .selectedPitIndex(7)
                .build();

        assertThatThrownBy(() -> command.execute(gameContext)).isInstanceOf(NonEligibleMoveException.class).hasMessageContaining("doesn't belong to active player");
    }

    @Test
    @DisplayName("[Should] not break chain [Given] index belongs to active player and is not Kalah")
    void execute_throwsException_givenSelectedPitIsEmpty() {
        CheckEligibilityCommand command = new CheckEligibilityCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(6, PitEntity.builder().index(6).stonesCount(0).build());
        pits.set(7, PitEntity.builder().index(7).stonesCount(12).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setGameBoard(GameBoardEntity.builder().pitEntities(pits).build());

        GameContext gameContext = GameContext.builder()
                .gameState(gameState)
                .selectedPitIndex(0)
                .build();

        assertThatThrownBy(() -> command.execute(gameContext)).isInstanceOf(NonEligibleMoveException.class).hasMessageContaining("can't start sowing using empty pit");
    }

}