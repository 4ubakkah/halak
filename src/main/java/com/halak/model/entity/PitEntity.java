package com.halak.model.entity;

import com.halak.configuration.GameSpecifications;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
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
