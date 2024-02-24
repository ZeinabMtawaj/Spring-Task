package com.spring.task.feature.product.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ProductDTO {
    String code;
    String name;
    String price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
