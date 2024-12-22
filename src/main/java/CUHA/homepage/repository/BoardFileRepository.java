package CUHA.homepage.repository;


import CUHA.homepage.model.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
    Optional<List<BoardFile>> findAllByBoard_Id(Long boardId);
    void deleteByBoard_Id(Long boardId);
    Optional<BoardFile> findByoriginalFileName(String originalFileName);
}
