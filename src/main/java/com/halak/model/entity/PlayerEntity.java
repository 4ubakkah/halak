package com.halak.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLAYER")
public class PlayerEntity {

    public PlayerEntity(int kalahIndex, String name) {
        this.kalahIndex = kalahIndex;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "DECIMAL")
    private Long id;

    @Version
    private Integer version = 0;

    @Column(name = "LAST_UPDATED")
    @Temporal(TIMESTAMP)
    private Date lastUpdated;

//    @ManyToOne
//    @JoinColumn(name = "GAME_STATE_ID")
//    private GameState gameState;

    @Column(name = "NAME")
    private String name;

    private int kalahIndex;
}
