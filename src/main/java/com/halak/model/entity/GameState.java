package com.halak.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GAME_STATE")
public class GameState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "DECIMAL")
    private Long id;

    @Version
    private Integer version = 0;

    @Column(name = "LAST_UPDATED")
    @Temporal(TIMESTAMP)
    private Date lastUpdated;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PlayerEntity activePlayerEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlayerEntity> playerEntities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GAME_BOARD_ID")
    private GameBoardEntity gameBoard;

    @Column(name = "STARTED")
    private Boolean started = Boolean.FALSE;

    @Column(name = "COMPLETE")
    private Boolean complete = Boolean.FALSE;
}
