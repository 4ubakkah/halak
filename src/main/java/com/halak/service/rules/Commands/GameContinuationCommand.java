package com.halak.service.rules.Commands;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class GameContinuationCommand implements Command {

    @Override
    public boolean execute(Context context) {
        boolean gameComplete = (boolean) context.get("complete");

        if(gameComplete) {
            throw new RuntimeException("Game is complete already, create new game to be able to play again.");
        }

        return false;
    }
}
