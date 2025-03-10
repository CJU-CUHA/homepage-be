package CUHA.homepage.repository;

import CUHA.homepage.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAll(Pageable pageable);
    Page<Board> findAllByTitleContaining(String title, Pageable pageable);
    Page<Board> findAllByAuthor(String author, Pageable pageable);

}
