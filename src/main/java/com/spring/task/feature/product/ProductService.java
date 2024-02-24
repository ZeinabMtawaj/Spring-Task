package com.spring.task.feature.product;

import com.spring.task.feature.product.dto.ProductDTO;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(Product product);
    List<ProductDTO> list(int limit);
    ProductDTO getProductById(Long id);
    ProductDTO getProductByCode(String code);
    ProductDTO updateProduct(String code, Product productDetails);
    boolean deleteProduct(String code);
}
