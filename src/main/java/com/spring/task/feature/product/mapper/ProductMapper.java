package com.spring.task.feature.product.mapper;


import com.spring.task.feature.product.Product;
import com.spring.task.feature.product.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO productToProductDTO(Product product);
}
