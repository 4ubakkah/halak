package com.halak.service.rules;

import com.halak.model.game.GameBoard;
import com.halak.model.player.Player;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.chain.impl.ContextBase;

import java.util.List;

@Builder
@Data
public class GameContext extends ContextBase {

    private List<Player> players;

    private Player activePlayer;

    private int selectedPitId;

    private GameBoard gameBoard;

    private boolean complete;

    private boolean started;

    private int lastSownPitId;
}
