package CUHA.homepage.repository;

import CUHA.homepage.model.BoardFile;
import CUHA.homepage.model.ExamFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamFileRepository extends JpaRepository<ExamFile, Integer> {

    Optional<List<ExamFile>> findAllByExam_Id(Long examId);

    Optional<ExamFile> findByExam_Id(Long examId);

    void deleteByExam_Id(Long examId);

    Optional<ExamFile> findByoriginalFileName(String originalFileName);
    ;
}
