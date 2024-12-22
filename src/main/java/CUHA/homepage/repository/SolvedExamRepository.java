package CUHA.homepage.repository;

import CUHA.homepage.model.SolvedExam;
import CUHA.homepage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SolvedExamRepository extends JpaRepository<SolvedExam, Long> {
    Optional<SolvedExam> findByUsername(User username);
}
