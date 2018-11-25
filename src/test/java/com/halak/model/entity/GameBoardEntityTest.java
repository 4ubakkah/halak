package com.halak.model.entity;

import com.halak.configuration.GameSpecifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameBoardEntityTest {

    @Test
    @DisplayName("[Should] initialize game board with proper pits content")
    void testInitialGameBoardState() {
        GameBoardEntity gameBoardEntity = new GameBoardEntity();

        gameBoardEntity.getPits().forEach(pit -> {
            if (pit.isKalah()) {
                // Initially all Kalah pits have 0 stones
                assertThat(pit.getStonesCount()).isEqualTo(GameSpecifications.INITIAL_STONES_COUNT_PER_KALAH);
            } else {
                // Initially all regular pits have 6 stones
                assertThat(pit.getStonesCount()).isEqualTo(GameSpecifications.INITIAL_STONES_COUNT_PER_PIT);
            }
        });
    }
}