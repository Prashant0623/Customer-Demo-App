package com.Prashant.CRUDOp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Prashant.CRUDOp.entity.BusinessRequirement;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessRequirement, Long> {

	@Query(value = "SELECT description from business_requirement where customer_id =:id", nativeQuery = true)
	public List<String> findByCustomerId(@Param("id") Long id);

}
