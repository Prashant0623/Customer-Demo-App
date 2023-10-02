package com.Prashant.CRUDOp.entity;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Details {

	@Id
	private int detailId;

	private String sex;
	private String dob;
	private String nativePlace;

}
