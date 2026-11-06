package vn.edu.hust.sis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.sis.dto.response.SubjectResponse;
import vn.edu.hust.sis.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "*")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * Lấy danh sách môn học cho dropdown chọn môn trong modal (+ Score)
     */
    @GetMapping
    public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Integer id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }
}

