package vn.edu.hust.sis.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.sis.dto.request.StudentCreateRequest;
import vn.edu.hust.sis.dto.response.StudentResponse;
import vn.edu.hust.sis.entity.Student;
import vn.edu.hust.sis.exception.AppException;
import vn.edu.hust.sis.exception.ResourceNotFoundException;
import vn.edu.hust.sis.mapper.StudentMapper;
import vn.edu.hust.sis.repository.StudentRepository;
import vn.edu.hust.sis.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public StudentResponse createStudent(StudentCreateRequest request) {
        String code = request.getStudentCode().trim();
        if (studentRepository.existsByStudentCode(code)) {
            throw new AppException("Student Code '" + code + "' already exists!");
        }

        Student student = studentMapper.toEntity(request);
        Student saved = studentRepository.save(student);
        return studentMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return studentMapper.toResponse(student);
    }
}

