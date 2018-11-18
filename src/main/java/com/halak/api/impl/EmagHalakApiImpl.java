package com.halak.api.impl;

import com.halak.api.EmagHalakApi;
import com.halak.model.dto.GameDto;
import com.halak.model.entity.GameState;
import com.halak.model.mapper.GameMapper;
import com.halak.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;

/**
 * Rest controller serving as entry point to trigger game creation, retrieval of game information, playing the game
 */
@Slf4j
@RestController
public class EmagHalakApiImpl implements EmagHalakApi {

    public static final String BASE_GAME_URI = "/games/";
    public static final String CREATE_GAME_URI = "";
    public static final String GET_GAME_URI = "{gameId}";
    public static final String PLAY_GAME_URI = "{gameId}/pitEntities/{pitId}";

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @Override
    public ResponseEntity<GameDto> createGame() {
        log.info("Received request to create game");

        GameState gameState = gameService.create();

        return ResponseEntity.created(URI.create(BASE_GAME_URI + gameState.getId())).body(gameMapper.toDto(gameState));
    }

    @Override
    public ResponseEntity<GameDto> playGame(@PathVariable Long gameId, @PathVariable int pitId) throws Exception {
        log.info("Received request to play game with following id:{} and following pit {}", gameId, pitId);

        GameState gameState = gameService.play(gameId, pitId);

        return ResponseEntity.ok(gameMapper.toDto(gameState));
    }

    @Override
    public ResponseEntity<GameDto> getGame(@PathVariable Long gameId) {
        log.info("Received request to get info for the game with following id:{}.", gameId);

        Optional<GameState> info = gameService.getInfo(gameId);

        return info.isPresent() ? ResponseEntity.ok(gameMapper.toDto(info.get())) : ResponseEntity.noContent().build();
    }
}
