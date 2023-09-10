package com.formation.backend.model.dto.mapper;

import com.formation.backend.dao.CategoryRepository;
import com.formation.backend.exception.NotFoundException;
import com.formation.backend.model.dto.in.ProductDtoIn;
import com.formation.backend.model.dto.out.CategoryDtoOut;
import com.formation.backend.model.dto.out.ProductDtoOut;
import com.formation.backend.model.dto.out.ProductDtoOutShort;
import com.formation.backend.model.entity.Category;
import com.formation.backend.model.entity.Product;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category", target = "category")
    ProductDtoOut entityToDtoOut(Product product);

    CategoryDtoOut categoryToCategoryDtoOut(Category category);

    ProductDtoOutShort entityToDtoOutShort(Product product);

    @Mapping(target = "category", expression = "java(findCategory(productDtoIn.getCategoryId(), categoryRepository))")
    Product dtoInToEntity(ProductDtoIn productDtoIn, @Context CategoryRepository categoryRepository);

    default Category findCategory(Long categoryId, CategoryRepository categoryRepository) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }


}
