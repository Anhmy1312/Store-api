/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Admin
 */
public interface ProductRepository extends JpaRepository<Product, String>{
    // JpaRepository đã viết sẵn cho bạn các hàm: save(), findAll(), findById(), deleteById()...
}
