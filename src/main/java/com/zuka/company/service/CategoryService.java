package com.zuka.company.service;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zuka.company.dto.CategoryDTO;

public interface CategoryService {

	@GetMapping(value = "/api/category/{id}")
	CategoryDTO findByCategoryId(@PathVariable("id") UUID id);
}
