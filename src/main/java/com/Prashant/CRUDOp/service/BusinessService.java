package com.Prashant.CRUDOp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Prashant.CRUDOp.entity.BusinessRequirement;
import com.Prashant.CRUDOp.repository.BusinessRepository;

@Service
public class BusinessService {

	@Autowired
	private BusinessRepository businessRepository;

	public List<String> getByCustomerId(long id) {
		List<String> requirements = businessRepository.findByCustomerId(id);
		return requirements;
	}

	public List<BusinessRequirement> addBusinessRequirement(List<BusinessRequirement> businessRequirements) {

		if (businessRequirements.size() != 0) {
			List<BusinessRequirement> requirements = businessRepository.saveAll(businessRequirements);
			return requirements;
		}

		return null;

	}
}