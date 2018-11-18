package com.halak.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableMap;
import com.halak.DemoApplication;
import com.halak.model.dto.ErrorResponseDto;
import com.halak.model.dto.GameDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;

import static com.halak.api.impl.EmagHalakApiImpl.*;
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
        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post(BASE_GAME_URI + CREATE_GAME_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        GameDto createResultDto = objectMapper.readValue(createResult.getResponse().getContentAsString(), GameDto.class);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(BASE_GAME_URI + GET_GAME_URI, createResultDto.getGameId())
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

        //TODO check pitEntities
    }

    @Test
    @DisplayName("Smoke test for create game operation")
    public void shouldReturnOk_whenCreateGame() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(BASE_GAME_URI + CREATE_GAME_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        GameDto dto = objectMapper.readValue(result.getResponse().getContentAsString(), GameDto.class);

        assertThat(dto).isNotNull();
        assertThat(dto.getGameId()).isNotNull();
        assertThat(dto.getGameId()).isNotNull();
        assertThat(dto.getActivePlayer()).isNotNull();
        assertThat(dto.getPits()).isNotNull();

        //TODO check pitEntities
    }

    @Test
    @DisplayName("Smoke test for play game operation")
    public void shouldReturnOk_whenPlayGame() throws Exception {
        // create game first
        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post(BASE_GAME_URI + CREATE_GAME_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        GameDto createResponseDto = objectMapper.readValue(createResult.getResponse().getContentAsString(), GameDto.class);

        MvcResult playResult = mvc.perform(MockMvcRequestBuilders.put(BASE_GAME_URI + PLAY_GAME_URI, createResponseDto.getGameId(), 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        GameDto playResponseDto = objectMapper.readValue(playResult.getResponse().getContentAsString(), GameDto.class);

        assertThat(playResponseDto).isNotNull();
        assertThat(playResponseDto.getGameId()).isNotNull();
        assertThat(playResponseDto.getGameId()).isNotNull();
        assertThat(playResponseDto.getActivePlayer()).isNotNull();
        assertThat(playResponseDto.getPits()).isNotNull();

        assertThat(playResponseDto.getGameId()).isEqualTo(createResponseDto.getGameId());
        //player should be changed except if south player would start sowing from 1 pit
        assertThat(playResponseDto.getActivePlayer()).isNotEqualTo(createResponseDto.getActivePlayer());

        assertThat(extractPitsFromResponse(createResult.getResponse())).isNotEqualTo(playResult.getResponse());
    }

    private List<ImmutableMap<Integer, Integer>> extractPitsFromResponse(MockHttpServletResponse response) throws IOException {
        ObjectNode jsonNodes = objectMapper.readValue(response.getContentAsString(), ObjectNode.class);
        return objectMapper.readValue(jsonNodes.get("status").toString(), new TypeReference<List<ImmutableMap<Integer, Integer>>>() {
        });
    }

    @Test
    @DisplayName("Smoke test for play game operation given non-eligible pitId")
    public void shouldReturnBadRequest_whenPlayGame_givenPitIdOutsidePlayersBoard() throws Exception {
        MvcResult createResult = mvc.perform(MockMvcRequestBuilders.post(BASE_GAME_URI + CREATE_GAME_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        GameDto createResultDto = objectMapper.readValue(createResult.getResponse().getContentAsString(), GameDto.class);

        // Should be south player turn initially
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put(BASE_GAME_URI + PLAY_GAME_URI, createResultDto.getGameId(), 8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponseDto dto = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponseDto.class);

        assertThat(dto).isNotNull();
        assertThat(dto.getDescription()).contains("doesn't belong to active player");
    }
}