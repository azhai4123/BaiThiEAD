package vn.edu.hust.sis.service;

import vn.edu.hust.sis.dto.request.StudentCreateRequest;
import vn.edu.hust.sis.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentCreateRequest request);
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Integer studentId);
}

