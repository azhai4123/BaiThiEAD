package vn.edu.hust.sis.dto.response;

import java.math.BigDecimal;

/**
 * DTO hiển thị danh sách theo đúng thứ tự các cột trong ảnh đề bài:
 * Id | Student Id | Student Name | Subject Name | Score 1 | Score 2 | Credit | Grade
 */
public class StudentScoreViewResponse {

    private Integer id;             // STT / student_score_id
    private Integer studentId;      // ID sinh viên trong DB
    private String studentCode;     // Student Id hiển thị trên bảng (vd: 2007A10)
    private String studentName;     // Student Name (vd: Nguyen Van A)
    private Integer subjectId;      // ID môn học trong DB
    private String subjectName;     // Subject Name (vd: JAVA)
    private BigDecimal score1;      // Score 1 (vd: 8.0)
    private BigDecimal score2;      // Score 2 (vd: 8.0)
    private Integer credit;         // Credit (vd: 3 hoặc 4)
    private BigDecimal finalScore;  // 0.3 * score1 + 0.7 * score2
    private String grade;           // A, B, D, F

    public StudentScoreViewResponse() {
    }

    public StudentScoreViewResponse(Integer id, Integer studentId, String studentCode, String studentName,
                                   Integer subjectId, String subjectName,
                                   BigDecimal score1, BigDecimal score2, Integer credit,
                                   BigDecimal finalScore, String grade) {
        this.id = id;
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.score1 = score1;
        this.score2 = score2;
        this.credit = credit;
        this.finalScore = finalScore;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

