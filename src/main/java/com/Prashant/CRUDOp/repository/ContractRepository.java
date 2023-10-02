package com.Prashant.CRUDOp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Prashant.CRUDOp.entity.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

}
