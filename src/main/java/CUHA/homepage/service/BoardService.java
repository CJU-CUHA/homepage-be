package CUHA.homepage.service;

import CUHA.homepage.security.dto.BoardRequestDto;
import CUHA.homepage.security.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard(BoardRequestDto boardRequestDto);
    BoardResponseDto getBoard(Long id);
    BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto);
    void deleteBoard(Long id);
    List<BoardResponseDto> getBoards();
}
