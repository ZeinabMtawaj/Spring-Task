package com.spring.task.feature.refund.dto;

import com.spring.task.feature.customer.dto.CustomerDTO;
import com.spring.task.feature.product.dto.ProductDTO;
import com.spring.task.feature.purchase.dto.PurchaseDTO;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class RefundDTO {
    String code;
    PurchaseDTO purchase;
    Double amount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
