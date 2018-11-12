package com.halak.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.halak.DemoApplication;
import com.halak.api.impl.EmagHalakApiImpl;
import com.halak.model.dto.ErrorResponseDto;
import com.halak.model.dto.GameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {DemoApplication.class})
@ExtendWith(SpringExtension.class)
public class EmagHalakApiSTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .build();

        objectMapper.registerModule(new GuavaModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    @DisplayName("Smoke test for get game operation")
    public void shouldReturnOk_whenGetGame() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(EmagHalakApiImpl.GET_GAME_URI, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        GameDto dto = objectMapper.readValue(result.getResponse().getContentAsString(), GameDto.class);

        assertThat(dto).isNotNull();
        assertThat(dto.getGameId()).isNotNull();
        assertThat(UUID.fromString(dto.getGameId())).isNotNull();
        assertThat(dto.getActivePlayer()).isNotNull();
        assertThat(dto.getPits()).isNotNull();

        //TODO check pits
    }

    @Test
    @DisplayName("Smoke test for create game operation")
    public void shouldReturnOk_whenCreateGame() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(EmagHalakApiImpl.CREATE_GAME_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        GameDto dto = objectMapper.readValue(result.getResponse().getContentAsString(), GameDto.class);

        assertThat(dto).isNotNull();
        assertThat(dto.getGameId()).isNotNull();
        assertThat(UUID.fromString(dto.getGameId())).isNotNull();
        assertThat(dto.getActivePlayer()).isNotNull();
        assertThat(dto.getPits()).isNotNull();

        //TODO check pits
    }

    @Test
    @DisplayName("Smoke test for play game operation")
    public void shouldReturnOk_whenPlayGame() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put(EmagHalakApiImpl.PLAY_GAME_URI, 1, 5)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        mvc.perform(MockMvcRequestBuilders.put(EmagHalakApiImpl.PLAY_GAME_URI, 1, 11)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(EmagHalakApiImpl.PLAY_GAME_URI, 1, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        GameDto dto = objectMapper.readValue(result.getResponse().getContentAsString(), GameDto.class);

        assertThat(dto).isNotNull();
        assertThat(dto.getGameId()).isNotNull();
        assertThat(dto.getGameId()).isNotNull();
        assertThat(dto.getActivePlayer()).isNotNull();
        assertThat(dto.getPits()).isNotNull();

        //TODO check pits
    }

    @Test
    @DisplayName("Smoke test for play game operation given non-eligible pitId")
    public void shouldReturnBadRequest_whenPlayGame_givenPitIdOutsidePlayersBoard() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(EmagHalakApiImpl.PLAY_GAME_URI, 1, 8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponseDto dto = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponseDto.class);

        assertThat(dto).isNotNull();
        assertThat(dto.getDescription()).contains("doesn't belong to active player");
    }
}