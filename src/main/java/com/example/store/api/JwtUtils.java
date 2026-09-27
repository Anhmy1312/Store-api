/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component // Giao cho Spring quản lý class này
public class JwtUtils {
    
    // Tạo 1 chìa khoá bí mật ngẫu nhiên siêu bảo mật (HS256)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // Thời gian hết hạn của thẻ (vd: 24 giờ)
    private final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
    
    // Hàm tạo token dựa trên tên đăng nhập
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username) // Tên người được cấp thẻ
                .setIssuedAt(new Date()) // Thời gian cấp
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION)) // Thời gian hết hạn
                .signWith(key) // Ký tên bằng khoá bí mật để chống làm giả
                .compact(); // Đóng gói thành chuỗi
    }
    
    // Kiểm tra xem thẻ có hợp lệ hay không (Đúng chữ ký, chưa hết hạn)
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Đọc tên người dùng từ thẻ
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    
    
    
}
