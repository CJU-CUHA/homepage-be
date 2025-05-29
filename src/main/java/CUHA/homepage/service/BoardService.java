package CUHA.homepage.service;

import CUHA.homepage.security.dto.BoardRequestDto;
import CUHA.homepage.security.dto.BoardResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard(BoardRequestDto boardRequestDto);
    BoardResponseDto getBoard(Long id);
    BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto, String token);
    void deleteBoard(Long id, String token);
    List<BoardResponseDto> getBoards();

    Page<BoardResponseDto> getBoards(int page, int size);
    Page<BoardResponseDto> getBoardsByAuthor(Long author, int page, int size);
    Page<BoardResponseDto> getBoardsByTitle(String keyword, int page, int size);
    Page<BoardResponseDto> getBoardsByContent(String keyword, int page, int size);


}
