package vn.edu.hust.sis.service;

import vn.edu.hust.sis.dto.request.ScoreCreateRequest;
import vn.edu.hust.sis.dto.response.StudentScoreViewResponse;

import java.util.List;

public interface ScoreService {
    StudentScoreViewResponse insertScore(ScoreCreateRequest request);
    List<StudentScoreViewResponse> getAllStudentScores();
}

