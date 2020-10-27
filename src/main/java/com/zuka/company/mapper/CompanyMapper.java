package com.zuka.company.mapper;

import org.mapstruct.Mapper;

import com.zuka.company.dto.CompanyDTO;
import com.zuka.company.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	CompanyDTO toCompanyDTO(Company company);
	Company toCompany(CompanyDTO companyDTO);

}
