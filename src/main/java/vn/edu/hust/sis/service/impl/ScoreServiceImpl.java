package vn.edu.hust.sis.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.sis.dto.request.ScoreCreateRequest;
import vn.edu.hust.sis.dto.response.StudentScoreViewResponse;
import vn.edu.hust.sis.entity.Student;
import vn.edu.hust.sis.entity.StudentScore;
import vn.edu.hust.sis.entity.Subject;
import vn.edu.hust.sis.exception.AppException;
import vn.edu.hust.sis.exception.ResourceNotFoundException;
import vn.edu.hust.sis.mapper.ScoreMapper;
import vn.edu.hust.sis.repository.StudentRepository;
import vn.edu.hust.sis.repository.StudentScoreRepository;
import vn.edu.hust.sis.repository.SubjectRepository;
import vn.edu.hust.sis.service.ScoreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

    private final StudentScoreRepository studentScoreRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ScoreMapper scoreMapper;

    public ScoreServiceImpl(StudentScoreRepository studentScoreRepository,
                            StudentRepository studentRepository,
                            SubjectRepository subjectRepository,
                            ScoreMapper scoreMapper) {
        this.studentScoreRepository = studentScoreRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.scoreMapper = scoreMapper;
    }

    @Override
    public StudentScoreViewResponse insertScore(ScoreCreateRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + request.getStudentId()));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + request.getSubjectId()));

        // Kiểm tra xem sinh viên đã có điểm môn này chưa
        if (studentScoreRepository.existsByStudent_StudentIdAndSubject_SubjectId(student.getStudentId(), subject.getSubjectId())) {
            throw new AppException("Student '" + student.getFullName() + "' already has a score record for subject '" + subject.getSubjectName() + "'!");
        }

        StudentScore studentScore = new StudentScore(student, subject, request.getScore1(), request.getScore2());
        StudentScore saved = studentScoreRepository.save(studentScore);

        return scoreMapper.toViewResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentScoreViewResponse> getAllStudentScores() {
        return studentScoreRepository.findAllWithStudentAndSubject()
                .stream()
                .map(scoreMapper::toViewResponse)
                .collect(Collectors.toList());
    }
}

