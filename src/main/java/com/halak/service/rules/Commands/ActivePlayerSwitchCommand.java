package com.halak.service.rules.Commands;

import com.halak.model.player.Player;
import com.halak.service.rules.GameContext;
import com.halak.service.rules.GameUtilities;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;

public class ActivePlayerSwitchCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);

        Player activePlayer = gameContext.getActivePlayer();
        int lastSownPitId = gameContext.getLastSownPitId();
        List<Player> players = gameContext.getPlayers();

        if (activePlayer.getKalahIndex() != lastSownPitId) {
            gameContext.setActivePlayer(GameUtilities.findOpponent(players, activePlayer));
        }

        return true;
    }
}
