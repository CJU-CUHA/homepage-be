package CUHA.homepage.repository;

import CUHA.homepage.model.Category;
import CUHA.homepage.model.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Page<Exam> getExamsByCategory(Category category, Pageable pageable);
}
