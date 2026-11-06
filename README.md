# Hệ Thống Quản Lý Thông Tin Sinh Viên (SIS) - Spring Boot & MySQL

Dự án được xây dựng theo đề thi thực hành: **Enterprise Application Development in Java EE - SET01**.

---

## 1. Công Nghệ Sử Dụng
- **Java**: 17 (OpenJDK Temurin)
- **Framework**: Spring Boot 3.3.4 (Spring Data JPA, Spring Web, Validation)
- **Database**: MySQL 8.4.3 (chạy trên Laragon)
- **Frontend**: HTML5, CSS3, JavaScript ES6 (tông màu xanh rêu chuẩn như ảnh đề bài)
- **Kiến trúc**: Phân tầng hoàn chỉnh (`config`, `controller`, `dto`, `entity`, `exception`, `mapper`, `repository`, `seeder`, `service`, `ui-prototype`)

---

## 2. Cấu Trúc Database (MySQL Laragon)
Database name: `sis`

- **`student_t`**: `student_id` (PK, auto_increment), `student_code` (unique), `full_name`, `address`
- **`subject_t`**: `subject_id` (PK, auto_increment), `subject_code` (unique), `subject_name`, `credit`
- **`student_score_t`**: `student_score_id` (PK, auto_increment), `student_id` (FK), `subject_id` (FK), `score1`, `score2`

Script CSDL lưu tại: `C:\sis\database\sis.sql`

---

## 3. Cách Khởi Chạy Ứng Dụng
1. Bật **Laragon** và start dịch vụ **MySQL**.
2. Mở terminal tại thư mục `C:\sis`:
   ```bash
   .\mvnw.cmd spring-boot:run
   ```
3. Mở trình duyệt web truy cập:
   ```text
   http://localhost:8080
   ```

---

## 4. Các Tính Năng Đã Hiện Thực Đầy Đủ Theo Thang Điểm (15/15 Marks)

| Câu hỏi | Tính năng | Điểm | Hiện thực |
| :---: | :--- | :---: | :--- |
| **Question 1** | Entity Subject & Student | 3 | `Subject.java` & `Student.java` |
| **Question 1** | Insert Student từ web (+ Student) | 3 | Modal form `+ Student`, validate form, lưu vào MySQL |
| **Question 2** | Entity Score | 3 | `StudentScore.java` |
| **Question 2** | Insert Score cho sinh viên (+ Score) | 3 | Modal form `+ Score`, dropdown chọn SV và Môn học, nhập điểm (0.0 - 10.0) |
| **Question 3** | Display information student | 1 | Bảng giao diện giống 100% hình mẫu đề bài |
| **Question 3** | Convert score to Grade (A/B/D/F) | 1 | Công thức `0.3 * score1 + 0.7 * score2` và bảng chuyển đổi chuẩn |
| **Bonus** | Design good UI/UX | 1 | Tông màu xanh rêu, modal popup, thông báo alert, icon sửa bút chì |

