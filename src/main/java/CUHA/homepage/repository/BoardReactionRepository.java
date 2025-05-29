package CUHA.homepage.repository;

import CUHA.homepage.domain.Board;
import CUHA.homepage.domain.BoardReaction;
import CUHA.homepage.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardReactionRepository extends JpaRepository<BoardReaction, Long> {
    Optional<BoardReaction> findByUserAndBoard(User user, Board board);
}
