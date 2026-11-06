package vn.edu.hust.sis.seeder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.edu.hust.sis.entity.Student;
import vn.edu.hust.sis.entity.StudentScore;
import vn.edu.hust.sis.entity.Subject;
import vn.edu.hust.sis.repository.StudentRepository;
import vn.edu.hust.sis.repository.StudentScoreRepository;
import vn.edu.hust.sis.repository.SubjectRepository;

import java.math.BigDecimal;

/**
 * Tự động kiểm tra và chèn dữ liệu mẫu chính xác như trong đề bài nếu CSDL trống:
 * - 3 Subjects: JAVA (4 credit), PHP (3 credit), WDA (3 credit)
 * - 1 Student: 2007A10 - Nguyễn Văn A - Hà Nội
 * - 1 Score: 8.5, 7.0
 */
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseSeeder.class);

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final StudentScoreRepository studentScoreRepository;

    public DatabaseSeeder(SubjectRepository subjectRepository,
                          StudentRepository studentRepository,
                          StudentScoreRepository studentScoreRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.studentScoreRepository = studentScoreRepository;
    }

    @Override
    public void run(String... args) {
        // 1. Seed Subjects
        if (subjectRepository.count() == 0) {
            log.info("Seeding sample subjects into database...");
            subjectRepository.save(new Subject("JAVA", "Java Programming", 4));
            subjectRepository.save(new Subject("PHP", "PHP Programming", 3));
            subjectRepository.save(new Subject("WDA", "Web Development and Applications", 3));
        }

        // 2. Seed Student
        if (studentRepository.count() == 0) {
            log.info("Seeding sample student into database...");
            Student student = new Student("2007A10", "Nguyen Van A", "Ha Noi");
            studentRepository.save(student);

            // 3. Seed Score
            Subject javaSubject = subjectRepository.findBySubjectCode("JAVA").orElse(null);
            if (javaSubject != null) {
                log.info("Seeding sample score into database...");
                studentScoreRepository.save(new StudentScore(student, javaSubject, new BigDecimal("8.50"), new BigDecimal("7.00")));
            }
        }
        log.info("Database seed verification completed.");
    }
}

