package com.zuka.category.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zuka.category.dto.CategoryDTO;
import com.zuka.category.exception.CategoryException;

@Service
public interface CategoryService {

	CategoryDTO save(CategoryDTO categoryDTO) throws CategoryException;
	Page<CategoryDTO> listAll(Pageable pageable, CategoryDTO filter) throws CategoryException;
	void delete(CategoryDTO categoryDTO) throws CategoryException;
	CategoryDTO findById(UUID id) throws CategoryException;
	void deleteList(List<UUID> ids) throws CategoryException;
}
