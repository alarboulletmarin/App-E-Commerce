package com.formation.backend.model.dto.mapper;

import com.formation.backend.model.dto.out.CategoryDtoOut;
import com.formation.backend.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDtoOut entityToDtoOut(Category category);

    Category dtoInToEntity(CategoryDtoOut categoryDtoOut);
}
