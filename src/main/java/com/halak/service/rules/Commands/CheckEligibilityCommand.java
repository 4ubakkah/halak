package com.halak.service.rules.Commands;

import com.halak.model.exception.NonEligibleMoveException;
import com.halak.model.game.GameBoard;
import com.halak.model.game.GameSpecifications;
import com.halak.model.player.Player;
import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class CheckEligibilityCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);
        int selectedPitId = gameContext.getSelectedPitId();
        Player activePlayer = gameContext.getActivePlayer();
        GameBoard gameBoard = gameContext.getGameBoard();

        checkIfPlayerIsEligibleToMakeMove(activePlayer, selectedPitId, gameBoard.isKalah(selectedPitId));

        return false;
    }

    private boolean pitBelongsToPlayer(Player player, int selectedPitId) {
        return selectedPitId < player.getKalahIndex() && selectedPitId >= player.getKalahIndex() - GameSpecifications.PITS_COUNT_PER_PLAYER;
    }

    private void checkIfPlayerIsEligibleToMakeMove(Player player, int pitId, boolean isKalah) {
        if (isKalah) {
            throw new NonEligibleMoveException("Selected pitId: [%s] is Kalah. No interaction with Kalah till end of game is allowed.", pitId);
        }
        if (!pitBelongsToPlayer(player, pitId)) {
            throw new NonEligibleMoveException("Provided pit id: [%s] doesn't belong to active player: [%s]", pitId, player.getName());
        }
    }
}
