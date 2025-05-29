package CUHA.homepage.service;

import CUHA.homepage.security.dto.BoardReactionRequestDto;
import CUHA.homepage.security.dto.BoardReactionResponseDto;
import CUHA.homepage.security.dto.BoardRequestDto;
import CUHA.homepage.security.dto.BoardResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard(BoardRequestDto boardRequestDto);
    BoardResponseDto getBoard(Long id, String token);
    BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, String token);
    void deleteBoard(Long id, String token);
    List<BoardResponseDto> getBoards();

    Page<BoardResponseDto> getBoards(int page, String token);
    Page<BoardResponseDto> getBoardsByAuthor(Long author, int page, String token);
    Page<BoardResponseDto> getBoardsByTitle(String keyword, int page, String token);
    Page<BoardResponseDto> getBoardsByContent(String keyword, int page, String token);

    BoardReactionResponseDto reactToBoard(BoardReactionRequestDto boardReactionRequestDto, String token);
}
