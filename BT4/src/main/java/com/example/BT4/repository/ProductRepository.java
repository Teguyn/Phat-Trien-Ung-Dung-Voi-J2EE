package com.example.BT4.repository;

import com.example.BT4.model.Product;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private int autoId = 1;
    
    public ProductRepository() {
        // Thêm một số dữ liệu mẫu - người dùng có thể upload hình ảnh sau
        products.add(new Product("P001", "iPhone 15", 20000000L, null, "Điện thoại"));
        products.add(new Product("P002", "Samsung Galaxy S24", 22000000L, null, "Điện thoại"));
        products.add(new Product("P003", "MacBook Pro", 35000000L, null, "Laptop"));
    }
    
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }
    
    public Optional<Product> findByCode(String productCode) {
        return products.stream()
                .filter(p -> p.getProductCode().equals(productCode))
                .findFirst();
    }
    
    public Product save(Product product) {
        if (product.getProductCode() == null || product.getProductCode().isEmpty()) {
            product.setProductCode("P" + String.format("%03d", autoId++));
        }
        
        // Cập nhật nếu đã tồn tại
        Optional<Product> existing = findByCode(product.getProductCode());
        if (existing.isPresent()) {
            int index = products.indexOf(existing.get());
            products.set(index, product);
        } else {
            products.add(product);
        }
        return product;
    }
    
    public void delete(String productCode) {
        products.removeIf(p -> p.getProductCode().equals(productCode));
    }
}
