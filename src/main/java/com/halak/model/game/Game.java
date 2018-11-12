package com.halak.model.game;

import com.halak.model.game.pit.Pit;
import com.halak.model.player.FirstPlayer;
import com.halak.model.player.Player;
import com.halak.model.player.SecondPlayer;
import lombok.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Getter
    @Setter
    private GameBoard board = new GameBoard();

    @Getter
    private List<Player> players = Arrays.asList(new FirstPlayer(), new SecondPlayer());

    @Getter
    private String gameId = UUID.randomUUID().toString();

    @Getter
    @Setter
    private Player activePlayer = players.get(0);

    @Getter
    @Setter
    private boolean complete = Boolean.FALSE;

    @Getter
    @Setter
    private boolean started = Boolean.FALSE;

    @Getter
    @Setter
    private int lastSownPitId = -1;

    public List<Pit> getPlayersPits(Player player) {
        return board.getPitsInRange(player.getKalahIndex() - GameSpecifications.PITS_COUNT_PER_PLAYER, player.getKalahIndex());
    }

    public Pit getLastSownPit() {
        return board.getPit(lastSownPitId);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
