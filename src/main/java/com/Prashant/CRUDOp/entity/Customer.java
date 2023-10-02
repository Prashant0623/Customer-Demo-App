package com.Prashant.CRUDOp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {

	@Id
	@SequenceGenerator(sequenceName = "customer_seq", name = "sequence_customer", initialValue = 1000, allocationSize = 1)

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_customer")
	private Long customerId;
	private String customerName;

	private String accountType;

	@ManyToOne
	@JoinColumn(name = "contract_id")
	private Contract contractType;

	private String sex;
	private String dob;
	private String nativePlace;

}
