package com.spring.task.repository;

import com.spring.task.feature.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Collectors;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods can be added here

    Optional<Product> findByCode(String code);

    Page<Product> findAllByDeletedAtIsNull(Pageable pageable);

    Optional<Product> findByCodeAndDeletedAtIsNull(String code);

    Optional<Product> findByIdAndDeletedAtIsNull(Long id);
}
