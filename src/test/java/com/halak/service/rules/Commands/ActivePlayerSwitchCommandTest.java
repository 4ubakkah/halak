package com.halak.service.rules.Commands;

import com.halak.model.entity.GameState;
import com.halak.model.entity.GameStateFactory;
import com.halak.model.entity.PlayerEntity;
import com.halak.service.rules.GameContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ActivePlayerSwitchCommandTest {

    @ParameterizedTest(name = "Run #{index} with pit index:[{arguments}]")
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13})
    @DisplayName("[Should] switch player if last sown pitIndex is not player's own Kalah")
    public void execute(int pitIndex) {
        ActivePlayerSwitchCommand command = new ActivePlayerSwitchCommand();
        GameState gameState = new GameStateFactory().defaultGameState();
        GameContext gameContext = GameContext.builder().gameState(gameState).lastSownPitIndex(pitIndex).build();
        PlayerEntity initialPlayer = gameContext.getActivePlayer();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isTrue();
        assertThat(gameContext.getActivePlayer()).isNotEqualTo(initialPlayer);
    }

    @Test
    @DisplayName("[Should not] switch player if last sown pitIndex is player's own Kalah")
    public void execute() {
        ActivePlayerSwitchCommand command = new ActivePlayerSwitchCommand();
        GameState gameState = new GameStateFactory().defaultGameState();
        GameContext gameContext = GameContext.builder().gameState(gameState).lastSownPitIndex(gameState.getActivePlayerEntity().getKalahIndex()).build();
        PlayerEntity initialPlayer = gameContext.getActivePlayer();

        boolean execute = command.execute(gameContext);

        assertThat(execute).isTrue();
        assertThat(gameContext.getActivePlayer()).isEqualTo(initialPlayer);
    }
}