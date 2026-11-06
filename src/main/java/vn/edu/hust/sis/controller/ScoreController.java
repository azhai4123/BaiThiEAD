package vn.edu.hust.sis.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.sis.dto.request.ScoreCreateRequest;
import vn.edu.hust.sis.dto.response.StudentScoreViewResponse;
import vn.edu.hust.sis.service.ScoreService;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * Question 2: Insert the score of subjects for any student (when clicked (+ Score))
     */
    @PostMapping
    public ResponseEntity<StudentScoreViewResponse> insertScore(@Valid @RequestBody ScoreCreateRequest request) {
        StudentScoreViewResponse response = scoreService.insertScore(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Question 3: Display student information on the website like the picture below.
     * Each student and a subject have Grade = 0.3 * score1 + 0.7 * score2.
     */
    @GetMapping
    public ResponseEntity<List<StudentScoreViewResponse>> getAllScores() {
        return ResponseEntity.ok(scoreService.getAllStudentScores());
    }
}

