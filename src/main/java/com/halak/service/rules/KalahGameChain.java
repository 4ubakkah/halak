package com.halak.service.rules;

import com.halak.service.rules.Commands.*;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;

public class KalahGameChain extends ChainBase {

    public KalahGameChain() {
        super();
        addCommand(new ValidateInputCommand());
        addCommand(new GameStartCommand());
        addCommand(new GameContinuationCommand());
        addCommand(new CheckEligibilityCommand());
        addCommand(new SowStonesCommand());
        addCommand(new CaptureStonesCommand());
        addCommand(new GameCompletionCommand());
        addCommand(new CalculateAndDecideWinner());
        addCommand(new ActivePlayerSwitchCommand());
    }

    protected Command[] getCommands() {
        return this.commands;
    }
}
