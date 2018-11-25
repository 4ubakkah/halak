package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.GameState;
import com.halak.model.entity.factory.GameStateFactory;
import com.halak.model.entity.PitEntity;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.halak.service.rules.Commands.Utilities.composeEmptyPits;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *   [*] - index of pit
 *   (*) - amount of stones in pit
 **/
class SowStonesCommandTest {

    /**
     *  Before sowing:
     *          (0)  (0)  (0)  (0) (0) (0)
     *          [0] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (38) [0]  [1]  [2]  [3] [4] [5] (28)
     *          (2)  (0)  (0)  (0) (0) (4)
     *
     *  After sowing:
     *          (0)  (0)  (0)  (0) (0) (0)
     *          [0] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (38) [0]  [1]  [2]  [3] [4] [5] (28)
     *          (1)  (1)  (0)  (0) (0) (4)
     *
     *
     **/
    @Test
    void execute() {
        SowStonesCommand command = new SowStonesCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(0, PitEntity.builder().index(0).stonesCount(2).build());
        pits.set(5, PitEntity.builder().index(5).stonesCount(4).build());
        pits.set(6, PitEntity.builder().index(6).stonesCount(28).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(38).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pits(pits).build());
        GameContext gameContext = GameContext.builder().gameState(gameState).lastSownPitIndex(0).build();


        boolean execute = command.execute(gameContext);

        assertThat(execute).isFalse();
        assertThat(pits.get(0).getStonesCount()).isZero();
        assertThat(pits.get(1).getStonesCount()).isOne();
        assertThat(pits.get(2).getStonesCount()).isOne();
        assertThat(pits.get(3).getStonesCount()).isZero();
    }

    /**
     *  Before sowing:
     *          (0)  (0)  (0)  (0) (0) (0)
     *          [0] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (32) [0]  [1]  [2]  [3] [4] [5] (28)
     *          (2)  (0)  (0)  (0) (0) (4)
     *
     *  After sowing:
     *          (0)  (0)  (0)  (0) (0) (0)
     *          [0] [11] [10] [9] [8] [7]
     *     [13]  |    |    |    |   |   |  [6]
     *     (32) [0]  [1]  [2]  [3] [4] [5] (28)
     *          (2)  (0)  (0)  (0) (0) (4)
     *
     *
     **/
    @Test
    void execute2() {
        SowStonesCommand command = new SowStonesCommand();

        List<PitEntity> pits = composeEmptyPits();

        pits.set(0, PitEntity.builder().index(0).stonesCount(7).build());
        pits.set(6, PitEntity.builder().index(6).stonesCount(29).build());
        pits.set(13, PitEntity.builder().index(13).stonesCount(36).build());

        GameState gameState = new GameStateFactory().defaultGameState();
        gameState.setComplete(true);
        gameState.setGameBoard(GameBoardEntity.builder().pits(pits).build());
        GameContext gameContext = GameContext.builder().gameState(gameState).lastSownPitIndex(0).build();


        boolean execute = command.execute(gameContext);

        assertThat(execute).isFalse();
        assertThat(pits.get(0).getStonesCount()).isZero();
        assertThat(pits.get(1).getStonesCount()).isOne();
        assertThat(pits.get(2).getStonesCount()).isOne();
        assertThat(pits.get(3).getStonesCount()).isOne();
        assertThat(pits.get(4).getStonesCount()).isOne();
        assertThat(pits.get(5).getStonesCount()).isOne();
        assertThat(pits.get(6).getStonesCount()).isEqualTo(30);
        assertThat(pits.get(7).getStonesCount()).isOne();
        assertThat(pits.get(8).getStonesCount()).isZero();
        assertThat(pits.get(13).getStonesCount()).isEqualTo(36);
    }
}