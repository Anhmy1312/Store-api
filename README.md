# Store API - Enterprise RESTful Backend

Dự án RESTful API Backend được xây dựng theo chuẩn kiến trúc doanh nghiệp, tập trung vào bảo mật, tối ưu hóa dữ liệu và khả năng mở rộng. Dự án này được phát triển nhằm đáp ứng các tiêu chuẩn kỹ thuật thực tế cho vị trí Thực tập sinh / Fresher Java Backend.

## 🚀 Tính năng nổi bật

*   **Kiến trúc 3 lớp (3-Tier Architecture):** Tách biệt hoàn toàn tầng Controller, Service và Repository giúp source code mạch lạc, dễ dàng bảo trì.
*   **Bảo mật JWT & Spring Security:** Thiết lập luồng xác thực Stateless với JSON Web Token, cấp quyền truy cập và bảo vệ nghiêm ngặt các API Endpoint quản trị.
*   **Quản lý Quan hệ Cơ sở dữ liệu (Spring Data JPA):** Xây dựng mối quan hệ One-to-Many giữa `Category` (Danh mục) và `Product` (Sản phẩm), tự động hóa các thao tác truy vấn và tạo khóa ngoại.
*   **Kiểm soát Luồng dữ liệu (DTO Pattern & ModelMapper):** Tự động chuyển đổi dữ liệu giữa Entity và DTO, đảm bảo an toàn thông tin đầu vào/đầu ra.
*   **Xử lý Lỗi Toàn cục (Global Exception Handling):** Sử dụng `@RestControllerAdvice` để bắt lỗi và trả về các thông báo JSON có cấu trúc thống nhất (Mã lỗi, Thời gian, Thông điệp).
*   **Tài liệu Tự động (OpenAPI/Swagger UI):** Cung cấp giao diện đồ họa để đội Frontend hoặc Tester tương tác trực tiếp với API, tích hợp sẵn cơ chế nhúng thẻ Bearer Token.
*   **Kiểm thử Tự động (Unit Testing):** Xây dựng kịch bản kiểm thử độc lập giả lập Database (Mocking) cho tầng Service bằng JUnit 5 và Mockito.

## 🛠️ Công nghệ sử dụng

*   **Ngôn ngữ:** Java
*   **Core Framework:** Spring Boot 3.x, Spring Web
*   **Database & ORM:** MySQL, Spring Data JPA, Hibernate
*   **Security:** Spring Security, thư viện JJWT
*   **Tiện ích:** ModelMapper, Jakarta Validation
*   **API Documentation:** Springdoc OpenAPI (Swagger) 2.7.0
*   **Testing:** JUnit 5, Mockito

## 📦 Hướng dẫn cài đặt

### 1. Chuẩn bị Cơ sở dữ liệu
Tạo một database trống trong MySQL. Mặc định dự án sử dụng database tên là `local_db`:
```sql
CREATE DATABASE local_db;
```
### 2. Chạy ứng dụng
Mở Terminal / Command Prompt và chạy các lệnh sau:

Bash
# Clone dự án về máy
git clone [https://github.com/Anhmy1312/store-api.git](https://github.com/Anhmy1312/store-api.git)

# Di chuyển vào thư mục dự án
cd store-api

# Khởi chạy Server
./mvnw spring-boot:run
Server sẽ khởi động tại cổng 8080.

📖 Hướng dẫn Test API (Swagger UI)
* Khi Server đang chạy, bạn hãy truy cập trình duyệt vào đường dẫn sau để xem tài liệu API và test trực tiếp:
👉 http://localhost:8080/swagger-ui/index.html

* Quy trình Test API có bảo mật:

* Mở API POST /api/auth/login.

* Gửi Request Body: {"username": "admin", "password": "123456"} để nhận Token.

* Copy chuỗi Token được trả về.

* Bấm nút Authorize màu xanh lá ở đầu trang. Dán Token vào ô trống và bấm xác nhận.

* Lúc này bạn đã có quyền thực hiện các lệnh thêm, sửa, xóa Sản phẩm và Danh mục trực tiếp trên trình duyệt.
