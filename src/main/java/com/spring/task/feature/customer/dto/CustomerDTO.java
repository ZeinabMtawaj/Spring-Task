package com.spring.task.feature.customer.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CustomerDTO {
    String code;
    String name;
    String phone;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
