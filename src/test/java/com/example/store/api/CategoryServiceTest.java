/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.store.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Admin
 */
@ExtendWith(MockitoExtension.class) //Kích hoạt Mockito
public class CategoryServiceTest {
    // 1. Tạo một Repository giả (không kết nối SQL)
    @Mock
    private CategoryRepository categoryRepository;
    
    // 2. Bơm cái Repository giả đó vào Service thật của chúng ta
    @InjectMocks
    private CategoryService categoryService;
    
    // 3. Viết kịch bản Test cho hàm saveCategory
    @Test
    public void testSaveCategory_Succes(){
        // --- BƯỚC A: CHUẨN BỊ (ARRANGE) ---
        // Tạo dữ liệu đầu vào
        Category inputCategory = new Category();
        inputCategory.setName("Bàn phím cơ");
        
        // Tạo dữ liệu mong đợi trả về từ Database giả
        Category mockSaveCategory = new Category();
        mockSaveCategory.setId(1L);
        mockSaveCategory.setName("Bàn phím cơ");
        
        // Dạy cho Repository giả: Hễ ai gọi hàm save() thì hãy trả về mockSaveCategory!
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(mockSaveCategory);
        
        // --- BƯỚC B: THỰC THI (ACT) ---
        // Gọi hàm cần test trong Service
        Category actualResult = categoryService.saveCategory(inputCategory);
        
        // --- BƯỚC C: KIỂM TRA ĐỐI CHIẾU (ASSERT) ---
        // Đảm bảo kết quả không bị null
        Assertions.assertNotNull(actualResult);
        
        // Đảm bảo ID được tự động cấp là 1
        Assertions.assertEquals(1L, actualResult.getId());
        // Đảm bảo tên danh mục giữ nguyên
        Assertions.assertEquals("Bàn phím cơ", actualResult.getName());
        
    }
    
}
