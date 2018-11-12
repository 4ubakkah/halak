package com.halak.persistance;

import com.halak.model.exception.GameDoesntExistException;
import com.halak.model.game.Game;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameRepository {

    //TODO don't create instance here
    private Game game/* = new Game()*/;

    public Game createGame() {
        game = new Game();
        return game;
    }

    public Game getGameById(String gameId) throws GameDoesntExistException {
        return game;
    }
    public Optional<Game> findGameById(String gameId) {
        return Optional.of(game);
    }

    public Game saveGame(Game game) {
        this.game = game;
        return game;
    }
}
