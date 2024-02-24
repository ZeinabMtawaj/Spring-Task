package com.spring.task.feature.customer.mapper;

import com.spring.task.feature.customer.Customer;
import com.spring.task.feature.customer.dto.CustomerDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);
}
