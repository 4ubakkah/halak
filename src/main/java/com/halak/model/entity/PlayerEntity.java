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
public class PlayerEntity extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    private int kalahIndex;
}
