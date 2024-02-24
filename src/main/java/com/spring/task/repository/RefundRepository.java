package com.spring.task.repository;

import com.spring.task.feature.purchase.Purchase;
import com.spring.task.feature.refund.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RefundRepository extends JpaRepository<Refund, Long> {
    // Custom query methods can be added here

    Optional<Refund> findByCode(String code);

    Collection<Refund> findByPurchase(Purchase purchase);

    List<Refund> findByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query("SELECT r.purchase.amount, SUM(r.amount), r.purchase.customer.name, r.purchase.product.name, r.purchase.product.price " +
            "FROM Refund r " +
            "WHERE r.createdAt BETWEEN :startOfDay AND :endOfDay " +
            "GROUP BY r.purchase.code, r.purchase.amount, r.purchase.customer.name, r.purchase.product.name, r.purchase.product.price")
    List<Object[]> findRefundsGroupedByPurchaseWithDetails(LocalDateTime startOfDay, LocalDateTime endOfDay);

}