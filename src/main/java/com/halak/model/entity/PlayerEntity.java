package com.halak.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLAYER")
public class PlayerEntity extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    private int kalahIndex;
}
