package com.zuka.category.mapper;

import com.zuka.category.dto.CategoryDTO;
import com.zuka.category.model.Category;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-25T11:41:18+0000",
    comments = "version: 1.4.0.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        UUID id = null;

        CategoryDTO categoryDTO = new CategoryDTO( id );

        return categoryDTO;
    }

    @Override
    public Category toCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        return category;
    }
}
