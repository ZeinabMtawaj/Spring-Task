package com.spring.task.feature.refund;


import com.spring.task.feature.customer.Customer;
import com.spring.task.feature.product.Product;
import com.spring.task.feature.purchase.Purchase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    @NotNull(message = "Purchase cannot be null")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive or zero")
    private Double amount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.code = UUID.randomUUID().toString();
    }

}
