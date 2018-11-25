package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.GameState;
import com.halak.model.entity.factory.GameStateFactory;
import com.halak.model.entity.PitEntity;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.halak.configuration.GameSpecifications.DRAW_GAME_TEXT;
import static com.halak.service.rules.Commands.Utilities.composeEmptyPits;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *   [*] - index of pit
 *   (*) - amount of stones in pit
 **/
class CalculateAndDecideWinnerTest {

    @Test
    @DisplayName("[Should] proceed without breaking chain [Given] game is not flagged as complete")
    void shouldProceed() {
        CalculateAndDecideWinner calculateAndDecideWinner = new CalculateAndDecideWinner();

        GameContext gameContext = GameContext.builder()
                .gameState(GameState.builder().complete(false).build())
                .build();

        boolean execute = calculateAndDecideWinner.execute(gameContext);

        assertThat(execute).isFalse();
    }

    /**
     *          (0)  (0)  (0)  (0) (0) (0)
     *          [12] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (40) [0]  [1]  [2]  [3] [4] [5] (32)
     *          (0)  (0)  (0)  (0) (0) (0)
     *
     **/
    @Test
    @DisplayName("[Should] break chain [Given] game is flagged as complete/ North player should win")
    void shouldNotProceed_NorthPlayerWins() {
        CalculateAndDecideWinner calculateAndDecideWinner = new CalculateAndDecideWinner();
        List<PitEntity> pits = composeEmptyPits();

        pits.set(6, PitEntity.builder().index(6).stonesCount(32).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(40).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pits(pits).build());

        GameContext gameContext = GameContext.builder()
                .gameState(gameState)
                .build();

        boolean execute = calculateAndDecideWinner.execute(gameContext);

        assertThat(execute).isTrue();
        assertThat(gameContext.getWinnerName()).isEqualTo(gameContext.getPlayers().get(1).getName());
    }

    /**
     *          (0)  (0)  (0)  (0) (0) (0)
     *          [12] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (36) [0]  [1]  [2]  [3] [4] [5] (36)
     *          (0)  (0)  (0)  (0) (0) (0)
     *
     **/
    @Test
    @DisplayName("[Should] break chain [Given] game is flagged as complete/ Draw game")
    void shouldNotProceed_DrawGame() {
        CalculateAndDecideWinner calculateAndDecideWinner = new CalculateAndDecideWinner();
        List<PitEntity> pits = composeEmptyPits();

        pits.set(6, PitEntity.builder().index(6).stonesCount(36).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(36).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pits(pits).build());

        GameContext gameContext = GameContext.builder()
                .gameState(gameState)
                .build();

        boolean execute = calculateAndDecideWinner.execute(gameContext);

        assertThat(execute).isTrue();
        assertThat(gameContext.getWinnerName()).isEqualTo(DRAW_GAME_TEXT);
    }
}