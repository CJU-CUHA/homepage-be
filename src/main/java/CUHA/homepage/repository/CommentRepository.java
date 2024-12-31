package CUHA.homepage.repository;


import CUHA.homepage.model.Board;
import CUHA.homepage.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoard_Id(Long boardId);
    Page<Comment> findAllByBoard_Id(Long boardId, Pageable pageable);

}
