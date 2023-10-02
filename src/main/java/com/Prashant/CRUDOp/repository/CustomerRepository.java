package com.Prashant.CRUDOp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Prashant.CRUDOp.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT CustomerData.customer_seq.next_val FROM customer_seq", nativeQuery = true)
	public Long nextCustomerId();
}
