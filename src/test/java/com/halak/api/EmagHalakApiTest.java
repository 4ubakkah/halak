package com.halak.api;

import com.halak.api.impl.EmagHalakApiImpl;
import com.halak.model.dto.GameDto;
import com.halak.model.dto.GameDtoFixture;
import com.halak.model.entity.GameState;
import com.halak.model.mapper.impl.GameMapperImpl;
import com.halak.service.impl.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmagHalakApiTest {

    @InjectMocks
    private EmagHalakApiImpl api;

    @Mock
    private GameServiceImpl service;

    @Mock
    private GameMapperImpl mapper;

    @Mock
    private GameState gameState;

    private GameDto gameDto;

    @BeforeEach
    public void beforeEach() {
        gameDto = GameDtoFixture.regular();
    }

    @Test
    @DisplayName("[Should] delegate [when] createGame")
    public void createGame() {
        when(service.create()).thenReturn(gameState);
        when(mapper.toDto(gameState)).thenReturn(gameDto);

        ResponseEntity<GameDto> response = api.createGame();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualToComparingFieldByField(gameDto);
    }

    @Test
    @DisplayName("[Should] delegate [when] playGame")
    public void playGame() throws Exception {
        Long gameId = 1L;
        int pitId = 0;
        when(service.play(gameId, pitId)).thenReturn(gameState);
        when(mapper.toDto(gameState)).thenReturn(gameDto);

        ResponseEntity<GameDto> response = api.playGame(gameId, pitId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualToComparingFieldByField(gameDto);
    }

    @Test
    @DisplayName("[Should] delegate [when] getGame")
    public void getGame() {
        Long gameId = 1L;
        when(service.getInfo(gameId)).thenReturn(Optional.of(gameState));
        when(mapper.toDto(gameState)).thenReturn(gameDto);

        ResponseEntity<GameDto> response = api.getGame(gameId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualToComparingFieldByField(gameDto);
    }
}