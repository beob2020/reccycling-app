package com.example.recyclingapp.repo;

import com.example.recyclingapp.model.Product;
import com.example.recyclingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByProductName(String productName);
}
