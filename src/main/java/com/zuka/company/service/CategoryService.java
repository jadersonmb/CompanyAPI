package com.zuka.company.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zuka.company.dto.CategoryDTO;

@Service
public interface CategoryService {

	@GetMapping(value = "/findById/{id}")
	CategoryDTO findByCategoryId(@PathVariable("id") UUID id);
}
