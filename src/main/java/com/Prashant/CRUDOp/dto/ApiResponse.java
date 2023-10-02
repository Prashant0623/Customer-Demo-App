package com.Prashant.CRUDOp.dto;

import com.Prashant.CRUDOp.entity.MessageCode;

import lombok.Data;

@Data

public class ApiResponse {

	private MessageCode code;
	private String message;

}
