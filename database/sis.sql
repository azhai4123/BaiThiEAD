-- Tạo Database sis nếu chưa tồn tại
CREATE DATABASE IF NOT EXISTS sis CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sis;

-- Xóa bảng cũ nếu cần tạo mới
DROP TABLE IF EXISTS student_score_t;
DROP TABLE IF EXISTS student_t;
DROP TABLE IF EXISTS subject_t;

-- 1. Bảng student_t (Sinh viên)
CREATE TABLE student_t (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_code VARCHAR(20) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    address VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Bảng subject_t (Môn học)
CREATE TABLE subject_t (
    subject_id INT AUTO_INCREMENT PRIMARY KEY,
    subject_code VARCHAR(20) NOT NULL UNIQUE,
    subject_name VARCHAR(100) NOT NULL,
    credit INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Bảng student_score_t (Điểm sinh viên)
CREATE TABLE student_score_t (
    student_score_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    score1 DECIMAL(5,2),
    score2 DECIMAL(5,2),
    CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES student_t(student_id) ON DELETE CASCADE,
    CONSTRAINT fk_subject_id FOREIGN KEY (subject_id) REFERENCES subject_t(subject_id) ON DELETE CASCADE,
    CONSTRAINT uq_student_subject UNIQUE (student_id, subject_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Chèn dữ liệu mẫu chính xác theo đề bài
INSERT INTO subject_t (subject_code, subject_name, credit) VALUES
('JAVA', 'Java Programming', 4),
('PHP', 'PHP Programming', 3),
('WDA', 'Web Development and Applications', 3);

INSERT INTO student_t (student_code, full_name, address) VALUES
('2007A10', 'Nguyễn Văn A', 'Hà Nội');

INSERT INTO student_score_t (student_id, subject_id, score1, score2) VALUES
(1, 1, 8.50, 7.00);

