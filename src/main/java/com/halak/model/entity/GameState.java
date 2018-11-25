package com.halak.model.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
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
