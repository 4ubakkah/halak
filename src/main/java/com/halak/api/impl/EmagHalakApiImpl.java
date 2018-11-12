package com.halak.api.impl;

import com.halak.api.EmagHalakApi;
import com.halak.model.dto.GameDto;
import com.halak.model.game.Game;
import com.halak.model.mapper.GameMapper;
import com.halak.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

@Slf4j
@RestController
public class EmagHalakApiImpl implements EmagHalakApi {

    public static final String CREATE_GAME_URI = "/games";
    public static final String GET_GAME_URI = "/games/{gameId}";
    public static final String PLAY_GAME_URI = "/games/{gameId}/pits/{pitId}";

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @Override
    public ResponseEntity<GameDto> createGame() {
        log.info("Received request to create game");

        Game game = gameService.create();

        return ResponseEntity.created(URI.create("/TODO/" + game.getGameId())).body(gameMapper.toDto(game));
    }

    @Override
    public ResponseEntity<GameDto> playGame(@PathVariable String gameId, @PathVariable int pitId) throws Exception {
        log.info("Received request to create play game with following id:{} and following pit {}", gameId, pitId);

        Game game = gameService.play(gameId, pitId);

        return ResponseEntity.ok(gameMapper.toDto(game));
    }

    @Override
    public ResponseEntity<GameDto> getGame(@PathVariable String gameId) {
        log.info("Received request to get info for the game with following id:{}.", gameId);

        Optional<Game> game = gameService.getInfo(gameId);

        return game.isPresent() ? ResponseEntity.ok(gameMapper.toDto(game.get())) : ResponseEntity.noContent().build();
    }
}
