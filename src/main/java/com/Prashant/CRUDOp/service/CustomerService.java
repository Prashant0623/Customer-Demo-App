package com.Prashant.CRUDOp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Prashant.CRUDOp.dto.CustomerRequest;
import com.Prashant.CRUDOp.dto.CustomerResponse;
import com.Prashant.CRUDOp.entity.BusinessRequirement;
import com.Prashant.CRUDOp.entity.Contract;
import com.Prashant.CRUDOp.entity.Customer;
import com.Prashant.CRUDOp.entity.MessageCode;
import com.Prashant.CRUDOp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ContractService contractService;

	@Autowired
	private BusinessService businessService;

	public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
		CustomerResponse response = new CustomerResponse();

		Customer savedCustomer = new Customer();

		try {
			if (customerRequest != null) {

				List<Contract> availableContracts = contractService.getAvailableContractTypes();

				int isAvailable = 0;

				for (Contract contract : availableContracts) {
					if (contract.getContractId() == customerRequest.getContractType()) {
						isAvailable = 1;
						break;
					}
				}

				if (isAvailable == 0) {
					response.setSuccessMessage(MessageCode.CONTRACT_TYPE_NOT_FOUND);
					return response;
				}

				if (checkValidation(customerRequest)) {
					Customer customer = new Customer();
					customer.setCustomerId(customerRepository.nextCustomerId());
					customer.setCustomerName(customerRequest.getCustomerName());
					customer.setAccountType(customerRequest.getAccountType());
					List<BusinessRequirement> businessRequirements = new ArrayList<>();

					for (int i = 0; i < customerRequest.getBusinessRequirements().size(); i++) {
						BusinessRequirement businessRequirement = new BusinessRequirement();
						businessRequirement.setCustomerId(customer.getCustomerId());
						businessRequirement.setDescription(customerRequest.getBusinessRequirements().get(i));
						businessRequirements.add(businessRequirement);
					}

					businessService.addBusinessRequirement(businessRequirements);
					customer.setContractType(contractService.getContractById(customerRequest.getContractType()));

					customer.setDob(customerRequest.getDetails().getDob());
					customer.setNativePlace(customerRequest.getDetails().getNativePlace());
					customer.setSex(customerRequest.getDetails().getSex());

					savedCustomer = customerRepository.save(customer);
					response = createCustomerResponse(savedCustomer);
					response.setSuccessMessage(MessageCode.SUCCESS);

				} else {
					response.setSuccessMessage(MessageCode.DATA_VALIDATION_ERROR);

				}
			} else {
				response.setSuccessMessage(MessageCode.NULL_REQUEST_PASSED);
			}
		} catch (Exception e) {
			response.setSuccessMessage(MessageCode.FAILURE);
		}

		return response;

	}

	public boolean checkValidation(CustomerRequest customerRequest) throws ParseException {
		String validDate = "20-01-2002";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		boolean validDateOrNot = simpleDateFormat.parse(customerRequest.getDetails().getDob())
				.before(simpleDateFormat.parse(validDate));

		boolean validGender = customerRequest.getDetails().getSex().equalsIgnoreCase("M")
				|| customerRequest.getDetails().getSex().equalsIgnoreCase("F");

		if (validDateOrNot && validGender)
			return true;

		return false;

	}

	public CustomerResponse createCustomerResponse(Customer savedCustomer) {
		CustomerResponse response = new CustomerResponse();
		System.out.println(savedCustomer);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		response = modelMapper.map(savedCustomer, CustomerResponse.class);
		response.setBusinessRequirements(businessService.getByCustomerId(savedCustomer.getCustomerId()));
		return response;

	}

}
