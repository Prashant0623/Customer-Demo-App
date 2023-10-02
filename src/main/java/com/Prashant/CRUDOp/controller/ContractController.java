package com.Prashant.CRUDOp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Prashant.CRUDOp.dto.ContractRequest;
import com.Prashant.CRUDOp.dto.ContractResponse;
import com.Prashant.CRUDOp.service.ContractService;

@RestController
public class ContractController {

	@Autowired
	private ContractService contractService;

	@PostMapping("/add-contract-type")
	public ResponseEntity<ContractResponse> addContractType(@RequestBody ContractRequest contractRequest) {
		ContractResponse contractResponse = contractService.addContractType(contractRequest);

		if (contractResponse != null) {

			return new ResponseEntity<>(contractResponse, HttpStatus.OK);

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}

}
