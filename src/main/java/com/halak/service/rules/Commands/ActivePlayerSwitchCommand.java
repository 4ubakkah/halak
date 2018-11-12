package com.halak.service.rules.Commands;

import com.halak.model.player.Player;
import com.halak.service.rules.GameUtilities;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;

public class ActivePlayerSwitchCommand implements Command {

    @Override
    public boolean execute(Context context) {
//        Game game = (Game) context.get("game");
        Player activePlayer = (Player) context.get("activePlayer");
        int lastSownPitId = (int) context.get("lastSownPitId");
        List<Player> players = (List<Player>) context.get("players");

        if (activePlayer.getKalahIndex() != lastSownPitId) {
            context.put("activePlayer", GameUtilities.findOpponent(players, activePlayer));
        }

        return true;
    }
}
