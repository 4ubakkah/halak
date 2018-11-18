package com.halak.service.rules.Commands;

import com.halak.model.entity.PitEntity;
import com.halak.model.entity.PlayerEntity;
import com.halak.model.exception.NonEligibleMoveException;
import com.halak.service.rules.GameContext;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import static com.halak.service.rules.GameUtilities.pitBelongsToPlayer;

public class CheckEligibilityCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);
        PlayerEntity activePlayer = gameContext.getActivePlayer();
        PitEntity pit = gameContext.getSelectedPit();

        checkIfPlayerIsEligibleToMakeMove(activePlayer, pit);

        return false;
    }

    private void checkIfPlayerIsEligibleToMakeMove(PlayerEntity player, PitEntity pitEntity) {
        if (pitEntity.isKalah()) {
            throw new NonEligibleMoveException("Selected pit index: [%s] is Kalah. No interaction with Kalah is allowed till end of game.", pitEntity.getIndex());
        }
        if (!pitBelongsToPlayer(player, pitEntity.getIndex())) {
            throw new NonEligibleMoveException("Selected pit index: [%s] doesn't belong to active player: [%s].", pitEntity.getIndex(), player.getName());
        }

        if (pitEntity.getStonesCount() == 0) {
            throw new NonEligibleMoveException("Selected pit index: [%s] is empty. You can't start sowing using empty pit!", pitEntity.getIndex());
        }
    }
}
