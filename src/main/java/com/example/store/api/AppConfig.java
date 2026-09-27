/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author Admin
 */
@Configuration // Đánh dấu đây là file cấu hình của Spring
public class AppConfig {
    @Bean // Khởi tạo một đối tượng ModelMapper và giao cho Spring quản lý
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
