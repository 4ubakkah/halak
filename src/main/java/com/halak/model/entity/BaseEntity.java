package com.halak.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "DECIMAL")
    private Long id;

    @Version
    private Integer version = 0;

    @LastModifiedDate
    @Column(name = "LAST_UPDATED")
    @Temporal(TIMESTAMP)
    private Date lastUpdated;
}
