package com.halak.service.rules.Commands;

import com.halak.model.game.GameBoard;
import com.halak.model.game.pit.Pit;
import com.halak.model.player.Player;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
public class CalculateAndDecideWinner implements Command {

    @Override
    public boolean execute(Context context) {
        boolean gameComplete = (boolean) context.get("complete");
        List<Player> players = (List<Player>) context.get("players");
        GameBoard gameBoard = (GameBoard) context.get("gameBoard");

        if (gameComplete) {
            calculateAndFindWinner(gameBoard, players);
            context.put("complete", true);

            return true;
        }

        return false;
    }

    private void calculateAndFindWinner(GameBoard gameBoard, List<Player> players) {
        TreeMap<String, Pit> playerResultsMap = players.stream().collect(Collectors.toMap(p -> p.getName(), p -> gameBoard.getPit(p.getKalahIndex()), (a, b) -> a, TreeMap::new));

        String winnerName = playerResultsMap.pollLastEntry().getKey();
        log.info("Player {} ", winnerName);
    }
}
