///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.example.store.api;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * @author Admin
// */
//@RestController // Báo cho Spring biết đây là nơi tiếp nhận HTTP Request
//@RequestMapping("/api") // Định nghĩa đường dẫn gốc
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Xin chao! day la du an Spring Boot dau tien cua toi.";
//    }
//
//    @GetMapping("/product")
//    public Product getProduct() {
//        return new Product("P01", "Laptop Dell", 15000000);
//    }
//
//    @GetMapping("/products/{id}")
//    public ResponseEntity<?> getProductById(@PathVariable String id) {
//        if(id.equals("000")){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Khong tim thay san pham co ma: " + id);
//        }
//        Product p = new Product(id, "Laptop Dell", 15000000);
//        return ResponseEntity.ok(p);
//    }
//
//    @GetMapping("/products/search")
//    public String searchProduct(
//            @RequestParam(value = "keyword", defaultValue = "") String keyword,
//            @RequestParam(value = "page", defaultValue = "1") int page) {
//
//        return "Đang tìm sản phẩm có từ khóa: '" + keyword + "' ở trang " + page;
//    }
//    
//    @PostMapping("/products")
//    public String creatProduct(@RequestBody Product newProduct){
//        return "Da nhan san pham moi: " + newProduct.getName()+ " gia " + newProduct.getPrice();
//    }
//}
