package vn.edu.hust.sis.service;

import vn.edu.hust.sis.dto.response.SubjectResponse;

import java.util.List;

public interface SubjectService {
    List<SubjectResponse> getAllSubjects();
    SubjectResponse getSubjectById(Integer subjectId);
}

