package com.halak.service.rules;

import com.halak.service.rules.Commands.*;
import org.apache.commons.chain.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChainFactoryTest {

    private final ChainFactory chainFactory = new ChainFactory();

    @Test
    @DisplayName("Factory should produce Kalah Game Chain with right correct order of commands")
    public void kalahGameChain() {
        KalahGameChain kalahGameChain = chainFactory.kalahGameChain();

        Command[] commands = kalahGameChain.getCommands();
        assertThat(commands).hasSize(9);
        assertThat(commands[0]).isInstanceOf(ValidateInputCommand.class);
        assertThat(commands[1]).isInstanceOf(GameStartCommand.class);
        assertThat(commands[2]).isInstanceOf(GameContinuationCommand.class);
        assertThat(commands[3]).isInstanceOf(CheckEligibilityCommand.class);
        assertThat(commands[4]).isInstanceOf(SowStonesCommand.class);
        assertThat(commands[5]).isInstanceOf(CaptureStonesCommand.class);
        assertThat(commands[6]).isInstanceOf(GameCompletionCommand.class);
        assertThat(commands[7]).isInstanceOf(CalculateAndDecideWinner.class);
        assertThat(commands[8]).isInstanceOf(ActivePlayerSwitchCommand.class);
    }
}