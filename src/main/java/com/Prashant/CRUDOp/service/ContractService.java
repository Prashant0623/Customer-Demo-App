package com.Prashant.CRUDOp.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Prashant.CRUDOp.dto.ContractRequest;
import com.Prashant.CRUDOp.dto.ContractResponse;
import com.Prashant.CRUDOp.entity.Contract;
import com.Prashant.CRUDOp.repository.ContractRepository;

@Service
public class ContractService {

	@Autowired
	private ContractRepository contractRepository;

	public ContractResponse addContractType(ContractRequest contractRequest) {
		ContractResponse response = new ContractResponse();

		Contract contract = new Contract();
		if (contractRequest != null) {

			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			contract.setContractName(contractRequest.getContractName());
			Contract savedContract = contractRepository.save(contract);
			response = modelMapper.map(savedContract, ContractResponse.class);
			return response;

		}
		return null;

	}

	public Contract getContractById(int contractId) {

		Contract contract = contractRepository.findById(contractId).get();
		return contract;
	}

	public List<Contract> getAvailableContractTypes(){
		return contractRepository.findAll();
	}
}
