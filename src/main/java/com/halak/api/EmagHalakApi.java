package com.halak.api;

import com.halak.api.impl.EmagHalakApiImpl;
import com.halak.model.dto.GameDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.halak.api.impl.EmagHalakApiImpl.BASE_GAME_URI;

@RequestMapping(path = BASE_GAME_URI, produces = {MediaType.APPLICATION_JSON_VALUE})
public interface EmagHalakApi {

    @ApiOperation(value = "Create a game", httpMethod = "POST")
    @PostMapping(value = EmagHalakApiImpl.CREATE_GAME_URI)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created game"),
            @ApiResponse(code = 400, message = "Could not make move based on provided arguments"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    ResponseEntity<GameDto> createGame();

    @ApiOperation(value = "Make turn", httpMethod = "PUT")
    @PutMapping(value = EmagHalakApiImpl.PLAY_GAME_URI)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully made player's move"),
            @ApiResponse(code = 400, message = "Could not make move based on provided arguments"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    ResponseEntity<GameDto> playGame(@PathVariable Long gameId, @PathVariable int pitId) throws Exception;

    @ApiOperation(value = "Get game info", httpMethod = "GET")
    @GetMapping(value = EmagHalakApiImpl.GET_GAME_URI)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved game information"),
            @ApiResponse(code = 204, message = "No game information to display"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    ResponseEntity<GameDto> getGame(@PathVariable Long gameId);
}
