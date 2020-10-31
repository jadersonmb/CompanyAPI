package com.zuka.company.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zuka.company.dto.CompanyDTO;
import com.zuka.company.exception.CompanyException;
import com.zuka.company.model.Company;

@Service
public interface CompanyService {

	Company save(CompanyDTO companyDTO) throws CompanyException;
	CompanyDTO saveCompany(CompanyDTO companyDTO) throws CompanyException;
	Page<CompanyDTO> listAll(Pageable pageable, CompanyDTO filter) throws CompanyException;
	void delete(CompanyDTO companyDTO) throws CompanyException;
	CompanyDTO findById(UUID id) throws CompanyException;
	void deleteList(List<UUID> ids) throws CompanyException;
}
