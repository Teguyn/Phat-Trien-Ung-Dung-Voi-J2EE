package com.example.BT5.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.BT5.Model.Category;
import com.example.BT5.repository.CategoryRepository;
import java.util.List;

@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    
    public List<Category> getAllCategories() { return categoryRepository.findAll(); }
    public void saveCategory(Category category) { categoryRepository.save(category); }
    public Category getCategoryById(int id) { return categoryRepository.findById(id).orElse(null); }
    public void deleteCategory(int id) { categoryRepository.deleteById(id); }
}
