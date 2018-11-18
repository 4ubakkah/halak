package com.halak.service.rules.Commands;

import com.halak.model.entity.PlayerEntity;
import com.halak.service.rules.GameContext;
import com.halak.service.rules.GameUtilities;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;

public class ActivePlayerSwitchCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);

        PlayerEntity activePlayer = gameContext.getGameState().getActivePlayerEntity();
        int lastSownPitId = gameContext.getLastSownPitIndex();
        List<PlayerEntity> players = gameContext.getGameState().getPlayerEntities();

        if (activePlayer.getKalahIndex() != lastSownPitId) {
            gameContext.getGameState().setActivePlayerEntity(GameUtilities.findOpponent(players, activePlayer));
        }

        return true;
    }
}
