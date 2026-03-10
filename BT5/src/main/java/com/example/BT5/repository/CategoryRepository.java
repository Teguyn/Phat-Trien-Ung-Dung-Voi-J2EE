package com.example.BT5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BT5.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {}