package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.PitEntity;
import com.halak.model.entity.PlayerEntity;
import com.halak.configuration.GameSpecifications;
import com.halak.service.rules.GameContext;
import com.halak.service.rules.GameUtilities;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;

@Slf4j
public class GameCompletionCommand extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);

        GameBoardEntity gameBoard = gameContext.getGameState().getGameBoard();
        PlayerEntity activePlayer = gameContext.getActivePlayer();
        List<PlayerEntity> players = gameContext.getPlayers();

        PlayerEntity opponent = GameUtilities.findOpponent(players, activePlayer);

        return hasPlayerCompleteTheGame(gameBoard, activePlayer, opponent) || hasPlayerCompleteTheGame(gameBoard, opponent, activePlayer);
    }

    private List<PitEntity> getPlayerPits(GameBoardEntity gameBoard, PlayerEntity player) {
        return gameBoard.getPitsInRange(player.getKalahIndex() - GameSpecifications.PITS_COUNT_PER_PLAYER, player.getKalahIndex());
    }

    private boolean doPlayersPitsContainStones(GameBoardEntity gameBoard, PlayerEntity player) {
        return getPlayerPits(gameBoard, player).stream().mapToInt(PitEntity::getStonesCount).sum() == 0;
    }

    private boolean hasPlayerCompleteTheGame(GameBoardEntity gameBoard, PlayerEntity player, PlayerEntity opponent) {
        if (doPlayersPitsContainStones(gameBoard, player)) {
            log.info("PlayerEntity {} finished game.", player);
            moveStonesFromOpponentsPitsToKalah(gameBoard, player, opponent);
            return true;
        }

        return false;
    }

    //TODO description
    private void moveStonesFromOpponentsPitsToKalah(GameBoardEntity gameBoard, PlayerEntity player, PlayerEntity opponent) {
        List<PitEntity> playerPits = getPlayerPits(gameBoard, opponent);
        int sumOfStonesCollectedFromPits = playerPits.stream().mapToInt(PitEntity::extractStones).sum();

        gameBoard.getPit(player.getKalahIndex()).addStones(sumOfStonesCollectedFromPits);
    }
}
