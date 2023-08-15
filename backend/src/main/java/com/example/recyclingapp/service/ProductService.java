package com.example.recyclingapp.service;


import com.example.recyclingapp.exceptions.ProductNotFoundException;
import com.example.recyclingapp.model.Product;
import com.example.recyclingapp.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product by id " + id + " was not found."));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product oldProduct = getProduct(id);
        if (!oldProduct.getId().equals(id)){
            throw new ProductNotFoundException("Product by id " + id + " was not found.");
        }
        oldProduct.setProductName(product.getProductName());
        oldProduct.setProductDescription(product.getProductDescription());
        oldProduct.setProductPrice(product.getProductPrice());
        oldProduct.setImage(product.getImage());
        return productRepository.save(oldProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}