package com.zuka.company.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zuka.company.dto.CategoryDTO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	@LoadBalanced
	RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "defaultCategory")
	public CategoryDTO findByCategoryId(UUID id) {
		return restTemplate.getForObject("http://CATEGORYAPI/findById/{id}", CategoryDTO.class, id);
	}
	
	@SuppressWarnings("unused")
	private CategoryDTO defaultCategory(UUID id) {
		return new CategoryDTO();
	}
}
