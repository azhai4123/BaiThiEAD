package vn.edu.hust.sis.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ScoreCreateRequest {

    @NotNull(message = "Student is required")
    private Integer studentId;

    @NotNull(message = "Subject is required")
    private Integer subjectId;

    @NotNull(message = "Score 1 is required")
    @DecimalMin(value = "0.0", message = "Score 1 must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Score 1 must not exceed 10.0")
    private BigDecimal score1;

    @NotNull(message = "Score 2 is required")
    @DecimalMin(value = "0.0", message = "Score 2 must be at least 0.0")
    @DecimalMax(value = "10.0", message = "Score 2 must not exceed 10.0")
    private BigDecimal score2;

    public ScoreCreateRequest() {
    }

    public ScoreCreateRequest(Integer studentId, Integer subjectId, BigDecimal score1, BigDecimal score2) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score1 = score1;
        this.score2 = score2;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public BigDecimal getScore1() {
        return score1;
    }

    public void setScore1(BigDecimal score1) {
        this.score1 = score1;
    }

    public BigDecimal getScore2() {
        return score2;
    }

    public void setScore2(BigDecimal score2) {
        this.score2 = score2;
    }
}

