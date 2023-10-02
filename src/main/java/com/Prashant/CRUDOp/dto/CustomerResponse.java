package com.Prashant.CRUDOp.dto;

import java.util.List;

import com.Prashant.CRUDOp.entity.Contract;
import com.Prashant.CRUDOp.entity.MessageCode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

	public CustomerResponse() {

	}

	public CustomerResponse(Long customerId, String customerName, String sex, String dob, String nativePlace,
			String accountType, List<String> businessRequirements, Contract contractType, MessageCode successMessage) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.sex = sex;
		this.dob = dob;
		this.nativePlace = nativePlace;
		this.accountType = accountType;
		this.businessRequirements = businessRequirements;
		this.contractType = contractType;
		this.successMessage = successMessage;
	}

	private Long customerId;
	private String customerName;
	private String sex;
	private String dob;
	private String nativePlace;

	private String accountType;
	private List<String> businessRequirements;

	private Contract contractType;

	private MessageCode successMessage;
}
