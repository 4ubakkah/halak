package com.halak.service.rules.Commands;

import com.halak.model.entity.GameBoardEntity;
import com.halak.model.entity.PitEntity;
import com.halak.model.entity.PlayerEntity;
import com.halak.service.rules.GameContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.halak.configuration.GameSpecifications.DRAW_GAME_TEXT;

@Slf4j
public class CalculateAndDecideWinner extends AbstractGameCommand implements Command {

    @Override
    public boolean execute(Context context) {
        GameContext gameContext = getGameContext(context);
        boolean gameComplete = gameContext.isComplete();
        List<PlayerEntity> players = gameContext.getPlayers();
        GameBoardEntity gameBoard = gameContext.getGameState().getGameBoard();

        if (gameComplete) {
            String winnerName = calculateAndFindWinner(gameBoard, players);
            gameContext.setWinnerName(winnerName);
        }

        //TODO remove
        //gameContext.setComplete(gameComplete);

        return gameComplete;
    }

    private String calculateAndFindWinner(GameBoardEntity gameBoard, List<PlayerEntity> players) {
        TreeMap<PitEntity, String> playerResultsMap = players.stream()
                .collect(Collectors.toMap(p -> gameBoard.getPit(p.getKalahIndex()), PlayerEntity::getName, (a, b) -> a, () -> new TreeMap<>(Comparator.comparingInt(PitEntity::getStonesCount))));

        String winnerName = playerResultsMap.size() == 1 ? DRAW_GAME_TEXT : playerResultsMap.pollLastEntry().getValue();
        log.info("Winner is: {} ", winnerName);

        return winnerName;
    }
}
