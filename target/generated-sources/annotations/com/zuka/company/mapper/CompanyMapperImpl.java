package com.zuka.company.mapper;

import com.zuka.company.dto.CompanyDTO;
import com.zuka.company.dto.CompanyDTO.CompanyDTOBuilder;
import com.zuka.company.model.Company;
import com.zuka.company.model.Company.CompanyBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-31T01:19:09+0000",
    comments = "version: 1.4.0.Final, compiler: Eclipse JDT (IDE) 3.20.0.v20191203-2131, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyDTO toCompanyDTO(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDTOBuilder companyDTO = CompanyDTO.builder();

        companyDTO.address( company.getAddress() );
        companyDTO.cnpj( company.getCnpj() );
        companyDTO.id( company.getId() );
        companyDTO.name( company.getName() );

        return companyDTO.build();
    }

    @Override
    public Company toCompany(CompanyDTO companyDTO) {
        if ( companyDTO == null ) {
            return null;
        }

        CompanyBuilder company = Company.builder();

        company.address( companyDTO.getAddress() );
        company.cnpj( companyDTO.getCnpj() );
        company.id( companyDTO.getId() );
        company.name( companyDTO.getName() );

        return company.build();
    }
}
