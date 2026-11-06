package vn.edu.hust.sis;

import org.junit.jupiter.api.Test;
import vn.edu.hust.sis.mapper.ScoreMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCalculationTest {

    private final ScoreMapper scoreMapper = new ScoreMapper();

    @Test
    public void testScoreFormula_SampleData() {
        // Sample in paper: score1 = 8.5, score2 = 7.0
        // Grade = 0.3 * 8.5 + 0.7 * 7.0 = 2.55 + 4.90 = 7.45 -> B
        BigDecimal finalScore = scoreMapper.calculateFinalScore(new BigDecimal("8.5"), new BigDecimal("7.0"));
        assertEquals(new BigDecimal("7.45"), finalScore);
        assertEquals("B", scoreMapper.convertScoreToGrade(finalScore));
    }

    @Test
    public void testGradeA() {
        // score1 = 8.0, score2 = 8.0 -> 8.0 -> A
        BigDecimal finalScore = scoreMapper.calculateFinalScore(new BigDecimal("8.0"), new BigDecimal("8.0"));
        assertEquals(new BigDecimal("8.00"), finalScore);
        assertEquals("A", scoreMapper.convertScoreToGrade(finalScore));
    }

    @Test
    public void testGradeB() {
        // score1 = 6.0, score2 = 6.0 -> 6.0 -> B
        BigDecimal finalScore = scoreMapper.calculateFinalScore(new BigDecimal("6.0"), new BigDecimal("6.0"));
        assertEquals("B", scoreMapper.convertScoreToGrade(finalScore));
    }

    @Test
    public void testGradeD() {
        // score1 = 4.0, score2 = 5.0 -> 0.3*4 + 0.7*5 = 1.2 + 3.5 = 4.7 -> D
        BigDecimal finalScore = scoreMapper.calculateFinalScore(new BigDecimal("4.0"), new BigDecimal("5.0"));
        assertEquals("D", scoreMapper.convertScoreToGrade(finalScore));
    }

    @Test
    public void testGradeF() {
        // score1 = 3.0, score2 = 3.5 -> 0.3*3 + 0.7*3.5 = 0.9 + 2.45 = 3.35 -> F
        BigDecimal finalScore = scoreMapper.calculateFinalScore(new BigDecimal("3.0"), new BigDecimal("3.5"));
        assertEquals("F", scoreMapper.convertScoreToGrade(finalScore));
    }
}

