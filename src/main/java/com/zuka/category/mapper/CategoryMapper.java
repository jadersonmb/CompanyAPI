package com.zuka.category.mapper;

import org.mapstruct.Mapper;

import com.zuka.category.dto.CategoryDTO;
import com.zuka.category.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDTO toCategoryDTO(Category category);
	 Category toCategory(CategoryDTO categoryDTO);

}
