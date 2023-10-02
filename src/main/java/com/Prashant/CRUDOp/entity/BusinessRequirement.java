package com.Prashant.CRUDOp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "seq", initialValue = 1000, allocationSize = 1)

public class BusinessRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long requirementId;

	private String description;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;

}
