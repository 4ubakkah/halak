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
public class PitEntity extends BaseEntity {

    @Column(name = "INDEX")
    private Integer index;

    @Column(name = "IS_KALAH")
    private boolean kalah;

    @Column(name = "STONES_COUNT")
    private int stonesCount = GameSpecifications.INITIAL_STONES_COUNT_PER_PIT;

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
