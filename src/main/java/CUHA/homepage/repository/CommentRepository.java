package CUHA.homepage.repository;


import CUHA.homepage.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoard_Id(Long boardId);
}
