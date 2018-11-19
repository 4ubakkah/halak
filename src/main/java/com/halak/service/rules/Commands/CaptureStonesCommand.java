package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.PlayerEntity;
import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import static com.halak.configuration.GameSpecifications.PITS_COUNT_PER_PLAYER;
import static com.halak.configuration.GameSpecifications.PLAYERS_COUNT;
import static com.halak.service.rules.GameUtilities.regularPitBelongsToPlayer;

/**
 * Decides if stones from opponents pit have to be captured.
 * In case the last sown stone is put into empty pit belonging to player(not Kalah) and opposite pit belonging to opponent contains stones,
 * current stone and stones from opposite pit are captured and put into active players Kalah.
 */
public class CaptureStonesCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);
        PlayerEntity activePlayer = gameContext.getActivePlayer();
        int lastSownPitIndex = gameContext.getLastSownPitIndex();
        GameBoardEntity gameBoard = gameContext.getGameState().getGameBoard();

        // 1.) Last sown pit is not Kalah
        // 2.) Last sown pit belongs to active player
        if (!gameBoard.getPit(lastSownPitIndex).isKalah() && regularPitBelongsToPlayer(activePlayer, lastSownPitIndex)) {
            // If extracting 1 stone from last sown pit produce 0 - this means before sowing pit was empty
            if (gameBoard.getPit(lastSownPitIndex).getStonesCount() - 1 == 0) {
                // Taking difference of last sown pit index and 12 (amount of sownable pits) and making absolute value out of it would produce opposite pit index
                int oppositePitId = Math.abs(lastSownPitIndex - PITS_COUNT_PER_PLAYER * PLAYERS_COUNT);

                // Skip capturing if opposite pit is empty
                if (gameBoard.getPit(oppositePitId).getStonesCount() > 0) {
                    int accumulatedStones = gameBoard.getPit(oppositePitId).extractStones() + gameBoard.getPit(lastSownPitIndex).extractStones();
                    gameBoard.getPit(activePlayer.getKalahIndex()).addStones(accumulatedStones);
                }
            }
        }

        return false;
    }
}
