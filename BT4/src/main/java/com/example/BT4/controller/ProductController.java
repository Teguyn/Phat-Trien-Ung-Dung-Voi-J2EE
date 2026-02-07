package com.example.BT4.controller;

import com.example.BT4.model.Product;
import com.example.BT4.service.ProductService;
import com.example.BT4.service.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private FileUploadService fileUploadService;
    
    // Danh sách các danh mục
    private static final List<String> CATEGORIES = Arrays.asList("Điện thoại", "Laptop");
    
    // Hiển thị danh sách sản phẩm
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }
    
    // Hiển thị form tạo mới sản phẩm
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", CATEGORIES);
        return "create";
    }
    
    // Lưu sản phẩm mới
    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute Product product, 
                            BindingResult result,
                            @RequestParam("imageFile") MultipartFile imageFile,
                            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", CATEGORIES);
            return "create";
        }
        
        // Xử lý upload file
        if (!imageFile.isEmpty()) {
            try {
                String fileName = fileUploadService.uploadFile(imageFile);
                product.setImage(fileName);
            } catch (IOException e) {
                model.addAttribute("error", "Lỗi upload hình ảnh: " + e.getMessage());
                model.addAttribute("categories", CATEGORIES);
                return "create";
            }
        } else {
            model.addAttribute("error", "Vui lòng chọn hình ảnh");
            model.addAttribute("categories", CATEGORIES);
            return "create";
        }
        
        productService.saveProduct(product);
        return "redirect:/products";
    }
    
    // Hiển thị form chỉnh sửa sản phẩm
    @GetMapping("/edit/{productCode}")
    public String showEditForm(@PathVariable String productCode, Model model) {
        Optional<Product> product = productService.getProductByCode(productCode);
        
        if (product.isEmpty()) {
            return "redirect:/products";
        }
        
        model.addAttribute("product", product.get());
        model.addAttribute("categories", CATEGORIES);
        return "edit";
    }
    
    // Cập nhật sản phẩm
    @PostMapping("/update/{productCode}")
    public String updateProduct(@PathVariable String productCode,
                               @Valid @ModelAttribute Product product,
                               BindingResult result,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", CATEGORIES);
            return "edit";
        }
        
        // Xử lý upload hình ảnh mới nếu có
        if (!imageFile.isEmpty()) {
            try {
                // Xóa hình ảnh cũ
                if (product.getImage() != null && !product.getImage().isEmpty()) {
                    fileUploadService.deleteFile(product.getImage());
                }
                
                // Upload hình ảnh mới
                String fileName = fileUploadService.uploadFile(imageFile);
                product.setImage(fileName);
            } catch (IOException e) {
                model.addAttribute("error", "Lỗi upload hình ảnh: " + e.getMessage());
                model.addAttribute("categories", CATEGORIES);
                return "edit";
            }
        }
        
        product.setProductCode(productCode);
        productService.saveProduct(product);
        return "redirect:/products";
    }
    
    // Xóa sản phẩm
    @GetMapping("/delete/{productCode}")
    public String deleteProduct(@PathVariable String productCode) {
        Optional<Product> product = productService.getProductByCode(productCode);
        if (product.isPresent() && product.get().getImage() != null) {
            try {
                fileUploadService.deleteFile(product.get().getImage());
            } catch (IOException e) {
                System.out.println("Lỗi xóa file: " + e.getMessage());
            }
        }
        productService.deleteProduct(productCode);
        return "redirect:/products";
    }
}
