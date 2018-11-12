package com.halak.service.rules.Commands;

import com.halak.model.game.GameBoard;
import com.halak.model.game.GameSpecifications;
import com.halak.model.game.pit.Pit;
import com.halak.model.player.Player;
import com.halak.service.rules.GameUtilities;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;

@Slf4j
public class GameCompletionCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameBoard gameBoard = (GameBoard) context.get("gameBoard");
        Player activePlayer = (Player) context.get("activePlayer");
        List<Player> players = (List<Player>) context.get("players");

        Player opponent = GameUtilities.findOpponent(players, activePlayer);

        return hasPlayerCompleteTheGame(gameBoard, activePlayer, opponent) || hasPlayerCompleteTheGame(gameBoard, opponent, activePlayer);
    }

    private List<Pit> getPlayerPits(GameBoard gameBoard, Player player) {
        return gameBoard.getPitsInRange(player.getKalahIndex() - GameSpecifications.PITS_COUNT_PER_PLAYER, player.getKalahIndex());
    }

    private boolean doPlayersPitsContainStones(GameBoard gameBoard, Player player) {
        return getPlayerPits(gameBoard, player).stream().mapToInt(pit -> pit.getStones()).sum() == 0;
    }

    private boolean hasPlayerCompleteTheGame(GameBoard gameBoard, Player player, Player opponent) {
        if (doPlayersPitsContainStones(gameBoard, player)) {
            log.info("Player {} finished game.", player);
            moveStonesFromHouseToKalah(gameBoard, opponent);
            return true;
        }

        return false;
    }

    //TODO description
    private GameBoard moveStonesFromHouseToKalah(GameBoard gameBoard, Player player) {
//        List<Pit> pits = gameBoard.getPits().subList(player.getKalahIndex() - GameSpecifications.PITS_COUNT_PER_PLAYER, player.getKalahIndex() + 1);
        List<Pit> playerPits = getPlayerPits(gameBoard, player);
        int sumOfStonesCollectedFromPits = playerPits.stream().mapToInt(Pit::extractStones).sum();

        playerPits.get(playerPits.size() - 1).addStones(sumOfStonesCollectedFromPits);

        return gameBoard;
    }
}
