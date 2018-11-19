package com.halak.service.impl;

import com.halak.model.entity.GameState;
import com.halak.model.entity.factory.GameStateFactory;
import com.halak.model.mapper.GameMapper;
import com.halak.persistance.GameStateRepository;
import com.halak.service.rules.ChainFactory;
import com.halak.service.rules.GameContext;
import com.halak.service.rules.KalahGameChain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameStateRepository gameStateRepository;

    @Mock
    private GameStateFactory gameStateFactory;

    @Mock
    private GameMapper gameMapper;

    @Mock
    private GameState gameState;

    @Mock
    private ChainFactory chainFactory;

    @Test
    @DisplayName("[Should] delegate game creation")
    public void create() {
        when(gameStateFactory.defaultGameState()).thenReturn(gameState);
        when(gameStateRepository.save(gameState)).thenReturn(gameState);

        GameState persistedGameState = gameService.create();

        assertThat(persistedGameState).isEqualTo(gameState);

        verify(gameStateRepository).save(gameState);
        verifyNoMoreInteractions(gameStateRepository);
    }

    @Test
    @DisplayName("[Should] delegate game play")
    public void play() throws Exception {
        Long gameId = 1L;
        int pitIndex = 2;
        GameContext gameContext = mock(GameContext.class);
        GameState gameStateAfterPlay = mock(GameState.class);
        KalahGameChain kalahGameChain = mock(KalahGameChain.class);

        when(gameStateRepository.findGameById(gameId)).thenReturn(Optional.of(gameState));
        when(gameMapper.toContext(gameState, pitIndex)).thenReturn(gameContext);
        when(chainFactory.kalahGameChain()).thenReturn(kalahGameChain);
        when(gameStateRepository.save(gameState)).thenReturn(mock(GameState.class));
        when(gameMapper.toEntity(gameContext)).thenReturn(gameStateAfterPlay);

        GameState persistedGameState = gameService.play(gameId, pitIndex);

        assertThat(persistedGameState).isNotEqualTo(gameState);
    }

    @Test
    @DisplayName("[Should] delegate retrieval of game information")
    public void getInfo() {
        Long gameId = 1L;

        when(gameStateRepository.findGameById(gameId)).thenReturn(Optional.of(gameState));

        Optional<GameState> info = gameService.getInfo(gameId);

        assertThat(info).isPresent();
        assertThat(info).hasValue(gameState);
        assertThat(info.get()).isEqualTo(gameState);

        verify(gameStateRepository).findGameById(gameId);
        verifyNoMoreInteractions(gameStateRepository);
    }
}