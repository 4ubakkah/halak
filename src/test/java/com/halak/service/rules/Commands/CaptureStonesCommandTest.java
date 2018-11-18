package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.GameState;
import com.halak.model.entity.GameStateFactory;
import com.halak.model.entity.PitEntity;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.halak.service.rules.Commands.Utilities.composeEmptyPits;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *   [*] - index of pit
 *   (*) - amount of stones in pit
 *    x  - last sown pit id
 **/
class CaptureStonesCommandTest {

    /**
     *          (4)  (0)  (0)  (0) (0) (0)
     *          [12] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (32) [0]  [1]  [2]  [3] [4] [5] (35)
     *          (1)  (0)  (0)  (0) (0) (0)
     *           x
     **/
    @Test
    @DisplayName("[Should] proceed and capture opponents stones [Given] last sown pit is active player's own pit containing zero stones before sowing")
    void shouldProceed_andCaptureOpponentsStones() {
        CaptureStonesCommand command = new CaptureStonesCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(0, PitEntity.builder().index(0).stonesCount(1).build());
        pits.set(6, PitEntity.builder().index(6).stonesCount(35).build());
        pits.set(12, PitEntity.builder().index(12).stonesCount(4).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(32).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pitEntities(pits).build());

        GameContext gameContext = GameContext.builder().gameState(gameState).lastSownPitIndex(0).build();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isFalse();
        assertThat(pits.get(0).getStonesCount()).isZero();
        assertThat(pits.get(12).getStonesCount()).isZero();
        assertThat(pits.get(6).getStonesCount()).isEqualTo(40);
        assertThat(pits.get(13).getStonesCount()).isEqualTo(32);
    }

    /**
     *          (4)  (0)  (0)  (0) (0) (0)
     *          [12] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (32) [0]  [1]  [2]  [3] [4] [5] (35)
     *          (1)  (0)  (0)  (0) (0) (0)  x
     *
     **/
    @Test
    @DisplayName("[Should] proceed and don't capture opponents stones [Given] last sown pit is active player's own Kalah")
    void shouldProceed_andDontCaptureOpponentsStones() {
        CaptureStonesCommand command = new CaptureStonesCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(0, PitEntity.builder().index(0).stonesCount(1).build());
        pits.set(6, PitEntity.builder().index(6).stonesCount(35).build());
        pits.set(12, PitEntity.builder().index(12).stonesCount(4).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(32).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pitEntities(pits).build());

        GameContext gameContext = GameContext.builder().gameState(gameState).lastSownPitIndex(6).build();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isFalse();
        assertThat(pits.get(0).getStonesCount()).isEqualTo(1);
        assertThat(pits.get(6).getStonesCount()).isEqualTo(35);
        assertThat(pits.get(12).getStonesCount()).isEqualTo(4);
        assertThat(pits.get(13).getStonesCount()).isEqualTo(32);
    }

    /**
     *          (0)  (0)  (0)  (0) (0) (0)
     *          [12] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (36) [0]  [1]  [2]  [3] [4] [5] (35)
     *          (1)  (0)  (0)  (0) (0) (0)
     *           x
     **/
    @Test
    @DisplayName("[Should] proceed and don't capture opponents stones [Given] last sown pit is active player's own Kalah")
    void shouldProceed_andDontCaptureOpponentsStones_givenOppositePitIsEmpty() {
        CaptureStonesCommand command = new CaptureStonesCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(0, PitEntity.builder().index(0).stonesCount(1).build());
        pits.set(6, PitEntity.builder().index(6).stonesCount(35).build());
        pits.set(12, PitEntity.builder().index(12).stonesCount(0).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(36).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pitEntities(pits).build());

        GameContext gameContext = GameContext.builder().gameState(gameState).lastSownPitIndex(6).build();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isFalse();
        assertThat(pits.get(0).getStonesCount()).isEqualTo(1);
        assertThat(pits.get(6).getStonesCount()).isEqualTo(35);
        assertThat(pits.get(12).getStonesCount()).isEqualTo(0);
        assertThat(pits.get(13).getStonesCount()).isEqualTo(36);
    }

    /**
     *          (2)  (0)  (0)  (0) (0) (0)
     *          [12] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (34) [0]  [1]  [2]  [3] [4] [5] (34)
     *          (2)  (0)  (0)  (0) (0) (0)
     *           x
     **/
    @Test
    @DisplayName("[Should] proceed and don't capture opponents stones [Given] last sown pit is active player's own pit containing zero stones before sowing")
    void shouldProceed_andCaptureOpponentsStones_givenLasSownPitHasMoreThanOneStone() {
        CaptureStonesCommand command = new CaptureStonesCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(0, PitEntity.builder().index(0).stonesCount(2).build());
        pits.set(6, PitEntity.builder().index(6).stonesCount(34).build());
        pits.set(12, PitEntity.builder().index(12).stonesCount(2).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(34).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pitEntities(pits).build());

        GameContext gameContext = GameContext.builder().gameState(gameState).lastSownPitIndex(6).build();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isFalse();
        assertThat(pits.get(0).getStonesCount()).isEqualTo(2);
        assertThat(pits.get(6).getStonesCount()).isEqualTo(34);
        assertThat(pits.get(12).getStonesCount()).isEqualTo(2);
        assertThat(pits.get(13).getStonesCount()).isEqualTo(34);
    }
}