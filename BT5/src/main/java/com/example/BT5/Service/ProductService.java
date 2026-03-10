package com.example.BT5.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.BT5.Model.Product;
import com.example.BT5.repository.ProductRepository;
import java.util.List;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;
    
    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public void saveProduct(Product product) { productRepository.save(product); }
    public Product getProductById(int id) { return productRepository.findById(id).orElse(null); }
    public void deleteProduct(int id) { productRepository.deleteById(id); }
}