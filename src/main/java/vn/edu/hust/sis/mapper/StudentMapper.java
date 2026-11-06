package vn.edu.hust.sis.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.sis.dto.request.StudentCreateRequest;
import vn.edu.hust.sis.dto.response.StudentResponse;
import vn.edu.hust.sis.entity.Student;

@Component
public class StudentMapper {

    public Student toEntity(StudentCreateRequest request) {
        if (request == null) {
            return null;
        }
        Student student = new Student();
        student.setStudentCode(request.getStudentCode() != null ? request.getStudentCode().trim() : null);
        student.setFullName(request.getFullName() != null ? request.getFullName().trim() : null);
        student.setAddress(request.getAddress() != null ? request.getAddress().trim() : null);
        return student;
    }

    public StudentResponse toResponse(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentResponse(
                student.getStudentId(),
                student.getStudentCode(),
                student.getFullName(),
                student.getAddress()
        );
    }
}

