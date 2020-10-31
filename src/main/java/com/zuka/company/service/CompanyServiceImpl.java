package com.zuka.company.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.zuka.company.dto.CategoryDTO;
import com.zuka.company.dto.CompanyDTO;
import com.zuka.company.exception.CompanyException;
import com.zuka.company.exception.ProblemType;
import com.zuka.company.mapper.CompanyMapper;
import com.zuka.company.model.Company;
import com.zuka.company.repository.CompanyRepository;
import com.zuka.company.repository.CompanySpec;

@Service
public class CompanyServiceImpl implements CompanyService {

	private CompanyRepository companyRepository;
	private CategoryService categoryService;
	private CompanyMapper mapper;
	private MessageSource messageSource;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper mapper,
			MessageSource messageSource, CategoryService categoryService) {
		this.companyRepository = companyRepository;
		this.mapper = mapper;
		this.messageSource = messageSource;
		this.categoryService = categoryService;
	}

	private void BusinessRulesSave(CompanyDTO companyDTO) throws CompanyException {
		if(Objects.isNull(companyDTO.getId())) {
			NotSaveNameDuplicate(companyDTO);
		}
	}


	private void NotSaveNameDuplicate(CompanyDTO companyDTO) throws CompanyException {
		ProblemType problemType = ProblemType.NAME_ALREADY_EXISTS;
		String messageDetails = messageSource.getMessage(problemType.getMessageSource(),
				new Object[] { companyDTO.getName() }, LocaleContextHolder.getLocale());
		if (Objects.nonNull(companyRepository.findByname(companyDTO.getName()))) {
			throw new CompanyException(HttpStatus.BAD_REQUEST.value(), problemType.getUri(), problemType.getTitle(),
					messageDetails);
		};
	}

	@Override
	public Company save(CompanyDTO companyDTO) throws CompanyException {
		BusinessRulesSave(companyDTO);
		Company company  = mapper.toCompany(companyDTO);
		company.setCategoryId(companyDTO.getCategory().getId());
		return companyRepository.save(company);
	}
	
	@Override
	public CompanyDTO saveCompany(CompanyDTO companyDTO) throws CompanyException {
		Company company = save(companyDTO);
		CompanyDTO dto = mapper.toCompanyDTO(company);
		dto.setCategory(findByCategoryId(company.getCategoryId()));
		return dto;
	}

	@Override
	public Page<CompanyDTO> listAll(Pageable pageable, CompanyDTO filter) throws CompanyException {
		Page<Company> companyPage = companyRepository.findAll(CompanySpec.searchDesc(filter), pageable);
		return new PageImpl<CompanyDTO>(companyPage
                .stream()
                .map(p -> new CompanyDTO(
                        p.getId(),
                        p.getName(),
                        p.getCnpj(),
                        p.getAddress(),
                        findByCategoryId(p.getCategoryId())))
                .collect(Collectors.toList()), pageable, companyPage.getTotalElements());
	}

	@Override
	public void delete(CompanyDTO companyDTO) throws CompanyException {
		companyRepository.delete(mapper.toCompany(companyDTO));
	}

	@Override
	public void deleteList(List<UUID> ids) throws CompanyException {
		ids.forEach(obj -> delete(findById(obj)));
	}

	@Override
	public CompanyDTO findById(UUID id) throws CompanyException {
		ProblemType problemType = ProblemType.USER_NOT_EXISTS;
		Optional<Company> obj = companyRepository.findById(id);
		String messageDetails = messageSource.getMessage(problemType.getMessageSource(), new Object[] {""}, LocaleContextHolder.getLocale());
		CompanyDTO companyDTO = mapper
				.toCompanyDTO(obj.orElseThrow(() -> new CompanyException(HttpStatus.BAD_REQUEST.value(),
						problemType.getTitle(), problemType.getUri(), messageDetails)));
		return companyDTO;
	}
	
	private CategoryDTO findByCategoryId(UUID categoryId) {
		return categoryService.findByCategoryId(categoryId);
	}

}
