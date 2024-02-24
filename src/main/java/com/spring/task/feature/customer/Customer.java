package com.spring.task.feature.customer;

import com.spring.task.feature.customer.validation.ValidPhoneNumber;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String code;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 100, message = "Name length must be between 1 and 100 characters")
    private String name;

    @NotBlank(message = "Phone cannot be blank")
    @ValidPhoneNumber
    private String phone;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @Column(name = "deletedAt")
    private LocalDateTime deletedAt;


    @PrePersist
    protected void onCreate() {
        this.code = UUID.randomUUID().toString();
    }


}
