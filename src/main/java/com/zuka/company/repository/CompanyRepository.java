package com.zuka.company.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zuka.company.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID>, JpaSpecificationExecutor<Company> {

	Company findByname(String name);
}
