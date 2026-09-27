/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Admin
 */
@Component // Giao cho Spring quản lý class này
public class JwtFilter extends OncePerRequestFilter{ // OncePerRequestFilter: Chặn và kiểm tra mỗi khi có Request
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        
        // Lấy thẻ JWT từ header "Authorization" của Postman
        String authHeader = request.getHeader("Authorization");
        
        // Thẻ chuẩn phải bắt đầu từ chữ "Bearer"
        if(authHeader != null && authHeader.startsWith("Bearer")){
            String token = authHeader.substring(7); // Cắt bỏ 7 kí tự "Bearer" để lấy mã thẻ
            
            // Nếu thẻ hợp lệ, cấp quyền đi tiếp
            if(jwtUtils.validateToken(token)){
                String username = jwtUtils.getUsernameFromToken(token);
                
                // Báo cho Spring Security biết: Người này đã xác thực thành công!
                UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
    // Cho phép Request đi tiếp đến các bước sau
    filterChain.doFilter(request, response);
    }
}
