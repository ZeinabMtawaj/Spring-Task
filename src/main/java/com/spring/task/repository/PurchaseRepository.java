package com.spring.task.repository;

import com.spring.task.feature.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
        // Custom query methods can be added here

    Optional<Purchase> findByCode(String code);

    List<Purchase> findByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}