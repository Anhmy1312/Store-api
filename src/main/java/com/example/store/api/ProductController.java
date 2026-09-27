/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto) {
        // Tự động chuyển đổi ProductDto -> Product chỉ với 1 dòng
        Product product = modelMapper.map(productDto, Product.class);

        // Tìm danh mục dưới Database theo ID khách gửi lên
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục có ID: " + productDto.getCategoryId()));

        // Gắn danh mục vào sản phẩm
        product.setCategory(category);
        
        // Lưu xuống Database
        Product saveProduct = productService.saveProduct(product);
        // Trả về 201 Created khi tạo thành công (Chuẩn REST)
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);

    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page, // Mặc định lấy trang đầu tiên
            @RequestParam(defaultValue = "10") int size, // Mặc định lấy 10 sản phẩm
            @RequestParam(defaultValue = "id") String sortBy // Mặc định sắp xếp theo mã ID
    
    ) {
        // Tạo cấu hình: Lấy trang số "page", kích thước "size", sắp xếp giảm dần theo "sortBy"
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        
        Page<Product> productPage = productService.getAllProducts(pageable);
        return ResponseEntity.ok(productPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
        // Hàm findById() tìm kiếm trong MySQL
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Loi 404: Khong tim thay san pham co ma: " + id);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("id") String id,
            @RequestBody ProductDto updateProductDto // Nhận DTO thay vì Entity
    ) {
        Optional<Product> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            // Nếu tìm thấy sản phẩm, tiến hành cập nhật
            Product product = optionalProduct.get();
            
            modelMapper.map(updateProductDto, product);

            Product saved = productService.saveProduct(product);
            return ResponseEntity.ok(saved);
        } else {
            // Nếu không tìm thấy, trả về lỗi 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Lỗi 404: Không tìm thấy sản phẩm mã " + id + " để cập nhật");

        }
    }
    
    @DeleteMapping("/id")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        if(productService.existsById(id)){
            productService.deleteProduct(id);
            return ResponseEntity.ok("Da xoa thanh cong san pham co ma: "+id);
            
        }else {
            // Nếu không xoa duoc, trả về lỗi 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Lỗi 404: Không xoa duoc sản phẩm mã " + id);
        }
    }
}
