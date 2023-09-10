package com.formation.backend.service;

import com.formation.backend.dao.CategoryRepository;
import com.formation.backend.exception.NotFoundException;
import com.formation.backend.model.dto.mapper.CategoryMapper;
import com.formation.backend.model.dto.out.CategoryDtoOut;
import com.formation.backend.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final String TextNotFound = "Product not found";

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDtoOut> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::entityToDtoOut)
                .collect(Collectors.toList());
    }

    public CategoryDtoOut getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(TextNotFound));
        return categoryMapper.entityToDtoOut(category);
    }

    public CategoryDtoOut createCategory(CategoryDtoOut categoryDtoOut) {
        Category category = categoryMapper.dtoInToEntity(categoryDtoOut);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.entityToDtoOut(savedCategory);
    }

    public CategoryDtoOut updateCategory(Long id, CategoryDtoOut categoryDtoOut) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(TextNotFound);
        }
        Category category = categoryMapper.dtoInToEntity(categoryDtoOut);
        category.setId(id);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.entityToDtoOut(updatedCategory);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(TextNotFound);
        }
        categoryRepository.deleteById(id);
    }
}
