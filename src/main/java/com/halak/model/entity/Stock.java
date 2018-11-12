package com.halak.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, columnDefinition = "DECIMAL")
	private Long id;

	@Version
	private Integer version = 0;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CURRENT_PRICE")
	private BigDecimal currentPrice;

	@Column(name = "LAST_UPDATED")
	@Temporal(TIMESTAMP)
	private Date lastUpdated;

}
