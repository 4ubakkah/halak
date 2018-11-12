package com.halak.service.rules.Commands;

import com.halak.model.game.GameBoard;
import com.halak.model.game.pit.Pit;
import com.halak.model.player.Player;
import com.halak.service.rules.GameContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
public class CalculateAndDecideWinner extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);
        boolean gameComplete = gameContext.isComplete();
        List<Player> players = gameContext.getPlayers();
        GameBoard gameBoard =  gameContext.getGameBoard();

        if (gameComplete) {
            calculateAndFindWinner(gameBoard, players);
        }

        gameContext.setComplete(gameComplete);

        return gameComplete;
    }

    private void calculateAndFindWinner(GameBoard gameBoard, List<Player> players) {
        TreeMap<String, Pit> playerResultsMap = players.stream().collect(Collectors.toMap(p -> p.getName(), p -> gameBoard.getPit(p.getKalahIndex()), (a, b) -> a, TreeMap::new));

        String winnerName = playerResultsMap.pollLastEntry().getKey();
        log.info("Player {} ", winnerName);
    }
}
