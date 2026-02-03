package com.example.BT3.controller;

import com.example.BT3.model.Book;
import com.example.BT3.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    // Hiển thị danh sách tất cả các sách
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }
    
    // Hiển thị form thêm sách mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }
    
    // Lưu sách mới
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }
    
    // Hiển thị form sửa sách
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "edit-book";
        }
        return "redirect:/books";
    }
    
    // Cập nhật sách
    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }
    
    // Xóa sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
