package com.example.recyclingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tl_products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="product_id" )
    private Long id;

    @Column(nullable = false, unique = true, length = 128)
    private String productName;

    @Column(nullable = false, length = 4000)
    private String productDescription;

    @Column(nullable = false)
    private Double productPrice;

    @Lob
    @Column(nullable = true, length = Integer.MAX_VALUE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private byte[] image;

    @Column(nullable = false, updatable = true)
    private LocalDateTime createdDate;

    @PrePersist
    private void init() {
        this.createdDate = LocalDateTime.now();
    }
}