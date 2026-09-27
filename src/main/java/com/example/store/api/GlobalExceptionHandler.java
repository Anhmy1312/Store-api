/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Admin
 */
@RestControllerAdvice // Đánh dấu đây là class xử lý lỗi toàn cục
public class GlobalExceptionHandler {
    // 1. Bắt lỗi Validation (Khi nhập sai các quy tắc trong DTO như giá âm, để trống tên)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        
        // Lấy danh sách các trường bị lỗi và câu thông báo tương ứng
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        
        ErrorResponse response = new ErrorResponse(
                new Date(),
                HttpStatus.BAD_REQUEST.value(), //Ma 404
                "Dữ liệu đầu vào không hợp lệ", 
                errors // Truyền chi tiết các trường bị lỗi vào đây
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    // 2. Bắt các lỗi chung (Exception) trong quá trình chạy(VD: lỗi logic, NullPointer...)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(Exception ex){
        ErrorResponse response = new ErrorResponse(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), //  Ma 500
                "Lỗi hệ thống : " + ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }   
}
