package com.halak.api;

import com.halak.model.dto.GameDto;
import com.halak.model.dto.GameDtoFixture;
import com.halak.model.game.Game;
import com.halak.model.mapper.impl.GameMapperImpl;
import com.halak.service.impl.GameServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmagHalakApiTest {

    @InjectMocks
    private EmagHalakApi api;

    @Mock
    private GameServiceImpl service;

    @Mock
    private GameMapperImpl mapper;

    @Test
    @DisplayName("[Should] delegate [when] createGame")
    public void createGame() {
        //TODO get rid of mock
        Game game = Mockito.mock(Game.class);
        GameDto gameDto = GameDtoFixture.regular();
        when(service.create()).thenReturn(game);
        when(mapper.toDto(game)).thenReturn(gameDto);

        ResponseEntity<GameDto> response = api.createGame();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualToComparingFieldByField(gameDto);
    }

    @Test
    @DisplayName("[Should] delegate [when] playGame")
    public void playGame() throws Exception {
        //TODO get rid of mock
        Game game = Mockito.mock(Game.class);
        GameDto gameDto = GameDtoFixture.regular();
        String gameId = UUID.randomUUID().toString();
        int pitId = 0;
        when(service.play(gameId, pitId)).thenReturn(game);
        when(mapper.toDto(game)).thenReturn(gameDto);

        ResponseEntity<GameDto> response = api.playGame(gameId, pitId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualToComparingFieldByField(gameDto);
    }

    @Test
    @DisplayName("[Should] delegate [when] getGame")
    public void getGame() {
        //TODO get rid of mock
        Game game = Mockito.mock(Game.class);
        GameDto gameDto = GameDtoFixture.regular();
        String gameId = UUID.randomUUID().toString();
        when(service.getInfo(gameId)).thenReturn(Optional.of(game));
        when(mapper.toDto(game)).thenReturn(gameDto);

        ResponseEntity<GameDto> response = api.getGame(gameId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualToComparingFieldByField(gameDto);
    }
}