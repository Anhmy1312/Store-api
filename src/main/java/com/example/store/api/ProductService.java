/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service // Đánh dấu đây là một Bean thuộc tầng Service để Spring quản lý (IoC)
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    // Lấy
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    // Tìm theo id
    public Optional<Product> getProductById(String id){
        return productRepository.findById(id);
    }
    // Lưu hoặc cập nhật
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    // Kieemr tra xem sp có tồn tại không
    public boolean existsById(String id){
        return productRepository.existsById(id);
    }
    // Xoa
    public void deleteProduct(String id){
        productRepository.deleteById(id);
    }
    
    // Lấy sản phẩm có phân trang
    public Page<Product> getAllProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }
    
}
