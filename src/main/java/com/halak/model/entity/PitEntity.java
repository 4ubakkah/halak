package com.halak.model.entity;

import com.halak.configuration.GameSpecifications;
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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PIT")
public class PitEntity {

    public PitEntity(int index, boolean kalah) {
        this.index = index;
        this.kalah = kalah;
        //TODO cleanup
        if (kalah) {
            stonesCount = 0;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "DECIMAL")
    private Long id;

    @Version
    private Integer version = 0;

    @Column(name = "INDEX")
    private Integer index;

    @Column(name = "LAST_UPDATED")
    @Temporal(TIMESTAMP)
    private Date lastUpdated;

//    @ManyToOne
//    private PlayerEntity player;
//    @JoinColumn(name = "PLAYER_ID")

    @Column(name = "IS_KALAH")
    private boolean kalah;

    @Column(name = "STONES_COUNT")
    private int stonesCount = GameSpecifications.STONES_COUNT_PER_PIT;

    public int extractStones() {
        int extractedStonesCount = stonesCount;
        stonesCount = 0;
        return extractedStonesCount;
    }

    public void putStone() {
        stonesCount++;
    }

    public void addStones(int stones) {
        this.stonesCount = this.stonesCount + stones;
    }
}
