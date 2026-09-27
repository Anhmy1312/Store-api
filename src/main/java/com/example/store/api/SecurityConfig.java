/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Admin
 */
@Configuration // Đánh dấu đây là file cấu hình của Spring
@EnableWebSecurity // Kích hoạt tính năng tùy chỉnh bảo mật
public class SecurityConfig {

    // Gọi bác bảo vệ JwtFilter vào đây
    @Autowired
    private JwtFilter jwtFilter;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Tắt CSRF: Phải tắt khi làm REST API độc lập (không dùng form HTML của Spring)
            .csrf(csrf -> csrf.disable())
            
            // 2. Cấu hình phân quyền đường dẫn
            .authorizeHttpRequests(auth -> auth
                // Cấp quyền truy cập tự do cho giao diện tài liệu Swagger    
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                    
                // Mở cửa tự do cho khách xem danh sách sản phẩm (GET)
               .requestMatchers(HttpMethod.GET, "/api/products/**", "/api/categories/**").permitAll()
                
                // Mở cửa tự do cho các đường dẫn đăng nhập/đăng ký (chúng ta sẽ viết sau)
                .requestMatchers("/api/auth/**").permitAll()
                
                // TẤT CẢ các đường dẫn và thao tác khác (POST, PUT, DELETE) bắt buộc phải có thẻ xác thực
                .anyRequest().authenticated()
            );
            
        // Lệnh của JwtFilter: Yêu cầu Spring kiểm tra thẻ JWT TRƯỚC KHI thực hiện các lớp bảo mật khác
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
