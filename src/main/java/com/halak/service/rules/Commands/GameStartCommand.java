package com.halak.service.rules.Commands;

import com.halak.model.game.GameBoard;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class GameStartCommand implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        if ((boolean)context.get("started") == false) {
            context.put("gameBoard", new GameBoard());
            context.put("started", true);
        }

        return false;
    }
}
