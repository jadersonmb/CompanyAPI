package com.zuka.category.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.zuka.category.dto.CategoryDTO;
import com.zuka.category.exception.CategoryException;
import com.zuka.category.exception.ProblemType;
import com.zuka.category.mapper.CategoryMapper;
import com.zuka.category.model.Category;
import com.zuka.category.repository.CategoryRepository;
import com.zuka.category.repository.CategorySpec;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private CategoryMapper mapper;
	private MessageSource messageSource;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper,
			MessageSource messageSource) {
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
		this.messageSource = messageSource;
	}

	private void BusinessRulesSave(CategoryDTO categoryDTO) throws CategoryException {
		if(Objects.isNull(categoryDTO.getId())) {
			NotSaveNameDuplicate(categoryDTO);
		}
	}


	private void NotSaveNameDuplicate(CategoryDTO categoryDTO) throws CategoryException {
		ProblemType problemType = ProblemType.NAME_ALREADY_EXISTS;
		String messageDetails = messageSource.getMessage(problemType.getMessageSource(),
				new Object[] { categoryDTO.getName() }, LocaleContextHolder.getLocale());
		if (Objects.nonNull(categoryRepository.findByname(categoryDTO.getName()))) {
			throw new CategoryException(HttpStatus.BAD_REQUEST.value(), problemType.getUri(), problemType.getTitle(),
					messageDetails);
		};
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) throws CategoryException {
		BusinessRulesSave(categoryDTO);

		Category category = categoryRepository.save(mapper.toCategory(categoryDTO));
		return mapper.toCategoryDTO(category);
	}

	@Override
	public Page<CategoryDTO> listAll(Pageable pageable, CategoryDTO filter) throws CategoryException {
		return categoryRepository.findAll(CategorySpec.searchDesc(filter), pageable).map(mapper::toCategoryDTO);
	}

	@Override
	public void delete(CategoryDTO categoryDTO) throws CategoryException {
		categoryRepository.delete(mapper.toCategory(categoryDTO));
	}

	@Override
	public void deleteList(List<UUID> ids) throws CategoryException {
		ids.forEach(obj -> delete(findById(obj)));
	}

	@Override
	public CategoryDTO findById(UUID id) throws CategoryException {
		ProblemType problemType = ProblemType.USER_NOT_EXISTS;
		Optional<Category> obj = categoryRepository.findById(id);
		String messageDetails = messageSource.getMessage(problemType.getMessageSource(), new Object[] {""}, LocaleContextHolder.getLocale());
		CategoryDTO categoryDTO = mapper
				.toCategoryDTO(obj.orElseThrow(() -> new CategoryException(HttpStatus.BAD_REQUEST.value(),
						problemType.getTitle(), problemType.getUri(), messageDetails)));
		return categoryDTO;
	}

}
