package vn.edu.hust.sis.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.sis.dto.response.SubjectResponse;
import vn.edu.hust.sis.entity.Subject;
import vn.edu.hust.sis.exception.ResourceNotFoundException;
import vn.edu.hust.sis.repository.SubjectRepository;
import vn.edu.hust.sis.service.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(s -> new SubjectResponse(s.getSubjectId(), s.getSubjectCode(), s.getSubjectName(), s.getCredit()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectResponse getSubjectById(Integer subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));
        return new SubjectResponse(subject.getSubjectId(), subject.getSubjectCode(), subject.getSubjectName(), subject.getCredit());
    }
}

