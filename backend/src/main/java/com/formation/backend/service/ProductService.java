package com.formation.backend.service;

import com.formation.backend.dao.CategoryRepository;
import com.formation.backend.dao.ProductRepository;
import com.formation.backend.exception.NotFoundException;
import com.formation.backend.model.dto.in.ProductDtoIn;
import com.formation.backend.model.dto.mapper.ProductMapper;
import com.formation.backend.model.dto.out.ProductDtoOut;
import com.formation.backend.model.dto.out.ProductDtoOutShort;
import com.formation.backend.model.entity.Category;
import com.formation.backend.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final String TextNotFound = "Product not found";
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, CategoryRepository categoryRepository1, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDtoOutShort> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::entityToDtoOutShort)
                .collect(Collectors.toList());
    }

    public ProductDtoOut getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(TextNotFound));
        return productMapper.entityToDtoOut(product);
    }

    public ProductDtoOut createProduct(ProductDtoIn productDtoIn) {
        Product product = productMapper.dtoInToEntity(productDtoIn, categoryRepository);
        Category category = categoryRepository.findById(productDtoIn.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found"));
        product.setCategory(category);
        Product createdProduct = productRepository.save(product);
        return productMapper.entityToDtoOut(createdProduct);
    }


    /**
     * Update a product
     *
     * @param id
     * @param productDtoIn
     * @return ProductDtoOut
     */
    public ProductDtoOut updateProduct(Long id, ProductDtoIn productDtoIn) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(TextNotFound);
        }
        Product product = productMapper.dtoInToEntity(productDtoIn, categoryRepository);
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        return productMapper.entityToDtoOut(updatedProduct);
    }

    /**
     * Delete a product
     *
     * @param id
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(TextNotFound);
        }
        productRepository.deleteById(id);
    }

}
