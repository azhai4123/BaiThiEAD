package vn.edu.hust.sis.mapper;

import org.springframework.stereotype.Component;
import vn.edu.hust.sis.dto.response.StudentScoreViewResponse;
import vn.edu.hust.sis.entity.StudentScore;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ScoreMapper {

    /**
     * Công thức tính điểm theo đề bài:
     * Grade = 0.3 * score1 + 0.7 * score2
     *
     * Bảng quy đổi Score sang Grade:
     * 8.0 to 10   -> A
     * 6.0 to 7.9  -> B
     * 4.0 to 5.9  -> D
     * less than 4.0 -> F
     */
    public BigDecimal calculateFinalScore(BigDecimal score1, BigDecimal score2) {
        if (score1 == null || score2 == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal part1 = score1.multiply(new BigDecimal("0.3"));
        BigDecimal part2 = score2.multiply(new BigDecimal("0.7"));
        return part1.add(part2).setScale(2, RoundingMode.HALF_UP);
    }

    public String convertScoreToGrade(BigDecimal finalScore) {
        if (finalScore == null) {
            return "F";
        }
        double score = finalScore.doubleValue();
        if (score >= 8.0) {
            return "A";
        } else if (score >= 6.0) {
            return "B";
        } else if (score >= 4.0) {
            return "D";
        } else {
            return "F";
        }
    }

    public StudentScoreViewResponse toViewResponse(StudentScore score) {
        if (score == null) {
            return null;
        }
        BigDecimal finalScore = calculateFinalScore(score.getScore1(), score.getScore2());
        String grade = convertScoreToGrade(finalScore);

        return new StudentScoreViewResponse(
                score.getStudentScoreId(),
                score.getStudent() != null ? score.getStudent().getStudentId() : null,
                score.getStudent() != null ? score.getStudent().getStudentCode() : "",
                score.getStudent() != null ? score.getStudent().getFullName() : "",
                score.getSubject() != null ? score.getSubject().getSubjectId() : null,
                score.getSubject() != null ? score.getSubject().getSubjectName() : "",
                score.getScore1(),
                score.getScore2(),
                score.getSubject() != null ? score.getSubject().getCredit() : null,
                finalScore,
                grade
        );
    }
}

