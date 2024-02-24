package com.spring.task.feature.customer;

import com.spring.task.feature.customer.dto.CustomerDTO;

import java.util.Collection;
import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(Customer customer);

    Collection<CustomerDTO> list(int limit);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO getCustomerByCode(String code);
    CustomerDTO updateCustomer(String code, Customer customerDetails);
    boolean deleteCustomer(String code);
}

