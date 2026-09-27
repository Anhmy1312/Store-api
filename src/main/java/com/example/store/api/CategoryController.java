/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    // Lệnh GET: Ai cũng xem được (đã cấu hình cho phép khách xem ở SecurityConfig)
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    } 
    
    // Lệnh POST: Tạo danh mục mới (Bắt buộc phải có thẻ JWT Token)
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category saveCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
    }
}
