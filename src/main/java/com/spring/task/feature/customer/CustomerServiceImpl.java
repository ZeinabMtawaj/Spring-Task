package com.spring.task.feature.customer;

import com.spring.task.exception.ResourceNotFoundException;
import com.spring.task.feature.customer.dto.CustomerDTO;
import com.spring.task.feature.customer.mapper.CustomerMapper;
import com.spring.task.repository.CustomerRepository;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(savedCustomer);
    }


    @Override
    public CustomerDTO getCustomerById(Long id) {
        // Implement logic to retrieve a customer by ID
        Customer customer = customerRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
        return customerMapper.customerToCustomerDTO(customer);

    }

    @Override
    public CustomerDTO getCustomerByCode(String code) {
        // Implement logic to retrieve a customer by ID
        Customer customer =customerRepository.findByCodeAndDeletedAtIsNull(code)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with code " + code));
        return  customerMapper.customerToCustomerDTO(customer);
    }



    @Override
    public List<CustomerDTO> list(int limit) {
        return customerRepository.findAllByDeletedAtIsNull(PageRequest.of(0, limit))
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }


    @Override
    public CustomerDTO updateCustomer(String code, Customer customerDetails) {
        Customer customer = customerRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with code " + code));

        // Update customer fields here
        customer.setName(customerDetails.getName());
        customer.setPhone(customerDetails.getPhone());

        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDTO(updatedCustomer);
    }

    @Override
    public boolean deleteCustomer(String code) {
        Customer customer = customerRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with code " + code));
        customer.setDeletedAt(LocalDateTime.now()); // Mark as deleted now
        customerRepository.save(customer);
        return TRUE;
    }

}