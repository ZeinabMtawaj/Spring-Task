package com.spring.task.feature.purchase.dto;

import com.spring.task.feature.customer.dto.CustomerDTO;
import com.spring.task.feature.product.dto.ProductDTO;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PurchaseDTO {
    String code;

    CustomerDTO customer;
    ProductDTO product;


    Double amount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
