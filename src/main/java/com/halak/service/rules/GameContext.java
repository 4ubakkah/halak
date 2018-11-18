package com.halak.service.rules;

import com.halak.model.entity.GameState;
import com.halak.model.entity.PitEntity;
import com.halak.model.entity.PlayerEntity;
import lombok.*;
import org.apache.commons.chain.impl.ContextBase;

import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
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
