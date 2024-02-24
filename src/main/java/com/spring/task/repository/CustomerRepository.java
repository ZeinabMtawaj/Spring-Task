package com.spring.task.repository;

import com.spring.task.feature.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query methods can be added here

    Optional<Customer> findByCode(String code);
    Page<Customer> findAllByDeletedAtIsNull(Pageable pageable);

    Optional<Customer> findByCodeAndDeletedAtIsNull(String code);

    Optional<Customer> findByIdAndDeletedAtIsNull(Long id);




}
