package vn.edu.hust.sis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.sis.entity.StudentScore;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {

    @Query("SELECT sc FROM StudentScore sc JOIN FETCH sc.student JOIN FETCH sc.subject ORDER BY sc.studentScoreId ASC")
    List<StudentScore> findAllWithStudentAndSubject();

    Optional<StudentScore> findByStudent_StudentIdAndSubject_SubjectId(Integer studentId, Integer subjectId);
    boolean existsByStudent_StudentIdAndSubject_SubjectId(Integer studentId, Integer subjectId);
}

