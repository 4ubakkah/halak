package com.halak.model.entity;

import com.fasterxml.jackson.databind.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "GAME_STATE")
public class GameState extends BaseEntity {

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
