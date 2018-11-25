package com.halak.service.rules;

import com.halak.model.entity.GameState;
import com.halak.model.entity.PitEntity;
import com.halak.model.entity.PlayerEntity;
import lombok.*;
import org.apache.commons.chain.impl.ContextBase;

import java.util.List;

/**
 * Data structure to that wraps gameState and ads meta information: currently selected pit index, las sown pit index, winner name.
 * This structure is passed through each command in game chain, where changes of state are applied and respective meta data is updated.
 */
@Builder
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class GameContext extends ContextBase {

    private GameState gameState;

    private int selectedPitIndex = -1;

    private int lastSownPitIndex = -1;

    private String winnerName;

    public PlayerEntity getActivePlayer() {
        return gameState.getActivePlayerEntity();
    }

    public PitEntity getSelectedPit() {
        return gameState.getGameBoard().getPit(selectedPitIndex);
    }

    public List<PlayerEntity> getPlayers() {
        return gameState.getPlayerEntities();
    }

    public boolean isComplete() {
        return gameState.getComplete();
    }

    public boolean isStarted() {
        return gameState.getStarted();
    }

    public void setStarted(boolean started) {
        gameState.setStarted(started);
    }

}
