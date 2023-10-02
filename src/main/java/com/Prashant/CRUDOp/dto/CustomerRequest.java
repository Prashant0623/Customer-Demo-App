package com.Prashant.CRUDOp.dto;

import java.util.List;

import com.Prashant.CRUDOp.entity.Details;

import lombok.Data;

@Data
public class CustomerRequest {

	private Long customerId;
	private String customerName;

	private Details details;

	private List<String> businessRequirements;
	private String accountType;

	private int contractType;

}
