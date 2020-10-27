package com.zuka.company.mapper;

import com.zuka.company.dto.CompanyDTO;
import com.zuka.company.model.Company;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-27T22:03:59+0000",
    comments = "version: 1.4.0.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDTO toCompanyDTO(Company company) {
        if ( company == null ) {
            return null;
        }

        UUID id = null;

        CompanyDTO companyDTO = new CompanyDTO( id );

        return companyDTO;
    }

    @Override
    public Company toCompany(CompanyDTO companyDTO) {
        if ( companyDTO == null ) {
            return null;
        }

        Company company = new Company();

        return company;
    }
}
