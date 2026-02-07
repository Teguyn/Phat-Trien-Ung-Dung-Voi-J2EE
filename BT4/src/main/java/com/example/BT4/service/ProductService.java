package com.example.BT4.service;

import com.example.BT4.model.Product;
import com.example.BT4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductByCode(String productCode) {
        return productRepository.findByCode(productCode);
    }
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public void deleteProduct(String productCode) {
        productRepository.delete(productCode);
    }
}
