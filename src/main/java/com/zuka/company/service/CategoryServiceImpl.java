package com.zuka.company.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

import com.zuka.company.dto.CategoryDTO;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	@LoadBalanced
	RestTemplate restTemplate;

	@Override
	public CategoryDTO findByCategoryId(UUID id) {
		return restTemplate.getForObject("http://CategoryAPI/api/category/{id}", CategoryDTO.class, id);
	}
	
}
