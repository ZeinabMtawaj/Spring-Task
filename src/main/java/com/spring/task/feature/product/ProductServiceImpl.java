package com.spring.task.feature.product;

import com.spring.task.exception.ResourceNotFoundException;
import com.spring.task.feature.product.dto.ProductDTO;
import com.spring.task.feature.product.mapper.ProductMapper;
import com.spring.task.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO saveProduct(Product product) {

        // Logic to save product
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDTO(savedProduct);
    }



    @Override
    public ProductDTO getProductById(Long id) {
        // Logic to retrieve a product by ID
        Product product = productRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return productMapper.productToProductDTO(product);

    }

    @Override
    public ProductDTO getProductByCode(String code) {
        // Logic to retrieve a product by code
        Product product = productRepository.findByCodeAndDeletedAtIsNull(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code " + code));
        return productMapper.productToProductDTO(product);

    }

    @Override
    public List<ProductDTO> list(int limit) {
        // Logic to retrieve a limited list of products
        return productRepository.findAllByDeletedAtIsNull(PageRequest.of(0, limit)).toList()
                .stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(String code, Product productDetails) {
        // Logic to update an existing product
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code " + code));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product = productRepository.save(product);

        return productMapper.productToProductDTO(product);
    }

    @Override
    public boolean deleteProduct(String code) {
        // Logic to delete a product
        Product product = productRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with code " + code));
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
        return TRUE;
    }
}
