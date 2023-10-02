package com.Prashant.CRUDOp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Prashant.CRUDOp.dto.ApiResponse;
import com.Prashant.CRUDOp.dto.CustomerRequest;
import com.Prashant.CRUDOp.dto.CustomerResponse;
import com.Prashant.CRUDOp.entity.MessageCode;
import com.Prashant.CRUDOp.service.CustomerService;

@RestController
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add-customer")
	public ResponseEntity<ApiResponse> addCustomer(@RequestBody CustomerRequest customerRequest) {
		ApiResponse apiResponse = new ApiResponse();
		CustomerResponse customerResponse = customerService.saveCustomer(customerRequest);

		if (customerResponse.getSuccessMessage().equals(MessageCode.SUCCESS)) {
			apiResponse.setCode(MessageCode.SUCCESS);
			apiResponse.setMessage("Customer Data Saved Successfully.");
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} else if (customerResponse.getSuccessMessage().equals(MessageCode.DATA_VALIDATION_ERROR)) {
			apiResponse.setCode(MessageCode.DATA_VALIDATION_ERROR);
			apiResponse.setMessage("Data Validation Error. Please Enter valid input! " + "Sex should be M or F. "
					+ "DOB should be before 01-01-2002.");
			return ResponseEntity.badRequest().body(apiResponse);
		} else if (customerResponse.getSuccessMessage().equals(MessageCode.CONTRACT_TYPE_NOT_FOUND)) {
			apiResponse.setCode(MessageCode.CONTRACT_TYPE_NOT_FOUND);
			apiResponse.setMessage("Contract Type ID not found. Please check again.");
			return ResponseEntity.badRequest().body(apiResponse);
		}
		apiResponse.setCode(MessageCode.FAILURE);
		apiResponse.setMessage("Error Saving Data. Please Try Again Later.");
		return ResponseEntity.internalServerError().body(apiResponse);

	}
}
