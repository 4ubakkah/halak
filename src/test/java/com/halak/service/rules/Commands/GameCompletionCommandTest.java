package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.GameState;
import com.halak.model.entity.factory.GameStateFactory;
import com.halak.model.entity.PitEntity;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.halak.service.rules.Commands.Utilities.composeEmptyPits;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * [*] - index of pit
 * (*) - amount of stones in pit
 **/
class GameCompletionCommandTest {

    @Test
    @DisplayName("[Should] not break chain [Given] none of the players has complete the game")
    void shouldProceed_givenNoneHasCompleteGame() {
        GameCompletionCommand command = new GameCompletionCommand();

        //As we start with default game state that leads to the fact that nobody has complete the game
        GameContext gameContext = GameContext.builder().gameState(new GameStateFactory().defaultGameState()).build();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isFalse();
    }

    /**
     *      (1)  (1)  (0)  (0) (0) (0)
     *      [12] [11] [10] [9] [8] [7]
     * [13]  |    |    |    |   |   |  [6]
     * (40) [0]  [1]  [2]  [3] [4] [5] (32)
     *      (0)  (0)  (0)  (0) (0) (0)
     **/
    @Test
    @DisplayName("[Should] break chain [Given] active player has complete the game")
    void shouldNotProceed_givenActivePlayerHasCompleteGame() {
        GameCompletionCommand command = new GameCompletionCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(6, PitEntity.builder().index(6).stonesCount(40).build());
        pits.set(11, PitEntity.builder().index(11).stonesCount(1).build());
        pits.set(12, PitEntity.builder().index(12).stonesCount(1).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(30).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pitEntities(pits).build());

        GameContext gameContext = GameContext.builder().gameState(gameState).build();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isTrue();
        // Active player has complete game so he gets all stones from opponent's pits added to his Kalah
        assertThat(pits.get(12).getStonesCount()).isZero();
        assertThat(pits.get(13).getStonesCount()).isEqualTo(30);
        assertThat(pits.get(6).getStonesCount()).isEqualTo(42);
    }

    /**
     *      (0)  (0)  (0)  (0) (0) (0)
     *      [12] [11] [10] [9] [8] [7]
     * [13]  |    |    |    |   |   |  [6]
     * (38) [0]  [1]  [2]  [3] [4] [5] (28)
     *      (2)  (0)  (0)  (0) (0) (4)
     **/
    @Test
    @DisplayName("[Should] break chain [Given] opponent player has complete the game")
    void shouldNotProceed_givenOpponentPlayerHasCompleteGame() {
        GameCompletionCommand command = new GameCompletionCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(0, PitEntity.builder().index(0).stonesCount(2).build());
        pits.set(5, PitEntity.builder().index(0).stonesCount(4).build());
        pits.set(6, PitEntity.builder().index(6).stonesCount(28).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(38).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pitEntities(pits).build());

        GameContext gameContext = GameContext.builder().gameState(gameState).build();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isTrue();
        // Opponent has complete game so he gets all stones from active player's pits added to his Kalah
        assertThat(pits.get(0).getStonesCount()).isZero();
        assertThat(pits.get(5).getStonesCount()).isZero();
        assertThat(pits.get(6).getStonesCount()).isEqualTo(28);
        assertThat(pits.get(13).getStonesCount()).isEqualTo(44);
    }
}